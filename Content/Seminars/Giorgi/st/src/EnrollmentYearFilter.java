public class EnrollmentYearFilter implements Filter {
    private final int enrollmentYear;

    public EnrollmentYearFilter(int enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    @Override
    public boolean filter(Student st) {
        return st.getEnrollmentYear() == enrollmentYear;
    }
}
