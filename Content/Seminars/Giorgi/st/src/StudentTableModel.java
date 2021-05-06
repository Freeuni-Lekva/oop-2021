import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class StudentTableModel extends AbstractTableModel {
    private List<Student> students;

    public void setStudents(List<Student> students) {
        this.students = students;
        fireTableDataChanged();
    }

    public StudentTableModel() {
        this.students = new ArrayList<>();
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "First Name";
            case 1: return "Last Name";
            case 2: return "Enrollment Year";
        }
        throw new RuntimeException(String.valueOf(column));
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Student st = students.get(rowIndex);
        switch (columnIndex) {
            case 0: st.setFirstName(aValue.toString());
            case 1: st.setLastName(aValue.toString());
            case 2: st.setEnrollmentYear(Integer.valueOf(aValue.toString()));
        }
        throw new RuntimeException(String.valueOf(columnIndex));
    }

    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student st = students.get(rowIndex);
        switch (columnIndex) {
            case 0: return st.getFirstName();
            case 1: return st.getLastName();
            case 2: return st.getEnrollmentYear();
        }
        throw new RuntimeException(String.valueOf(columnIndex));
    }
}
