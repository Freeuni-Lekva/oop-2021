package shared.filter.sql;

import shared.SqlColumnName;
import shared.filter.descriptor.*;

import java.lang.reflect.Field;

public class FilterBuilder<T> {
    private final Class<T> cls;

    public FilterBuilder(Class<T> cls) {
        this.cls = cls;
    }
    
    public ParametrizedFilter build(ExpressionDescription ed) {
        if (ed instanceof CompareOperationDescription) {
            return buildCompareOperationExpression((CompareOperationDescription) ed);
        } else if (ed instanceof FieldValueDescription) {
            return buildFieldValueExpression((FieldValueDescription) ed);
        } else if (ed instanceof ConstValueDescription) {
            return buildConstValueExpression((ConstValueDescription) ed);
        } else if (ed instanceof AndExpressionDescription) {
            return buildAndExpression((AndExpressionDescription) ed);
        }
        throw new IllegalArgumentException(ed.toString());
    }

    private ParametrizedFilter buildAndExpression(AndExpressionDescription ed) {
        ParametrizedFilter ret = new ParametrizedFilter();
        boolean first = true;
        for (ExpressionDescription e : ed.getChildren()) {
            if (!first) {
                ret.append(" AND ");
            }
            first = false;
            ret.append("(").append(build(e)).append(")");
        }
        return ret;
    }

    private ParametrizedFilter buildConstValueExpression(ConstValueDescription ed) {
        return new ParametrizedFilter(ed.getValue());
    }

    private String getSqlColumnName(String val) {
        try {
            Field f = cls.getDeclaredField(val);
            // f.setAccessible(true);
            SqlColumnName name = f.getDeclaredAnnotation(SqlColumnName.class);
            if (name != null) {
                return name.name();
            }
        } catch (NoSuchFieldException e){
            // pass
        }
        return val;
    }

    private ParametrizedFilter buildFieldValueExpression(FieldValueDescription ed) {
        return new ParametrizedFilter(getSqlColumnName(ed.getFieldName()));
    }

    private ParametrizedFilter buildCompareOperationExpression(CompareOperationDescription ed) {
        switch (ed.getOperator()) {
            case EQUALS:
                return new ParametrizedFilter()
                        .append(build(ed.getLeft()))
                        .append(" = ")
                        .append(build(ed.getRight()));
            case LESS_THEN:
                return new ParametrizedFilter()
                    .append(build(ed.getLeft()))
                    .append(" < ")
                    .append(build(ed.getRight()));
            case GREATER_THEN:
                return new ParametrizedFilter()
                        .append(build(ed.getLeft()))
                        .append(" > ")
                        .append(build(ed.getRight()));
            default:
                throw new IllegalArgumentException(ed.getOperator().toString());
        }
    }}
