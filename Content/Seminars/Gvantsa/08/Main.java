import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static int count = 0;
    public int i;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(0, 1));

        JButton button = new MyButton("Click!");
        JLabel label = new JLabel("Count: 0");

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                count++;

                label.setText("Count: " + count);
            }
        });

        panel.add(button);
        panel.add(label);

        frame.setContentPane(panel);

        frame.setSize(500, 400);
        frame.setName("My window");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static class MyButton extends JButton {
        public MyButton(String text) {
            super(text);
            setForeground(Color.CYAN);
            setBackground(Color.WHITE);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawOval(5, 5, this.getWidth() - 10, this.getHeight() - 10);
        }
    }
}
