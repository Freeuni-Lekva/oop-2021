package ge.edu.freeuni;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Student {
    public final static int NO_ID = -1;

    private int id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("enrollment_year")
    private int enrollmentYear;

    public Student(int id, String firstName, String lastName, int enrollmentYear) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enrollmentYear = enrollmentYear;
    }

    public Student(String firstName, String lastName, int enrollmentYear) {
        this(NO_ID, firstName, lastName, enrollmentYear);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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