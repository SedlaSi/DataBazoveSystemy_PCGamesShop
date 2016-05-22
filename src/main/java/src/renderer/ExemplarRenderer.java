package src.renderer;

import src.model.Exemplar;

import javax.swing.*;
import java.awt.*;

public class ExemplarRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value != null) {
            Exemplar e = (Exemplar) value;
            label.setText(e.getHra().getNazev() + "     " + e.getPlatforma().getNazev() + ", cena:  " + e.getCena() + " Kč" + ",    police: " + e.getHra().getPolice().getNazev() + ",      číslo produktu: " + e.getId());
            list.setEnabled(true);
        } else {
            label.setText("Nebyli nalezené žádné hry");
            list.setEnabled(false);
        }

        return label;
    }


}