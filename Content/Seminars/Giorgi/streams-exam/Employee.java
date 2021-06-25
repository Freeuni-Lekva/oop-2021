public class Employee {
    enum Department {
        INFRASTRUCTURE,
        DATA_SCIENCE,
        OPERATIONS,
        HUMAN_RESOURCES
    }

    private String firstName;
    private String lastName;
    private Department department;
    private int salary;

    public Employee(String firstName, String lastName, Department department, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Department getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }
}
