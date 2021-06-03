import javax.swing.table.AbstractTableModel;
import java.util.List;

public class StudentModel extends AbstractTableModel {
    private StudentDao store;
    private List<Student> students;
    private Filter currFilter;

    public StudentModel(StudentDao store) {
        this.store = store;
        students = store.getAll();
        currFilter = new AllFilter();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return String.class;
            case 1: return String.class;
            case 2: return Integer.class;
            case 3: return DeleteElem.class;
        }
        throw new RuntimeException(String.valueOf(columnIndex));
    }

    public void applyFilter(Filter filter) {
        currFilter = filter;
        students = store.filter(filter);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "First Name";
            case 1: return "Last Name";
            case 2: return "Enrollment Year";
            case 3: return "Delete";
        }
        throw new RuntimeException(String.valueOf(column));
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student st = students.get(rowIndex);
        switch (columnIndex) {
            case 0: return st.getFirstName();
            case 1: return st.getLastName();
            case 2: return st.getEnrollmentYear();
            case 3: return new DeleteElem(this, rowIndex);
        }
        throw new RuntimeException(String.valueOf(columnIndex));
    }

    public void add(Student st) {
        store.add(st);
        applyFilter(currFilter);
    }

    public void remove(int id) {
        store.remove(students.get(id));
        students.remove(id);
        fireTableRowsDeleted(id, id);
    }
}
