import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class StudentTableModel extends AbstractTableModel {
    private String[] header = {"First name", "Last name", "Year"};
    private List<Student> students;

    public StudentTableModel(){
        students = new ArrayList<>();
        students.add(new Student("F", "l", 1));
    }

    public void setStudents(List<Student> students){
        this.students = students;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public String getColumnName(int column){
        return header[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return false;
    }


    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int i, int j) {
        Student student = students.get(i);
        switch (j){
            case 0: return student.getFirstName();
            case 1: return student.getLastName();
            case 2: return student.getYear();
        }
        throw new RuntimeException("Couldn't get value");
    }
}
