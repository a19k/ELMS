package views;

import com.formdev.flatlaf.fonts.jetbrains_mono.FlatJetBrainsMonoFont;
import models.Employee;
import models.EmployeeService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class loginForm {
    private JPanel loginPanel;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JLabel loginLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton logInButton;

    public loginForm(JFrame frame){;
        FlatJetBrainsMonoFont.install();//custom font, 3 sizes
        Font customFont20 = new Font("JetBrains Mono", Font.PLAIN,20);
        Font customFont18 = new Font("JetBrains Mono", Font.PLAIN,18);
        Font customFont16 = new Font("JetBrains Mono", Font.PLAIN,16);
        loginLabel.setFont(customFont20);
        usernameLabel.setFont(customFont16);
        passwordLabel.setFont(customFont16);
        usernameTextField.setFont(customFont18);
        passwordField.setFont(customFont18);
        logInButton.setFont(customFont18);

        usernameTextField.setBorder(new EmptyBorder(2,5,2,5));//eliminates weird border and
        passwordField.setBorder(new EmptyBorder(2,5,2,5));//separates the text from the border with insets

        EmployeeService empService = new EmployeeService();//service to Employees collection

        logInButton.addActionListener(new ActionListener() {//login button pressed
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = usernameTextField.getText();
                char[] password = passwordField.getPassword();//password input from user

                Employee employee = empService.findOneEmployee(username);//find employee by username


                if(employee==null){//if no fitting document was found
                    JOptionPane.showMessageDialog(null,"User does not exist!");
                    usernameTextField.setText("");//empties the text field and
                    usernameTextField.grabFocus();//focuses on it
                    return;
                }

                String truePassword = employee.getPassword();//password in db
                if(!truePassword.equals(String.valueOf(password))) {//if passwords are not the same
                    JOptionPane.showMessageDialog(null, "Password is incorrect!");
                    passwordField.setText("");//empties the password field and
                    passwordField.grabFocus();//focuses on it
                }
                else{//if user exists and password is correct
                    Arrays.fill(password, (char) 0);//burn the evidence

                    //open next form
                    switch (employee.getRole()) {
                        case "employee":
                            //open leaveRequestCreation form
                            employeeForm.main(null);
                            break;
                        case "manager":
                            //open leaveRequestReview/employeeList/leaveRequestList form
                            managerForm.main(null);
                            break;
                        case "admin":
                            //open input/employeeList/leaveRequestList form
                            adminForm.main(null);
                            break;
                        default:throw new IllegalArgumentException("Illegal role found in database: "+employee.getRole()+" employee, manager or admin expected!");
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("loginForm");
        frame.setContentPane(new loginForm(frame).loginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
