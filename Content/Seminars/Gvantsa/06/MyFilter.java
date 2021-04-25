import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class MyFilter implements Filter{

    private List<Integer> indexes;

    public MyFilter(){
        indexes = new ArrayList<Integer>();
    }

    @Override
    public int size() {
        return indexes.size();
    }

    private int find(int index){
        for (int i = 0; i < indexes.size(); i++) {
            if (indexes.get(i) == index)
                return i;
        }
        return -1;
    }

    @Override
    public boolean isPresent(int index) {
        int foundIndex = Collections.binarySearch(indexes, index);
        return foundIndex >= 0;
    }

    @Override
    public void add(int index) {
        int foundIndex = Collections.binarySearch(indexes, index);
        if (foundIndex >= 0) return;
        int newIndex = -foundIndex - 1;
        indexes.add(newIndex, index);
    }

    @Override
    public void remove(int index) {
        int foundIndex = Collections.binarySearch(indexes, index);
        if (foundIndex < 0) return;
        indexes.remove(foundIndex);
    }
}
