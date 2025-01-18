
# Dynamic Employee Management System (DEMS)

## Overview

The Dynamic Employee Management System (DEMS) is a Spring Boot application designed to efficiently manage employee data. It leverages PostgreSQL for data persistence, Redis for caching frequently accessed information, and follows a layered architecture for maintainability and scalability. The system implements core features for employee management, searching, and filtering, with a focus on data integrity and performance. Administrative privileges are required for certain operations.

## Features

*   **Employee Management:**
    *   **Add Employee (Admin only):**  Allows administrators to create new employee records.
    *   **Retrieve Employee:** Fetches details of a specific employee by their ID.
    *   **Update Employee (Admin only):** Enables administrators to modify existing employee information.
    *   **Delete Employee (Admin only):**  Allows administrators to remove employee records from the system.
*   **Search and Filtering:**
    *   **Search Employees:** Find employees by name or department.
    *   **Filter Employees:** Narrow down employee lists based on:
        *   Salary range (minimum and maximum salary).
*   **Caching:**
    *   Frequently accessed employee profiles are cached in Redis to improve response times.
    *   The cache is automatically invalidated when employee data is updated or deleted.
*   **User Management:**
    *   **Create User (Admin only):** Administrators can create new user accounts for the system.

## Technologies Used

*   **Backend:**
    *   Java 21
    *   Spring Boot
    *   Spring Data JPA
    *   Spring Data Redis
    *   ModelMapper
    *   Lombok
*   **Database:**
    *   PostgreSQL
*   **Caching:**
    *   Redis
*   **Connection Pooling:**
    *   HikariCP
*   **Build Tool:**
    *   Maven


