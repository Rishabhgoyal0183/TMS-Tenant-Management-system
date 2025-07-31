package com.tms.backend.auth.service.impl;

import com.tms.backend.auth.request.LoginRequest;
import com.tms.backend.auth.service.AuthService;
import com.tms.backend.entity.MasterUser;
import com.tms.backend.entity.RbacRole;
import com.tms.backend.exception.ResourceNotFoundException;
import com.tms.backend.repository.MasterUserRepository;
import com.tms.backend.repository.RbacRoleRepository;
import com.tms.backend.response.JwtResponse;
import com.tms.backend.security.jwt.JwtUtils;
import com.tms.backend.security.userDetails.UserDetailsImpl;
import com.tms.backend.security.userSession.entity.UserSession;
import com.tms.backend.security.userSession.service.UserSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final UserSessionService userSessionService;

    private final JwtUtils jwtUtils;

    private final MasterUserRepository masterUserRepository;

    private final RbacRoleRepository rbacRoleRepository;

    @Override
    public JwtResponse login(LoginRequest loginRequest) {
        // Authenticate user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmailId(), loginRequest.getPassword()));

        // Set authentication in the security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Create user session
        UserSession userSession = userSessionService.createSession(authentication);

        // Generate JWT
        String jwt = jwtUtils.generateJwtToken(authentication, userSession.getSessionId(), userSession.getExpiresAt());

        // Get user details
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Set<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        if (!roles.contains("Super Admin")){
            MasterUser masterUser = masterUserRepository.findById(userDetails.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));

            Long propertyId = masterUser.getPropertyId();

            // Validate roles
            List<RbacRole> rbacRoles = rbacRoleRepository.findByNameAndPropertyId(roles.iterator().next(), propertyId);
            if (!roles.contains(rbacRoles.getFirst().getName())) {
                throw new BadCredentialsException("Credential does not match");
            }
        }

        return new JwtResponse(jwt);
    }

    @Override
    public void logout(String token) {

    }
}
