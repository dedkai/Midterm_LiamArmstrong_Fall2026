# Maple Leaf Public Library Management System

This project is a Java-based library management system for Maple Leaf Public Library. It demonstrates object-oriented programming concepts including inheritance, abstraction, polymorphism, interfaces, method overloading, validation, and static counters.

## Java Version
This project is built using Java SE-25 (Java 21 or higher is required).

## Project Structure and Hierarchy 

```text
Midterm_LiamArmstrong_Fall2026
└── src
    └── librarysystem
        ├── interfaces
        │   ├── Reportable.java
        │   └── Reservable.java
        ├── main
        │   └── LibraryManagementSystem.java
        ├── model
        │   ├── Book.java
        │   ├── Librarian.java
        │   ├── LibraryAssistant.java
        │   ├── LibraryStaff.java
        │   ├── Member.java
        │   ├── Person.java
        │   └── SeniorLibrarian.java
        └── tests
            └── LibraryManagementSystemTest.java

Person (abstract)
├── Member
└── LibraryStaff (abstract)
    ├── Librarian
    │   └── SeniorLibrarian
    └── LibraryAssistant
