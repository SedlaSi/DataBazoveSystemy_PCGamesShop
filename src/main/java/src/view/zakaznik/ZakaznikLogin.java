package src.view.zakaznik;

/**
 * Created by root on 14.4.16.
 */
import javax.swing.*;
import java.awt.*;

public class ZakaznikLogin extends JFrame{

    public static void main(String [] args){
       /* javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });*/
        new ZakaznikLogin();

    }

    public ZakaznikLogin(){
        this.setSize(400,400);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        int xPos = (dim.width /2) - (this.getWidth()/2);
        int yPos = (dim.height /2) - (this.getHeight()/2);
        this.setLocation(xPos,yPos);
        this.setResizable(false);
        this.setTitle("Login Screen");

        JPanel mainPanel = new JPanel();

        JLabel labelA = new JLabel("Welcome");
        labelA.setText("PRDEL");
        labelA.setToolTipText("toto je prdel");
        mainPanel.add(labelA);

        JButton button = new JButton();
        button.setText("TLACITKO");
        mainPanel.add(button);

        JTextField textField = new JTextField("default text");
        textField.setColumns(20);
        mainPanel.add(textField);

        JTextArea textArea = new JTextArea();
        textArea.setColumns(15);
        textArea.setRows(10);
        textArea.setText("ldflajfaldkjflakdjfbaljdfblaskjbflakjsbflaskjfbalksjfbalskdjfbalskfjbasldfkb");
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        mainPanel.add(textArea);

        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainPanel.add(scrollPane);

        this.add(mainPanel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textField.requestFocus();

    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = frame.getContentPane();

        //Add the ubiquitous "Hello World" label.
       /* JLabel label = new JLabel("Hello World");
        container.add(label);*/


        JPasswordField passwordField = new JPasswordField();
        container.add(passwordField);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

}
