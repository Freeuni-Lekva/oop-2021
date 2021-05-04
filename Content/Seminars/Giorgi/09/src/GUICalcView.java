import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUICalcView extends BasicCalcView {

    public static final int CALC_WIDTH = 300;
    public static final int CALC_HEIGHT = 300;

    private JFrame frame;
    private final JTextField inputField;
    private String operator = "";
    private double leftOperand = 0;
    private boolean clear = false;

    public GUICalcView() {
        frame = new JFrame();
        // set general options
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        int x = (screenSize.width - CALC_WIDTH) / 2;
        int y = (screenSize.height - CALC_HEIGHT) / 2;

        frame.setSize(CALC_WIDTH, CALC_HEIGHT);
        frame.setLocation(x, y);

        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set main layout
        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setHgap(10);
        borderLayout.setVgap(10);
        frame.setLayout(borderLayout);

        // create input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        inputField = new JTextField();
        inputField.setFont(new Font("Times New Roman", Font.BOLD, 20));
        inputField.setEditable(false);
        inputPanel.add(inputField);

        // create value button panel
        JPanel valueButtonPanel = new JPanel(getGridLayout(4, 3));
        for (int i = 0; i <= 9; ++i) {
            valueButtonPanel.add(getCalcButton("" + i));
        }
        valueButtonPanel.add(getCalcButton("."));
        valueButtonPanel.add(getCalcButton("="));

        // create command button panel
        JPanel commandButtonPanel = new JPanel(getGridLayout(4, 1));
        String[] commands = {"+", "-", "*", "/"};
        for (String c : commands) {
            commandButtonPanel.add(getCalcButton(c));
        }

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(valueButtonPanel, BorderLayout.CENTER);
        frame.add(commandButtonPanel, BorderLayout.EAST);
    }

    private GridLayout getGridLayout(int rows, int cols) {
        GridLayout gridLayout = new GridLayout(rows, cols);
        gridLayout.setHgap(5);
        gridLayout.setVgap(5);
        return gridLayout;
    }

    private JButton getCalcButton(String text) {
        JButton button = new JButton(text);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                fireInputListener(e.getActionCommand().charAt(0));
            }
        });
        return button;
    }

    @Override
    public void show() {
        frame.setVisible(true);
    }

    @Override
    public void displayChanged(String display) {
        inputField.setText(display);
    }
}