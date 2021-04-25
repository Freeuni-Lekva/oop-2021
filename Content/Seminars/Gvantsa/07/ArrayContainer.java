public class ArrayContainer implements MyContainer{
    private int arr[];

    public ArrayContainer(){

    }

    @Override
    public int getLength() {
        return arr.length;
    }

    @Override
    public int get(int index) {
        return arr[index];
    }

    @Override
    public void add(int index) {
    }
}
