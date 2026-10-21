package librarysystem.model;

public class SeniorLibrarian extends Librarian {
    private final String specialization;
    private final double seniorityAllowance;

    public SeniorLibrarian(String name, String phoneNumber, String department,
                          double monthlySalary, int dailyIssueLimit, int booksProcessed,
                          String specialization, double seniorityAllowance) {
        super(name, phoneNumber, department, monthlySalary, dailyIssueLimit, booksProcessed);

        if (specialization == null || specialization.isBlank()) {
            throw new IllegalArgumentException("Specialization is required.");
        }
        if (seniorityAllowance < 0) {
            throw new IllegalArgumentException("Seniority allowance cannot be negative.");
        }

        this.specialization = specialization.trim();
        this.seniorityAllowance = seniorityAllowance;
    }

    public String getSpecialization() {
        return specialization;
    }

    public double getSeniorityAllowance() {
        return seniorityAllowance;
    }

    @Override
    public double calculateMonthlyCompensation() {
        return super.calculateMonthlyCompensation() + seniorityAllowance;
    }

    @Override
    public String getRoleDescription() {
        return "Senior Librarian";
    }

    @Override
    public void displayStaffDetails() {
        super.displayStaffDetails();
        System.out.printf(
                "Specialization: %s | Seniority Allowance: $%.2f | Total Monthly Compensation: $%.2f%n",
                specialization, seniorityAllowance, calculateMonthlyCompensation()
        );
    }
}