package shared;

import java.util.ArrayList;
import java.util.List;

public class AndFilter implements Filter {
    private List<Filter> filters;

    public AndFilter() {
        filters = new ArrayList<>();
    }

    public void add(Filter f) {
        filters.add(f);
    }

    public int size() {
        return filters.size();
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

    @Override
    public String format() {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < filters.size(); i++) {
            if (i > 0) {
                ret.append(" AND ");
            }
            ret.append('(');
            ret.append(filters.get(i).format());
            ret.append(')');
        }
        return ret.toString();
    }
}