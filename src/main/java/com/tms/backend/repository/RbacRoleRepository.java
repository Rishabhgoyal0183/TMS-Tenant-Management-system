package com.tms.backend.repository;

import com.tms.backend.entity.RbacRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RbacRoleRepository extends JpaRepository<RbacRole, Long> {

    List<RbacRole> findByNameAndPropertyId(String name, Long propertyId);
}
