package Swing;

public class FirstNameFilter implements Filter{
    private String firstName;

    public FirstNameFilter(String firstName){
        this.firstName = firstName;
    }

    @Override
    public boolean filter(Student student) {

        return student.getFirstName().startsWith(firstName);
    }
}
