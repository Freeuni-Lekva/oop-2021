package shared.filter.reflection;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class FieldValue<T> implements Expression<T> {
    private final String fieldName;

    public FieldValue(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public T evaluate(Object o) {
        try {
            PropertyDescriptor pd = new PropertyDescriptor(fieldName, o.getClass());
            return (T) pd.getReadMethod().invoke(o);
//            Field f = o.getClass().getDeclaredField(fieldName);
//            f.setAccessible(true);
//            return (T) f.get(o);
        } catch (IllegalAccessException | IntrospectionException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }
}
