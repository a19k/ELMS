package views;

import com.formdev.flatlaf.fonts.jetbrains_mono.FlatJetBrainsMonoFont;
import models.Employee;
import models.EmployeeService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class loginForm {
    private JPanel loginPanel;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JLabel loginLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton logInButton;

    public loginForm(){;
        FlatJetBrainsMonoFont.install();
        Font customFont20 = new Font("JetBrains Mono", Font.PLAIN,20);
        Font customFont18 = new Font("JetBrains Mono", Font.PLAIN,18);
        Font customFont16 = new Font("JetBrains Mono", Font.PLAIN,16);
        loginLabel.setFont(customFont20);
        usernameLabel.setFont(customFont16);
        passwordLabel.setFont(customFont16);
        usernameTextField.setFont(customFont18);
        passwordField.setFont(customFont18);
        logInButton.setFont(customFont18);

        usernameTextField.setBorder(new EmptyBorder(2,5,2,5));
        passwordField.setBorder(new EmptyBorder(2,5,2,5));

        EmployeeService empService = new EmployeeService();

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = usernameTextField.getText();
                char[] password = passwordField.getPassword();

                //List<Employee> employees = empService.getAllEmployees();



            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("loginForm");
        frame.setContentPane(new loginForm().loginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
