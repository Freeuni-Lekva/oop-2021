package shared;

public class EnrollmentYearFilter implements Filter{
    private int enrollmentYear;

    public EnrollmentYearFilter(int enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    @Override
    public boolean filter(Student st) {
        return enrollmentYear == st.getEnrollmentYear();
    }

    @Override
    public String format() {
        return new StringBuilder()
                .append("enrollment_year = ")
                .append(enrollmentYear)
                .toString();
    }
}