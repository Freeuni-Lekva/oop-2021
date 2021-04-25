public interface Filter {
    // Returns total number of objects present.
    public int size();

    // Returns true if object at given position is present.
    public boolean isPresent(int index);

    // Marks object with given index as present.
    public void add(int index);

    // Removes object with given index from filter.
    public void remove(int index);
}