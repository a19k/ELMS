package views;

import javax.swing.*;

public class adminForm {
    private JPanel adminPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("adminForm");
        frame.setContentPane(new adminForm().adminPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
