public class NoOpFilter implements Filter {
    @Override
    public boolean filter(Student st) {
        return true;
    }
}
