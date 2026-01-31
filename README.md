# Student Course Registration API (Assignment 4: SOLID & Advanced OOP)

## A. Project Overview
This project represents **Milestone 2** of the Course Registration System. It has been refactored to strictly follow **SOLID principles**, implement **Layered Architecture**, and utilize advanced Java features like **Generics, Lambdas, and Reflection**.

## B. SOLID Principles Documentation
* **SRP (Single Responsibility):** Each class has one job. `CourseService` handles logic, `CourseRepository` handles SQL, `Main` handles UI.
* **OCP (Open/Closed):** The system supports new course types (`OnlineCourse`, `OnCampusCourse`) without modifying the base logic in Service or Entity classes.
* **LSP (Liskov Substitution):** Subclasses (`OnlineCourse`) can fully replace the parent `Course` class without breaking the application logic.
* **ISP (Interface Segregation):** Interfaces are split into specific behaviors: `Creditable` (for credits) and `Schedulable` (for timing), avoiding fat interfaces.
* **DIP (Dependency Inversion):** High-level `CourseService` depends on the abstraction `ICrudRepository<T>`, not on the concrete `CourseRepository` class.

## C. Advanced OOP Features
* **Generics:** Implemented `ICrudRepository<T>` to create a reusable data access layer for any entity type.
* **Lambdas:** Used in `SortingUtils` to sort courses by name: `(c1, c2) -> c1.getName().compareTo(...)`.
* **Reflection:** Created `ReflectionUtils` to inspect class fields and methods at runtime (demonstrated in Main).
* **Interface Methods:**
    * **Default:** `isHighCreditCourse()` in `Creditable` interface.
    * **Static:** `getTimeZoneInfo()` in `Schedulable` interface.

## D. Architecture & Layers
* **Controller (`Main`):** Entry point, demonstrates features using Reflection and Service calls.
* **Service (`CourseService`):** Orchestrates business logic, validation, and transaction flow.
* **Repository (`CourseRepository`):** interacting with MySQL via JDBC using `PreparedStatement`.
* **Utils:** Helper classes for sorting and reflection.

## E. Execution Instructions
1.  Ensure MySQL is running and `course_reg_db` exists (see `src/resources/schema.sql`).
2.  Update database credentials in `src/repository/DatabaseConnection.java`.
3.  Run `src/controller/Main.java`.
