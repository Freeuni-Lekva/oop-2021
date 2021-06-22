package shared;

public class AllFilter implements Filter {
    @Override
    public boolean filter(Student st) {
        return true;
    }

    @Override
    public String format() {
        return "1 = 1";
    }
}