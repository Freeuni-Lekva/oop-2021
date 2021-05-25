public class Foo {
    public static synchronized void x(Main m) {
        synchronized (m) {

        }
        // synchronized (Foo.class) {

        // }
    }

    public static synchronized void y() {
        synchronized (Foo.class) {

        }
    }
}
