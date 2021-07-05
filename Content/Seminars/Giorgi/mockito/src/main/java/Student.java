public class Student {
    public final static int NO_ID = -1;

    private int id;
    private String firstName;
    private String lastName;
    private int enrollmentYear;
    private double gpa;

    public Student(int id, String firstName, String lastName, int enrollmentYear, double gpa) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enrollmentYear = enrollmentYear;
        this.gpa = gpa;
    }

    public Student(String firstName, String lastName, int enrollmentYear, double gpa) {
        this(NO_ID, firstName, lastName, enrollmentYear, gpa);
    }

    public Student() {
        this.id = NO_ID;
        this.firstName = "";
        this.lastName = "";
        this.enrollmentYear = 0;
        this.gpa = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
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

    @Override
    public boolean equals(Object obj){
        if (!(obj instanceof Student))
            return false;
        Student student = (Student) obj;
        return student.getFirstName() == this.getFirstName()
                && student.getLastName() == this.getLastName()
                && student.getEnrollmentYear() == this.getEnrollmentYear()
                && student.getGpa() == this.getGpa();
    }
}