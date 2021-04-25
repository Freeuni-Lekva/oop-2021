package core;

public class Student implements Moodable{
    protected int units;

    public static final int MAX_UNITS = 20;
    public static final int DEFAULT_UNITS = 15;

    public Student(int initUnits) {
        units = initUnits;
    }

    public Student() {
        this(DEFAULT_UNITS);
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        if ((units < 0) || (units > MAX_UNITS)) {
            return;
        }
        this.units = units;
    }



    public int getStress() {
        System.out.println("Get Stress");
        return (units * 10);
    }


    public boolean dropUnits(int drop) {
        if (units - drop >= 9) {
            setUnits(units - drop);
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + units;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Student){

        }

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Student other = (Student) obj;
        return units == other.units;
    }

    public static void main(String[] args) {

        Student a = new Student(12);
        Student b = new Student();

        System.out.println("a u:" + a.getUnits() + " s:" + a.getStress());
        System.out.println("b u:" + b.getUnits() + " s:" + b.getStress());

        System.out.println("a drops 3 units");
        a.dropUnits(3);
        System.out.println("a u:" + a.getUnits() + " s:" + a.getStress());

        b = a;
        b.setUnits(10);
        System.out.println("b = a, b.setUnits(10)");

        System.out.println("a u:" + a.getUnits() + " s:" + a.getStress());
    }

    @Override
    public Boolean getMood() {
        System.out.println("Student Mood");
        return getStress() > 1000;
    }
}