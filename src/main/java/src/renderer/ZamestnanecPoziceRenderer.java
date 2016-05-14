package src.renderer;

import src.model.Pozice;

import javax.swing.*;
import java.awt.*;

public class ZamestnanecPoziceRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value != null) {
            Pozice pozice = (Pozice) value;

            label.setHorizontalTextPosition(JLabel.RIGHT);
            label.setText(pozice.getNazev());
        }
        return label;
    }


}