package src.renderer;

import src.model.Zakaznik;

import javax.swing.*;
import java.awt.*;

public class ZakaznikRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value != null) {
            Zakaznik z = (Zakaznik) value;
            label.setText(z.getPrijmeni() + " " + z.getJmeno() + ", " + z.getUsername());
            list.setEnabled(true);
        }

        return label;
    }


}