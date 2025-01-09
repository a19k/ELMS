package views;

import com.formdev.flatlaf.fonts.jetbrains_mono.FlatJetBrainsMonoFont;
import com.password4j.BcryptFunction;
import com.password4j.Password;
import com.password4j.types.Bcrypt;
import models.Employee;
import models.EmployeeService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

public class adminForm {
    //layout components
    private JPanel adminPanel;
    private JTabbedPane adminTabbedPane;
    private JPanel tabInput;

    //labels
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel nameLabel;
    private JLabel roleLabel;
    private JLabel managerIDLabel;
    private JLabel leaveBalanceLabel;

    //interactive components
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JTextField nameTextField;
    private JTextField manager_idTextField;
    private JRadioButton employeeRadio;
    private JRadioButton managerRadio;
    private JRadioButton adminRadio;
    private ButtonGroup roleRadioGroup;
    private JSpinner leaveBalanceSpinner;

    //save
    private JButton saveButton;

    public adminForm(){
        FlatJetBrainsMonoFont.install();
        Font customFont20 = new Font("JetBrains Mono", Font.PLAIN,20);
        Font customFont18 = new Font("JetBrains Mono", Font.PLAIN,18);
        Font customFont16 = new Font("JetBrains Mono", Font.PLAIN,16);

        adminTabbedPane.setFont(customFont20);
        usernameTextField.setFont(customFont18);
        passwordTextField.setFont(customFont18);
        nameTextField.setFont(customFont18);
        manager_idTextField.setFont(customFont18);
        employeeRadio.setFont(customFont16);
        managerRadio.setFont(customFont16);
        adminRadio.setFont(customFont16);
        leaveBalanceSpinner.setFont(customFont18);

        usernameLabel.setFont(customFont16);
        passwordLabel.setFont(customFont16);
        nameLabel.setFont(customFont16);
        roleLabel.setFont(customFont16);
        managerIDLabel.setFont(customFont16);
        leaveBalanceLabel.setFont(customFont16);

        saveButton.setFont(customFont18);

        EmployeeService empService = new EmployeeService();
        List<Employee> employees = empService.getAllEmployees();

        adminRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager_idTextField.setText("");
                manager_idTextField.setVisible(false);
            }
        });
        employeeRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager_idTextField.setVisible(true);
            }
        });
        managerRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager_idTextField.setVisible(true);
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isInvalid = false;
                boolean isAdmin = false;

                if (usernameTextField.getText().isEmpty() || empService.findOneEmployee("username", usernameTextField.getText())!=null) {
                    usernameTextField.setText("Enter valid username!");
                    isInvalid = true;
                }

                if (passwordTextField.getText().isEmpty()) {
                    passwordTextField.setText("Enter valid password!");
                    isInvalid = true;
                }

                if (nameTextField.getText().isEmpty()) {
                    nameTextField.setText("Enter valid name!");
                    isInvalid = true;
                }

                if (roleRadioGroup.getSelection()==null ){
                    isInvalid = true;
                }
                else {
                    isAdmin = Objects.equals(roleRadioGroup.getSelection().getActionCommand(), Employee.ROLE_ADMIN);
                }

                if(!isAdmin) {
                    if (manager_idTextField.getText().isEmpty()) {
                        manager_idTextField.setText("Enter valid manager ID");
                        isInvalid = true;
                    }
                    Employee manager = empService.findOneEmployee("_id", manager_idTextField.getText());
                    if (manager == null || !Objects.equals(manager.getRole(), Employee.ROLE_MANAGER)) {
                        manager_idTextField.setText("Enter valid manager ID");
                        isInvalid = true;
                    }
                }

                if (leaveBalanceSpinner.getValue()==null || !(leaveBalanceSpinner.getValue() instanceof Integer) ){
                    isInvalid=true;
                }

                if (isInvalid) {
                    JOptionPane.showMessageDialog(null,"Invalid input");
                    return;
                }

                String username = usernameTextField.getText();
                String password = passwordTextField.getText();
                String name = nameTextField.getText();
                String role = roleRadioGroup.getSelection().getActionCommand();
                String manager_id = manager_idTextField.getText();
                int leaveBalance = (int) leaveBalanceSpinner.getModel().getValue();

                BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);
                String hashedPassword = Password.hash(password).with(bcrypt).getResult();

                empService.addEmployee(new Employee(username,hashedPassword,name,role,manager_id,leaveBalance));
            }
        });
    /*

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setDataVector(new String[][]{} ,new String[]{"Subject", "Grade"});
        gradesTable.setModel(tableModel);
        gradesTable.setRowHeight(30);
        gradesTable.getTableHeader().setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
        gradesTable.getTableHeader().setBackground(new Color(220,220,220));
        gradesPane.setBorder(new EmptyBorder(0,0,0,0));
        gradesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        addAGradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.addRow(new String[]{"", ""});
                gradesTable.changeSelection(gradesTable.getRowCount()-1,1,false,false);
                gradesTable.editCellAt(gradesTable.getRowCount()-1,0);
                gradesTable.grabFocus();
            }
        });
        removeAGradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.removeRow(gradesTable.getSelectedRow());
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gradesTable.editCellAt(gradesTable.getSelectedRow(),gradesTable.getSelectedColumn());
                gradesTable.grabFocus();
            }
        });*/
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception ignored){}

        EmployeeService empService = new EmployeeService();
        JFrame frame = new JFrame("inputForm");
        frame.setContentPane(new adminForm().adminPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

}
