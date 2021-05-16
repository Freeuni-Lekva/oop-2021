package bean;

import java.util.Date;

public class Student {
    private int studentId;
    private String idNumber;
    private String firstName;
    private String lastName;
    private Date registerDate;

    public Student(){
    }

    public int getStudentId(){
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdNumber(){
        return idNumber;
    }

    public void setIdNumber(String idNumber){
        this.idNumber = idNumber;
    }

    public Date getRegisterDate(){
        return registerDate;
    }

    public void setRegisterDate(Date registerDate){
        this.registerDate = registerDate;
    }
}
