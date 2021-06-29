package shared.filter.reflection;

import java.util.ArrayList;
import java.util.List;

public class AndFilter implements Filter {
    private List<Filter> filters;

    public AndFilter() {
        filters = new ArrayList<>();
    }

    public AndFilter add(Filter f) {
        filters.add(f);
        return this;
    }

    public int size() {
        return filters.size();
    }

    @Override
    public Boolean evaluate(Object o) {
        for (Filter f : filters) {
            if (!f.evaluate(o)) {
                return false;
            }
        }
        return true;
    }
}