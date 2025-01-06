package views;

import javax.swing.*;

public class managerForm {
    private JPanel managerPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("ManagerForm");
        frame.setContentPane(new managerForm().managerPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
