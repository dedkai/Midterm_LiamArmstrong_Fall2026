package librarysystem.main;

import librarysystem.interfaces.Reportable;
import librarysystem.interfaces.Reservable;
import librarysystem.model.Book;
import librarysystem.model.Librarian;
import librarysystem.model.LibraryAssistant;
import librarysystem.model.LibraryStaff;
import librarysystem.model.Member;
import librarysystem.model.Person;
import librarysystem.model.SeniorLibrarian;

import java.util.ArrayList;
import java.util.List;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        System.out.println("=== Maple Leaf Public Library Management System ===");
        List<Member> members = new ArrayList<>();
        List<LibraryStaff> staffList = new ArrayList<>();

        // Successful registration
        try {
            Member member1 = Member.registerMember("Alice Johnson", "555-1001", 28, "Premium");
            members.add(member1);
            System.out.println("Registration successful: " + member1);
        } catch (IllegalArgumentException e) {
            System.out.println("Registration failed: " + e.getMessage());
        }

        // Successful registration
        try {
            Member member2 = Member.registerMember("Benjamin Lee", "555-1002", 34, "Student");
            members.add(member2);
            System.out.println("Registration successful: " + member2);
        } catch (IllegalArgumentException e) {
            System.out.println("Registration failed: " + e.getMessage());
        }

        // Unsuccessful registration due to invalid age
        try {
            Member invalidMember = Member.registerMember("Invalid User", "555-1003", -3);
            members.add(invalidMember);
        } catch (IllegalArgumentException e) {
            System.out.println("Unsuccessful registration: " + e.getMessage());
        }

        // Staff creation
        Librarian librarian = new Librarian(
                "Maya Chen", "555-2001", "Circulation", 3200.00, 18, 75
        );
        SeniorLibrarian seniorLibrarian = new SeniorLibrarian(
                "Daniel Smith", "555-2002", "Cataloguing", 4200.00, 20, 120,
                "Digital Archives", 600.00
        );
        LibraryAssistant libraryAssistant = new LibraryAssistant(
                "Priya Nair", "555-2003", "Reference", 2400.00, "Evening", 300.00
        );

        staffList.add(librarian);
        staffList.add(seniorLibrarian);
        staffList.add(libraryAssistant);

        System.out.println("\n=== Member Details ===");
        for (Member member : members) {
            member.displayBasicDetails();
            System.out.println(member.generateReport());
        }

        System.out.println("\n=== Staff Details ===");
        for (LibraryStaff staff : staffList) {
            staff.displayStaffDetails();
            System.out.printf("Compensation: $%.2f%n", staff.calculateMonthlyCompensation());
            System.out.println();
        }

        // Late fee calculations
        System.out.println("\n=== Late Fee Calculations ===");
        System.out.printf("Default late fee: $%.2f%n", librarian.calculateLateFee(5));
        System.out.printf("Custom late fee: $%.2f%n", librarian.calculateLateFee(5, 2.50));

        try {
            librarian.calculateLateFee(-4);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid late fee request: " + e.getMessage());
        }

        try {
            librarian.calculateLateFee(3, -1.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid rate: " + e.getMessage());
        }

        // Runtime polymorphism with LibraryStaff references
        System.out.println("\n=== Runtime Polymorphism Demo ===");
        LibraryStaff staffRef = seniorLibrarian;
        staffRef.displayStaffDetails();

        // Interface demonstration
        System.out.println("\n=== Interface Demonstration ===");
        Reportable reportableMember = members.getFirst();
        System.out.println(reportableMember.generateReport());

        Reservable reservableMember = members.getFirst();
        System.out.println("Reservation status: " + reservableMember.reserveBook("B-101"));
        System.out.println("Reservation status: " + reservableMember.reserveBook(""));

        // Book reservation test
        Book book = new Book("B-102", "Java 21 for Beginners", "Alex Martin");
        System.out.println("Book available before reserve: " + book.isAvailable());
        System.out.println("Reservation status for book: " + book.reserveBook("B-102"));
        System.out.println("Book available after reserve: " + book.isAvailable());

        // Unsuccessful reservation for unavailable book
        System.out.println("Second attempt on the same book: " + book.reserveBook("B-102"));

        // Example of outstanding fee preventing reservation
        Member feeMember = Member.registerMember("Carol Green", "555-1103", 41, "Premium");
        feeMember.setOutstandingFees(15.50);
        System.out.println("Reservation blocked by outstanding fee: " + feeMember.reserveBook("B-201"));

        // Display static counters
        System.out.println("\n=== Static Counter Summary ===");
        System.out.println("Total People: " + Person.getPersonCount());
        System.out.println("Total Members: " + Member.getMemberCount());
        System.out.println("Total Staff: " + LibraryStaff.getStaffCount());

        System.out.println("\n=== Program complete ===");
    }
}