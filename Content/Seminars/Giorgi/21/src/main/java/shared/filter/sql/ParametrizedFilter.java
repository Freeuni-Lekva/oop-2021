package shared.filter.sql;

import java.util.ArrayList;
import java.util.List;

public class ParametrizedFilter {
    private final StringBuilder sql;
    private final List<Object> params;

    public ParametrizedFilter() {
        sql = new StringBuilder();
        params = new ArrayList<>();
    }

    public ParametrizedFilter(String init) {
        this();
        sql.append(init);
    }

    public ParametrizedFilter(Object param) {
        this();
        appendParam(param);
    }

    public ParametrizedFilter appendParam(Object param) {
        sql.append("?");
        params.add(param);
        return this;
    }

    public ParametrizedFilter append(String part) {
        sql.append(part);
        return this;
    }

    public ParametrizedFilter append(ParametrizedFilter o) {
        sql.append(o.sql);
        params.addAll(o.params);
        return this;
    }

    @Override
    public String toString() {
        return sql.toString();
    }

    public List<Object> getParams() {
        return params;
    }
}
