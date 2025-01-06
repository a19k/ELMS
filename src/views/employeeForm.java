package views;

import javax.swing.*;

public class employeeForm {

    public JPanel employeePanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("employeeForm");
        frame.setContentPane(new employeeForm().employeePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
