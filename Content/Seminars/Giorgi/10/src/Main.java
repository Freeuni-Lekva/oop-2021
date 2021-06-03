import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main extends JFrame {
    // public StudentStore store;
    public StudentModel model;
    private JLabel statusL;
    private BlockingQueue<Student> studentQueue;

    public Main(StudentDao store) {
        // this.store = store;
        model = new StudentModel(store);

        studentQueue = new LinkedBlockingQueue<>();
        Thread inserter = new Thread(() -> {
            try {
                Student st = studentQueue.take();
                model.add(st);
                SwingUtilities.invokeLater(() -> {
                    statusL.setText(st.getFirstName() + " was inserted");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        inserter.start();

        JPanel mainPanel = new JPanel(new BorderLayout());


        JTable table = new JTable();
        table.getTableHeader().setBackground(Color.white);
        table.setDefaultRenderer(DeleteElem.class, new DeleteButtonRenderer());
        table.setModel(model);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);
        tablePanel.add(table, BorderLayout.CENTER);

        JPanel actionP = new JPanel(new GridLayout(3, 1));
        actionP.add(createStatusPanel());
        actionP.add(createAddPanel());
        actionP.add(createSearchPanel());
        mainPanel.add(actionP, BorderLayout.NORTH);
        mainPanel.add(tablePanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        setSize(500, 1500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private Component createStatusPanel() {
        JPanel statusP = new JPanel();
        statusL = new JLabel("STATUS");
        statusP.add(statusL);
        return statusP;
    }

    private JPanel createAddPanel() {
        JPanel create = new JPanel(new GridLayout(1, 4));
        JTextField firstName = new JTextField();
        JTextField lastName = new JTextField();
        JTextField enrollmentYear = new JTextField();
        JButton add = new JButton("Add");
        create.add(firstName);
        create.add(lastName);
        create.add(enrollmentYear);
        create.add(add);

        firstName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student st = new Student(
                        firstName.getText(),
                        lastName.getText(),
                        Integer.valueOf(enrollmentYear.getText()));
                studentQueue.add(st);
            }
        });
        return create;
    }

    private JPanel createSearchPanel() {
        JPanel search = new JPanel(new GridLayout(1, 4));
        JTextField firstName = new JTextField();
        JTextField lastName = new JTextField();
        JTextField enrollmentYear = new JTextField();
        JButton searchB = new JButton("Search");
        search.add(firstName);
        search.add(lastName);
        search.add(enrollmentYear);
        search.add(searchB);

        searchB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AndFilter f = new AndFilter();
                if (firstName.getText().length() > 0) {
                    f.add(new FirstNameFilter(firstName.getText()));
                }
                if (lastName.getText().length() > 0) {
                    f.add(new LastNameFilter(lastName.getText()));
                }
                if (enrollmentYear.getText().length() > 0) {
                    f.add(new EnrollmentYearFilter(Integer.valueOf(enrollmentYear.getText())));
                }
                model.applyFilter(f);
            }
        });
        return search;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException, NamingException {
        DataSource ds = new MysqlConnectionPoolDataSource();
        Class.forName("com.mysql.cj.jdbc.Driver");
        // System.setProperty("java.naming.factory.initial", InitialContextFactory.class.getName());
//        Context ctx = new InitialContext();
//        ctx.bind("storage/conn", conn);
        // StudentDao store = new ArrayListStudentDao();
        StudentDao store = new SqlStudentDao(ConnectionManager.getInstance());
        // store.add(new Student("foo", "bar", 2002));
        // store.add(new Student("dev", "null", 2003));
        Main m = new Main(store);
    }
}




