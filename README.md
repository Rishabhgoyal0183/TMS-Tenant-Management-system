# Tenant Management System

[![Java](https://img.shields.io/badge/Java-21-blue.svg)](https://www.oracle.com/java/)  
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.0-green.svg)](https://spring.io/projects/spring-boot)  

A **Tenant Management System** built for small-scale PG (Paying Guest) and flat rentals. This full-stack application lets property owners manage listings, tenant applications, leases, rent payments, and documents through a secure, role-based interface.

## 🌟 Key Features

- **Role-Based Access Control**: Owner, Tenant, and Admin roles with fine-grained, property-scoped permissions.  
- **Authentication & Security**: Secure JWT-based login/register using Spring Security and BCrypt password encoding.  
- **Property Management**: Create, view, update, and delete properties with title, address, rent, images, and amenities.  
- **Application Workflow**: Tenants browse available properties, apply online, and upload KYC documents for approval.  
- **Lease & Payment Tracking**: Automated lease creation, rent schedule generation, payment recording, and reminder notifications.  
- **Document Handling**: Secure upload and preview of ownership proofs, tenant KYC, and lease agreements (local or AWS S3 storage).  
- **Dashboard & Reporting**: Summary views and charts for occupancy rates, upcoming lease expirations, and revenue metrics.  
- **Notifications**: In-app and email alerts for application status changes, rent due reminders, and lease renewals.  

## 🛠 Technology Stack

- **Backend**: Spring Boot, Spring Security, Hibernate/JPA, MySQL  
- **Frontend**: React, React Router, Ant Design  
- **File Storage**: AWS S3, local filesystem for POC  
- **Notifications**: JavaMail (SMTP) and Firebase Cloud Messaging  
- **Build & CI**: Gradle, GitHub Actions  

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher  
- MySQL 8 or compatible  
- AWS S3 file storage  

### Installation

1. **Clone the repository**  
    ```bash
    git clone https://github.com/Rishabhgoyal0183/TMS-Tenant-Management-System.git
    cd TMS-Tenant-Management-System
    ```

## 📝 Usage

1. **Register** as an Owner, Tenant, or Admin via the frontend.  
2. **Owner**: Add new properties → Review tenant applications → Approve/Reject.  
3. **Tenant**: Browse properties → Apply and upload documents → Track application status.  
4. **Admin**: Monitor all properties, tenants, and generate high-level reports.  

## 🤝 Contributing

1. Fork the repo.  
2. Create a feature branch: `git checkout -b feature/YourFeature`  
3. Commit changes: `git commit -m "Add YourFeature"`  
4. Push branch: `git push origin feature/YourFeature`  
5. Open a Pull Request.  

Please follow the existing code style, include tests, and ensure all CI checks pass.

---

*Made with ❤️ by Rishabh Goyal*
