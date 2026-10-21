package librarysystem.tests;

import librarysystem.model.Librarian;
import librarysystem.model.LibraryAssistant;
import librarysystem.model.LibraryStaff;
import librarysystem.model.Member;
import librarysystem.model.Person;
import librarysystem.model.SeniorLibrarian;

/**
 * Test class for the library system.
 */
public class LibraryManagementSystemTest {

    // Helper: simple assertion
    public static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    // Print header before a test
    public static void printHeader(String testName) {
        System.out.println();
        System.out.println("========================================");
        System.out.println("Running test: " + testName);
        System.out.println("========================================");
    }

    // Print pass/fail result
    public static void printResult(String testName, boolean passed, String message) {
        if (passed) {
            System.out.println("[PASS] " + testName + " - " + message);
        } else {
            System.out.println("[FAIL] " + testName + " - " + message);
        }
    }

    // Run a test and print result
    public static void runTest(String testName, Runnable test) {
        printHeader(testName);

        try {
            test.run();
            printResult(testName, true, "completed successfully");
        } catch (Throwable e) {
            printResult(testName, false, e.getMessage());
            System.out.println("Exception details:");
            e.printStackTrace(System.out);
        }
    }

    // Test 1: valid member registration
    public static void testSuccessfulMemberRegistration() {
        Member member = Member.registerMember("Test User", "555-3001", 25, "Gold");

        assertTrue(member.getMemberNumber().startsWith("M"),
                "Member number should start with M.");

        assertTrue("Gold".equals(member.getMembershipType()),
                "Membership type should match the provided value.");

        System.out.println("Created member: " + member);
    }

    // Test 2: invalid registration is rejected
    public static void testInvalidRegistrationRejected() {
        boolean threw = false;

        try {
            Member.registerMember("Bad User", "555-3002", -1);
        } catch (IllegalArgumentException e) {
            threw = true;
        }

        assertTrue(threw, "Negative age should be rejected.");

        System.out.println("Invalid registration rejected.");
    }

    // Test 3: default late fee
    public static void testLateFeeDefaultRate() {
        Librarian librarian = new Librarian(
                "Late Fee Librarian", "555-3003", "Loans", 3000.00, 10, 40
        );

        double fee = librarian.calculateLateFee(6);

        assertTrue(Math.abs(fee - 6.00) < 0.0001,
                "Default late fee should be $1/day.");

        System.out.println("Default late fee for 6 days = $" + fee);
    }

    // Test 4: custom late fee
    public static void testLateFeeCustomRate() {
        Librarian librarian = new Librarian(
                "Custom Late Fee Librarian", "555-3004", "Loans", 3000.00, 10, 40
        );

        double fee = librarian.calculateLateFee(5, 2.5);

        assertTrue(Math.abs(fee - 12.50) < 0.0001,
                "Custom rate fee should be 5 * 2.5 = 12.5.");

        System.out.println("Custom late fee for 5 days at $2.50/day = $" + fee);
    }

    // Test 5: compensation checks
    public static void testCompensationCalculations() {
        Librarian librarian = new Librarian(
                "Librarian 1", "555-3005", "Circulation", 2500.00, 12, 30
        );

        SeniorLibrarian senior = new SeniorLibrarian(
                "Senior Librarian 1", "555-3006", "Research", 4000.00, 15, 50,
                "Archives", 500.00
        );

        LibraryAssistant assistant = new LibraryAssistant(
                "Assistant 1", "555-3007", "Reference", 1800.00, "Morning", 150.00
        );

        // Librarian = 2500 + (30 * 2)
        assertTrue(Math.abs(librarian.calculateMonthlyCompensation() - 2560.00) < 0.0001,
                "Librarian compensation should be salary + $2 per book");

        // Senior = 4000 + (50 * 2) + 500
        assertTrue(Math.abs(senior.calculateMonthlyCompensation() - 4600.00) < 0.0001,
                "Senior librarian compensation should include salary + processing + seniority");

        // Assistant = 1800 + 150
        assertTrue(Math.abs(assistant.calculateMonthlyCompensation() - 1950.00) < 0.0001,
                "Library assistant compensation should include shift allowance");

        System.out.println("Librarian compensation = $" + librarian.calculateMonthlyCompensation());
        System.out.println("Senior Librarian compensation = $" + senior.calculateMonthlyCompensation());
        System.out.println("Assistant compensation = $" + assistant.calculateMonthlyCompensation());
    }

    // Test 6: static counters
    public static void testStaticCounters() {
        int beforePeople = Person.getPersonCount();
        int beforeMembers = Member.getMemberCount();
        int beforeStaff = LibraryStaff.getStaffCount();

        Member.registerMember("Counter Member", "555-3008", 30, "Basic");
        new Librarian("Counter Librarian", "555-3009", "Loans", 3500, 10, 20);

        assertTrue(Person.getPersonCount() >= beforePeople + 1,
                "Person count should increase.");

        assertTrue(Member.getMemberCount() >= beforeMembers + 1,
                "Member count should increase.");

        assertTrue(LibraryStaff.getStaffCount() >= beforeStaff + 1,
                "Staff count should increase.");

        System.out.println("Before -> People: " + beforePeople
                + ", Members: " + beforeMembers
                + ", Staff: " + beforeStaff);

        System.out.println("After  -> People: " + Person.getPersonCount()
                + ", Members: " + Member.getMemberCount()
                + ", Staff: " + LibraryStaff.getStaffCount());
    }

    // Main test runner
    public static void main(String[] args) {
        System.out.println("====================================================");
        System.out.println("Library Management System Test Suite");
        System.out.println("====================================================");

        runTest("testSuccessfulMemberRegistration", () -> testSuccessfulMemberRegistration());
        runTest("testInvalidRegistrationRejected", () -> testInvalidRegistrationRejected());
        runTest("testLateFeeDefaultRate", () -> testLateFeeDefaultRate());
        runTest("testLateFeeCustomRate", () -> testLateFeeCustomRate());
        runTest("testCompensationCalculations", () -> testCompensationCalculations());
        runTest("testStaticCounters", () -> testStaticCounters());

        System.out.println();
        System.out.println("====================================================");
        System.out.println("All tests finished.");
        System.out.println("====================================================");
    }
}