package librarysystem.model;

public class LibraryAssistant extends LibraryStaff {
    private final String shiftType;
    private final double shiftAllowance;

    public LibraryAssistant(String name, String phoneNumber, String department,
                           double monthlySalary, String shiftType, double shiftAllowance) {
        super(name, phoneNumber, department, monthlySalary);

        if (shiftType == null || shiftType.isBlank()) {
            throw new IllegalArgumentException("Shift type is required.");
        }
        if (shiftAllowance < 0) {
            throw new IllegalArgumentException("Shift allowance cannot be negative.");
        }

        this.shiftType = shiftType.trim();
        this.shiftAllowance = shiftAllowance;
    }

    public String getShiftType() {
        return shiftType;
    }

    public double getShiftAllowance() {
        return shiftAllowance;
    }

    @Override
    public double calculateMonthlyCompensation() {
        return getMonthlySalary() + shiftAllowance;
    }

    @Override
    public String getRoleDescription() {
        return "Library Assistant";
    }

    @Override
    public void displayStaffDetails() {
        super.displayStaffDetails();
        System.out.printf(
                "Role: %s | Shift Type: %s | Shift Allowance: $%.2f | Monthly Compensation: $%.2f%n",
                getRoleDescription(), shiftType, shiftAllowance, calculateMonthlyCompensation()
        );
    }
}