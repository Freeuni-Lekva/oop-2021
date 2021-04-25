package core;

public class Grad extends Student {

    private int yearsOnThesis;


    public Grad(int units, int thesis) {
        super(units);

        yearsOnThesis = thesis;
    }


    public Grad() {
        this(10, 0);
    }


    @Override
    public int getStress() {
        int stress = super.getStress();

        return (stress * 2 + yearsOnThesis);
    }

    public void setYearsOnThesis(int yearsOnThesis) {
        this.yearsOnThesis = yearsOnThesis;
    }

    public int getYearsOnThesis() {
        return (yearsOnThesis);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + yearsOnThesis;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!super.equals(obj)) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Grad other = (Grad) obj;

        return yearsOnThesis == other.yearsOnThesis;
    }

    @Override
    public Boolean getMood() {
        System.out.println("Grad Mood");
        return getStress() > 1000;
    }



    public static void main(String[] args) {
        Student s = new Student(13);
        Grad g = new Grad(13, 2);
        Student x = null;

        System.out.println("s " + s.getStress());
        System.out.println("g " + g.getStress());


        s.dropUnits(3);
        g.dropUnits(3);
        System.out.println("s " + s.getStress());
        System.out.println("g " + g.getStress());


        // s.getYearsOnThesis();
        g.getYearsOnThesis();

        x = g;

        x.getStress();

        // g = x;

        // x.getYearsOnThesis();

        ((Grad) x).getYearsOnThesis();
    }

    @Override
    public String toString() {
        return "Grad(units=" + units + "; years=" + yearsOnThesis + ")";
    }
}
