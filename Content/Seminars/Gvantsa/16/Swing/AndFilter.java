package Swing;

import java.util.ArrayList;
import java.util.List;

public class AndFilter implements Filter {
    private List<Filter> filters;

    public AndFilter(){
        filters = new ArrayList<>();
    }

    public void addFilter(Filter filter){
        filters.add(filter);
    }

    @Override
    public boolean filter(Student student) {
        for (Filter filter: filters){
            if (!filter.filter(student))
                return false;
        }
        return true;
    }
}
