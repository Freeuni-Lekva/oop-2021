public class FullFilter implements Filter {
    private String firstName;
    private String lastName;
    private Integer enrollmentYear;

    public FullFilter(String firstName, String lastName, Integer enrollmentYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.enrollmentYear = enrollmentYear;
    }

    @Override
    public boolean filter(Student st) {
        if (firstName != null && !st.getFirstName().equals(firstName)) {
            return false;
        }
        if (lastName != null && !st.getLastName().equals(lastName)) {
            return false;
        }
        if (enrollmentYear != null && st.getEnrollmentYear() != enrollmentYear) {
            return false;
        }
        return true;
    }

    @Override
    public String format() {
        return null;
    }
}
