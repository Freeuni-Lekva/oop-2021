import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SwingView {

    private JFrame frame;
    private StudentStore store;
    private StudentTableModel model;

    public SwingView(StudentStore store){
        this.store = store;
        frame = new JFrame();
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel createPanel = new JPanel(new GridLayout(2, 4));
        JTextField firstNameTF = new JTextField();
        JTextField lastNameTF = new JTextField();
        JTextField yearTF = new JTextField();
        JButton create = new JButton("Add");

        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String firstName = firstNameTF.getText();
                String lastName = lastNameTF.getText();
                String year = yearTF.getText();
                store.addStudent(new Student(firstName, lastName, Integer.valueOf(year)));
                model.setStudents(store.filter(new NoOPFilter()));
            }
        });
        JTextField firstNameFTF = new JTextField();
        firstNameFTF.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {

            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                if (firstNameFTF.getText().length() > 0){
                    Filter filter = new FirstNameFilter(firstNameFTF.getText());
                    model.setStudents(store.filter(filter));
                }
            }
        });
        JTextField lastNameFTF = new JTextField();
        JTextField yearFTF = new JTextField();
        JButton filter = new JButton("Filter");
        filter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AndFilter andFilter = new AndFilter();
                if (firstNameFTF.getText().length() > 0){
                    andFilter.addFilter(new FirstNameFilter(firstNameFTF.getText()));
                }
                if (lastNameFTF.getText().length() > 0){
                    andFilter.addFilter(new LastNameFilter(lastNameFTF.getText()));
                }
                if (yearFTF.getText().length() > 0){
                    andFilter.addFilter(new YearFilter(Integer.valueOf(yearFTF.getText())));
                }
                model.setStudents(store.filter(andFilter));
            }
        });

        createPanel.add(firstNameTF);
        createPanel.add(lastNameTF);
        createPanel.add(yearTF);
        createPanel.add(create);
        createPanel.add(firstNameFTF);
        createPanel.add(lastNameFTF);
        createPanel.add(yearFTF);
        createPanel.add(filter);

        mainPanel.add(createPanel, BorderLayout.NORTH);

        JTable table = new JTable();
        model = new StudentTableModel();
        table.setModel(model);
        JPanel tablePanel = new JPanel(new BorderLayout());

        tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);
        tablePanel.add(table, BorderLayout.CENTER);

        mainPanel.add(tablePanel);

        frame.setContentPane(mainPanel);
        frame.setSize(500, 800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void show(){
        frame.setVisible(true);
    }
}
