import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    StudentTableModel model;
    StudentStore store;

    public Main(String title, StudentStore store) {
        super(title);
        this.store = store;
        model = new StudentTableModel();
        model.setStudents(store.filter(new NoOpFilter()));

        JPanel mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);

        JPanel createPanel = new JPanel(new GridLayout(2, 4));
        JTextField firstName = new JTextField();
        JTextField lastName = new JTextField();
        JTextField enrollmentYear = new JTextField();
        JButton create = new JButton("Add");
        JTextField firstNameF = new JTextField();
        JTextField lastNameF = new JTextField();
        JTextField enrollmentYearF = new JTextField();
        JButton filter = new JButton("Filter");

        createPanel.add(firstName);
        createPanel.add(lastName);
        createPanel.add(enrollmentYear);
        createPanel.add(create);
        createPanel.add(firstNameF);
        createPanel.add(lastNameF);
        createPanel.add(enrollmentYearF);
        createPanel.add(filter);

        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                store.add(new Student(firstName.getText(), lastName.getText(), Integer.valueOf(enrollmentYear.getText())));
                model.setStudents(store.filter(new NoOpFilter()));
            }
        });

        ActionListener filterListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AndFilter f = new AndFilter();
                if (firstNameF.getText().length() > 0) {
                    f.add(new FirstNameFilter(firstNameF.getText()));
                }
                if (lastNameF.getText().length() > 0) {
                    f.add(new LastNameFilter(lastNameF.getText()));
                }
                if (enrollmentYearF.getText().length() > 0) {
                    f.add(new EnrollmentYearFilter(Integer.valueOf(enrollmentYearF.getText())));
                }
                model.setStudents(store.filter(f));
            }
        };

        filter.addActionListener(filterListener);

        mainPanel.add(createPanel, BorderLayout.NORTH);

        JPanel tablePanel = new JPanel(new BorderLayout());
        mainPanel.add(tablePanel, BorderLayout.CENTER);

        JTable table = new JTable();
        table.setModel(model);
        table.getTableHeader().setBackground(Color.WHITE);

        tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);
        tablePanel.add(table, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        Main main = new Main("სტუდენტები", new StudentStore());
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setSize(500, 800);
        main.setVisible(true);
    }
}