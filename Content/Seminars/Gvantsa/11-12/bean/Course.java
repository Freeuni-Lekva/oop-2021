package bean;

import java.util.Date;

public class Course {
    private int courseId;
    private String idNumber;
    private String courseName;
    private String courseType;
    private int courseCredit;

    public Course(){
    }

    public int getCourseId(){
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName(){
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseType(){
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public int getCourseCredit(){
        return courseCredit;
    }

    public void setCourseCredit(int courseCredit){
        this.courseCredit = courseCredit;
    }


}
