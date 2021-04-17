public class Test {

    public static void invoke(Parent parent){
        System.out.println("Invoke parent");
        parent.go();
    }

    public static void invoke(Child child){
        System.out.println("Invoke child");
        child.go();

    }

    public static void main(String[] args){
        Parent parent = new Parent();
        Child child = new Child();
        // Child parentC = (Child) new Parent();
        Parent childP = new Child();

        Test.invoke(parent);
        Test.invoke(child);
        Test.invoke(childP);
    }
}
