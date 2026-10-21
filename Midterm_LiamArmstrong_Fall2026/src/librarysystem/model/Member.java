package librarysystem.model;

import librarysystem.interfaces.Reportable;
import librarysystem.interfaces.Reservable;

public class Member extends Person implements Reportable, Reservable {
    private static int memberCount = 0;

    private final String memberNumber;
    private final int age;
    private final String membershipType;
    private double outstandingFees;
    private String lastReservedBookId;

    public Member(String name, String phoneNumber, int age) {
        this(name, phoneNumber, age, "Standard");
    }

    public Member(String name, String phoneNumber, int age, String membershipType) {
        super(name, phoneNumber);

        if (age <= 0 || age > 120) {
            throw new IllegalArgumentException("Age must be between 1 and 120.");
        }
        if (membershipType == null || membershipType.isBlank()) {
            throw new IllegalArgumentException("Membership type is required.");
        }

        this.memberNumber = "M" + (++memberCount);
        this.age = age;
        this.membershipType = membershipType.trim();
        this.outstandingFees = 0.0;
    }

    public static int getMemberCount() {
        return memberCount;
    }

    public static Member registerMember(String name, String phoneNumber, int age) {
        return new Member(name, phoneNumber, age);
    }

    public static Member registerMember(String name, String phoneNumber, int age, String membershipType) {
        return new Member(name, phoneNumber, age, membershipType);
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public int getAge() {
        return age;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public double getOutstandingFees() {
        return outstandingFees;
    }

    public void setOutstandingFees(double outstandingFees) {
        if (outstandingFees < 0) {
            throw new IllegalArgumentException("Outstanding fees cannot be negative.");
        }
        this.outstandingFees = outstandingFees;
    }

    @Override
    public String getRoleDescription() {
        return "Library Member";
    }

    @Override
    public String generateReport() {
        return String.format(
                "Member Report: %s | Member Number: %s | Membership: %s | Outstanding Fees: $%.2f",
                getName(), memberNumber, membershipType, outstandingFees
        );
    }

    @Override
    public boolean reserveBook(String bookId) {
        if (bookId == null || bookId.isBlank()) {
            System.out.println("Reservation failed: Book ID is required.");
            return false;
        }

        if (outstandingFees > 0) {
            System.out.println("Reservation failed: Member has outstanding fees.");
            return false;
        }

        this.lastReservedBookId = bookId.trim();
        System.out.println("Reservation successful for " + getName() + ": " + bookId);
        return true;
    }

    public String getLastReservedBookId() {
        return lastReservedBookId;
    }

    @Override
    public String toString() {
        return String.format(
                "Member{memberNumber='%s', age=%d, membershipType='%s', outstandingFees=%.2f}",
                memberNumber, age, membershipType, outstandingFees
        );
    }
}