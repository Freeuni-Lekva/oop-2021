import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteButtonRenderer extends JButton implements TableCellRenderer {
    private DeleteElem elem;

    public DeleteButtonRenderer() {
        super();
        setOpaque(true);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("-------");
                elem.getModel().remove(elem.getId());
            }
        });
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        elem = (DeleteElem)value;
        setText("-");
        return this;
    }
}
