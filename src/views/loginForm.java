package views;

import com.formdev.flatlaf.fonts.jetbrains_mono.FlatJetBrainsMonoFont;
import com.password4j.BcryptFunction;
import com.password4j.Password;
import com.password4j.types.Bcrypt;
import models.Employee;
import models.EmployeeService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class loginForm {
    //COMPONENTS
    private JPanel loginPanel;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JLabel loginLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton logInButton;

    //SERVICES
    private final EmployeeService empService = new EmployeeService();

    public loginForm(JFrame frame) {
        setUpFont();
        setUpButtonResponsivity();
        setUpEnterKeyListener();
        setUpButtonBehaviour();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        JFrame frame = new JFrame("loginForm");
        frame.setContentPane(new loginForm(frame).loginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    private void setUpFont(){
        FlatJetBrainsMonoFont.install();//custom font, 3 sizes
        Font customFont20 = new Font("JetBrains Mono", Font.PLAIN, 20);
        Font customFont18 = new Font("JetBrains Mono", Font.PLAIN, 18);
        Font customFont16 = new Font("JetBrains Mono", Font.PLAIN, 16);
        loginLabel.setFont(customFont20);
        usernameLabel.setFont(customFont16);
        passwordLabel.setFont(customFont16);
        usernameTextField.setFont(customFont18);
        passwordField.setFont(customFont18);
        logInButton.setFont(customFont18);
    }

    private void setUpButtonResponsivity() {
        MouseListener buttonResponse = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                JButton source = (JButton) e.getSource();
                source.setBackground(new Color(53, 116, 240));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                JButton source = (JButton) e.getSource();
                source.setBackground(new Color(55, 57, 60));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                JButton source = (JButton) e.getSource();
                source.setBackground(new Color(50, 53, 55));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JButton source = (JButton) e.getSource();
                source.setBackground(new Color(55, 57, 60));
            }
        };

        logInButton.addMouseListener(buttonResponse);

    }

    private void setUpEnterKeyListener(){
        KeyListener enter = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getExtendedKeyCode() == KeyEvent.getExtendedKeyCodeForChar(KeyEvent.VK_ENTER)) logInButton.doClick();
            }
        };
        usernameTextField.addKeyListener(enter);
        passwordField.addKeyListener(enter);
    }

    private void setUpButtonBehaviour(){
        logInButton.addActionListener(new ActionListener() {//login button pressed
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = usernameTextField.getText();
                char[] password = passwordField.getPassword();//password input from user

                Employee employee = empService.findOneEmployee("username", username);//find employee by username


                if (employee == null) {//if no fitting document was found
                    JOptionPane.showMessageDialog(null, "User does not exist!");
                    usernameTextField.setText("");//empties the text field and
                    usernameTextField.grabFocus();//focuses on it
                    return;
                }

                String truePassword = employee.getPassword();//password in db
                BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);
                boolean verified = Password.check(String.valueOf(password), employee.getPassword())
                        .with(bcrypt);//compare password in textField through hash function


                if (!verified) {//if passwords are not the same
                    JOptionPane.showMessageDialog(null, "Password is incorrect!");
                    passwordField.setText("");//empties the password field and
                    passwordField.grabFocus();//focuses on it
                } else {//if user exists and password is correct
                    Arrays.fill(password, (char) 0);//burn the evidence

                    //open next form
                    switch (employee.getRole()) {
                        case "employee":
                            //open leaveRequestCreation form
                            employeeForm.main(employee.toStringArray());
                            break;
                        case "manager":
                            //open leaveRequestReview/employeeList/leaveRequestList form
                            managerForm.main(employee.toStringArray());
                            break;
                        case "admin":
                            //open input/employeeList/leaveRequestList form
                            adminForm.main(null);
                            break;
                        default:
                            throw new IllegalArgumentException("Illegal role found in database: " + employee.getRole() + " employee, manager or admin expected!");
                    }
                }
            }
        });
    }

}

