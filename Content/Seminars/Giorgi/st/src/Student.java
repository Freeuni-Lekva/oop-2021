public class Student {
    private String firstName;
    private String lastName;
    private int enrollmentYear;

    public Student(String firstName, String lastName, int enrollmentYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.enrollmentYear = enrollmentYear;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(int enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }
}
