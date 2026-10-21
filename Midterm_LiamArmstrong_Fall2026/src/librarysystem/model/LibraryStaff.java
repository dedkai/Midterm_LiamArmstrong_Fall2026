package librarysystem.model;

public abstract class LibraryStaff extends Person {
    private static int staffCount = 0;

    private final String staffId;
    private final String department;
    private final double monthlySalary;

    public LibraryStaff(String name, String phoneNumber, String department, double monthlySalary) {
        super(name, phoneNumber);

        if (department == null || department.isBlank()) {
            throw new IllegalArgumentException("Department is required.");
        }
        if (monthlySalary < 0) {
            throw new IllegalArgumentException("Monthly salary cannot be negative.");
        }

        this.staffId = "STF" + (++staffCount);
        this.department = department.trim();
        this.monthlySalary = monthlySalary;
    }

    public static int getStaffCount() {
        return staffCount;
    }

    public String getStaffId() {
        return staffId;
    }

    public String getDepartment() {
        return department;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    public abstract double calculateMonthlyCompensation();

    public void displayStaffDetails() {
        System.out.printf(
                "Staff ID: %s | Name: %s | Department: %s | Monthly Salary: $%.2f%n",
                staffId, getName(), department, monthlySalary
        );
    }

    @Override
    public String toString() {
        return String.format(
                "LibraryStaff{staffId='%s', department='%s', monthlySalary=%.2f}",
                staffId, department, monthlySalary
        );
    }
}