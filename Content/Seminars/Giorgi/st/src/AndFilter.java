import java.util.ArrayList;
import java.util.List;

public class AndFilter implements Filter{
    private List<Filter> filters;

    public AndFilter() {
        this.filters = new ArrayList<>();
    }

    public void add(Filter f) {
        filters.add(f);
    }

    @Override
    public boolean filter(Student st) {
        for (Filter f : filters) {
            if (!f.filter(st)) {
                return false;
            }
        }
        return true;
    }
}
