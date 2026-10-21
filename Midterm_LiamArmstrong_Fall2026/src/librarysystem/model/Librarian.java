package librarysystem.model;

public class Librarian extends LibraryStaff {
    private final int dailyIssueLimit;
    private final int booksProcessed;

    public Librarian(String name, String phoneNumber, String department,
                     double monthlySalary, int dailyIssueLimit, int booksProcessed) {
        super(name, phoneNumber, department, monthlySalary);

        if (dailyIssueLimit <= 0) {
            throw new IllegalArgumentException("Daily issue limit must be positive.");
        }
        if (booksProcessed < 0) {
            throw new IllegalArgumentException("Books processed cannot be negative.");
        }

        this.dailyIssueLimit = dailyIssueLimit;
        this.booksProcessed = booksProcessed;
    }

    public int getDailyIssueLimit() {
        return dailyIssueLimit;
    }

    public int getBooksProcessed() {
        return booksProcessed;
    }

    public double calculateLateFee(int daysOverdue) {
        return calculateLateFee(daysOverdue, 1.0);
    }

    public double calculateLateFee(int daysOverdue, double dailyRate) {
        if (daysOverdue < 0) {
            throw new IllegalArgumentException("Overdue days cannot be negative.");
        }
        if (dailyRate <= 0) {
            throw new IllegalArgumentException("Daily rate must be greater than zero.");
        }
        return daysOverdue * dailyRate;
    }

    @Override
    public double calculateMonthlyCompensation() {
        return getMonthlySalary() + (booksProcessed * 2.0);
    }

    @Override
    public String getRoleDescription() {
        return "Librarian";
    }

    @Override
    public void displayStaffDetails() {
        super.displayStaffDetails();
        System.out.printf(
                "Role: %s | Daily Issue Limit: %d | Books Processed: %d | Monthly Compensation: $%.2f%n",
                getRoleDescription(), dailyIssueLimit, booksProcessed, calculateMonthlyCompensation()
        );
    }
}