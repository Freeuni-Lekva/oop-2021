package shared;

public class FirstNameFilter implements Filter {
    private String firstName;

    public FirstNameFilter(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public boolean filter(Student st) {
        return firstName.equals(st.getFirstName());
    }

    @Override
    public String format() {
        return new StringBuilder()
                .append("first_name = ")
                .append('"')
                .append(firstName)
                .append('"')
                .toString();
    }
}