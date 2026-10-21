package librarysystem.model;

public abstract class Person {
    private static int personCount = 0;

    private final String personId;
    private final String name;
    private final String phoneNumber;

    public Person(String name, String phoneNumber) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required.");
        }
        if (phoneNumber == null || phoneNumber.isBlank()) {
            throw new IllegalArgumentException("Phone number is required.");
        }

        this.name = name.trim();
        this.phoneNumber = phoneNumber.trim();
        this.personId = "P" + (++personCount);
    }

    public static int getPersonCount() {
        return personCount;
    }

    public String getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void displayBasicDetails() {
        System.out.printf("Person ID: %s | Name: %s | Phone: %s%n",
                personId, name, phoneNumber);
    }

    public abstract String getRoleDescription();

    @Override
    public String toString() {
        return String.format("Person{id='%s', name='%s', phone='%s'}",
                personId, name, phoneNumber);
    }
}