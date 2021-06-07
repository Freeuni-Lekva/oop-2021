package Swing;

public class Student {
    private String firstName;
    private String lastName;
    private int year;
    private String status;

    public Student(String firstName, String lastName, int year, String status){
        this.firstName = firstName;
        this.lastName = lastName;
        this.year = year;
        this.status = status;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public int getYear(){
        return year;
    }

    public String getStatus(){
        return status;
    }
}
