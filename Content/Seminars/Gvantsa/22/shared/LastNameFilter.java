package shared;

public class LastNameFilter implements Filter {
    private String lastName;

    public LastNameFilter(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean filter(Student st) {
        return lastName.equals(st.getLastName());
    }

    @Override
    public String format() {
        return new StringBuilder()
                .append("last_name = ")
                .append('"')
                .append(lastName)
                .append('"')
                .toString();
    }
}