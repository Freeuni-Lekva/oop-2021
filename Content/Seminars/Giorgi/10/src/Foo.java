public class Foo {
    private static Foo foo;

    public static Foo getInstance() {
        if (foo == null) {
            foo = new Foo();
        }
        return foo;
    }

    private Foo() {

    }
}
