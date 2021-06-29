import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    private int distance() {
        return x * x + y * y;
    }

    private int distance(Point o) {
        return (o.x- x) * (o.x- x) + (o.y-y)*(o.y-y);
    }
}

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object p = new Point(1, 2);
        Point o = new Point(5, 5);
        Method m = p.getClass().getDeclaredMethod("distance", Point.class);
        m.setAccessible(true);
        System.out.println(m.invoke(p, o));
    }
}
