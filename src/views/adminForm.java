package views;

import com.formdev.flatlaf.fonts.jetbrains_mono.FlatJetBrainsMonoFont;
import com.password4j.BcryptFunction;
import com.password4j.Password;
import com.password4j.types.Bcrypt;
import models.Employee;
import models.EmployeeService;
import models.LeaveRequest;
import models.LeaveRequestService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Objects;

public class adminForm {
    //INPUT components
    //layout
    private JPanel adminPanel;
    private JTabbedPane adminTabbedPane;
    private JPanel tabInput;
    private JPanel tabEmployees;
    private JPanel tabLeaveRequests;

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
    private JComboBox manager_idCombo;
    private JRadioButton employeeRadio;
    private JRadioButton managerRadio;
    private JRadioButton adminRadio;
    private ButtonGroup roleRadioGroup;
    private JSpinner leaveBalanceSpinner;

    //save
    private JButton saveButton;

    //EMPLOYEES components
    private JTable employeesTable;
    private JButton editButtonEmp;
    private JButton removeButtonEmp;

    //LEAVE REQUESTS components
    private JTable leaveRequestsTable;
    private JButton editButtonLR;
    private JButton removeButtonLR;

    //SERVICES
    private final EmployeeService empService = new EmployeeService();
    private final LeaveRequestService lrService = new LeaveRequestService();

    //LISTS
    private List<Employee> employeesList;
    private List<LeaveRequest> leaveRequestList;

    //TABLE MODELS
    private DefaultTableModel employeesTableModel;
    private DefaultTableModel leaveRequestsTableModel;
    private DefaultComboBoxModel<String> comboBoxModel;


    public adminForm(){
        setUpFont();
        setUpButtonResponsivity();
        setUpRadioButtons();
        setUp_employeesTable();
        setUp_leaveRequestsTable();
        setUpButtonBehaviour();
        setUpManagerIDCombo();

    }

    public static void main(String[] args) {

        try {//System Look and Feel set for the window, default is very old
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception ignored){}

        JFrame frame = new JFrame("inputForm");
        frame.setContentPane(new adminForm().adminPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    private void setUpFont(){
        FlatJetBrainsMonoFont.install();
        Font customFont20 = new Font("JetBrains Mono", Font.PLAIN,20);
        Font customFont18 = new Font("JetBrains Mono", Font.PLAIN,18);
        Font customFont16 = new Font("JetBrains Mono", Font.PLAIN,16);
        Font customFont10 = new Font("JetBrains Mono", Font.BOLD,10);

        adminTabbedPane.setFont(customFont20);
        usernameTextField.setFont(customFont18);
        passwordTextField.setFont(customFont18);
        nameTextField.setFont(customFont18);
        manager_idCombo.setFont(customFont18);
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

        editButtonEmp.setFont(customFont18);
        removeButtonEmp.setFont(customFont18);
        editButtonLR.setFont(customFont18);
        removeButtonLR.setFont(customFont18);

        employeesTable.setFont(customFont10);
        employeesTable.getTableHeader().setFont(customFont10);
        leaveRequestsTable.setFont(customFont10);
        leaveRequestsTable.getTableHeader().setFont(customFont10);
    }

    private void setUpButtonResponsivity(){
        MouseListener buttonResponse = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                JButton source = (JButton) e.getSource();
                source.setBackground(new Color(53,116,240));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                JButton source = (JButton) e.getSource();
                source.setBackground(new Color(55,57,60));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                JButton source = (JButton) e.getSource();
                source.setBackground(new Color(50,53,55));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JButton source = (JButton) e.getSource();
                source.setBackground(new Color(55,57,60));
            }
        };

        saveButton.addMouseListener(buttonResponse);
        editButtonEmp.addMouseListener(buttonResponse);
        removeButtonEmp.addMouseListener(buttonResponse);
        editButtonLR.addMouseListener(buttonResponse);
        removeButtonLR.addMouseListener(buttonResponse);
    }

    private void setUpButtonBehaviour(){
        //saving is complicated...
        //NEW EMPLOYEE CREATION
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isInvalid = false;
                boolean isAdmin = false;

                //empty textfield or username not available
                if (usernameTextField.getText().isEmpty() || empService.findOneEmployee("username", usernameTextField.getText())!=null) {
                    usernameTextField.setText("Enter valid username!");
                    isInvalid = true;
                }
                //empty textfield
                if (passwordTextField.getText().isEmpty()) {
                    passwordTextField.setText("Enter valid password!");
                    isInvalid = true;
                }
                //empty textfield
                if (nameTextField.getText().isEmpty()) {
                    nameTextField.setText("Enter valid name!");
                    isInvalid = true;
                }

                //no role selected
                if (roleRadioGroup.getSelection()==null ){
                    isInvalid = true;
                }
                else {//with admin role, checking for manager_id won't be necessary(admin doesn't need it)
                    isAdmin = Objects.equals(roleRadioGroup.getSelection().getActionCommand(), Employee.ROLE_ADMIN);
                }

                //value is null for whatever reason or is not Integer
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
                String manager_id = (String) manager_idCombo.getSelectedItem();
                int leaveBalance = (int) leaveBalanceSpinner.getModel().getValue();

                BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);
                String hashedPassword = Password.hash(password).with(bcrypt).getResult();

                empService.addEmployee(new Employee(username,hashedPassword,name,role,manager_id,leaveBalance));
                setUp_employeesTable();
            }
        });

        //tabEmployees
        //editButtonEmp.addActionListener(e -> adminTabbedPane.setSelectedComponent(tabEditEmp));
        removeButtonEmp.addActionListener(e -> employeesTableModel.removeRow(employeesTable.getSelectedRow()));

        //tabLeaveRequests
        //editButtonEmp.addActionListener(e -> adminTabbedPane.setSelectedComponent(tabEditLR);
        removeButtonLR.addActionListener(e -> leaveRequestsTableModel.removeRow(leaveRequestsTable.getSelectedRow()));
    }

    private void setUp_employeesTable(){
        employeesList = empService.getAllEmployees();//refresh the employee list

        //table model -> non-editable
        employeesTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        //setting header
        employeesTableModel.setColumnIdentifiers(new String[]{"ID", "Username","Password","Name","Role","Manager ID","Leave Balance"});

        //setting data
        for (int i = 0; i<employeesList.size(); i++)
            employeesTableModel.addRow(employeesList.get(i).toStringArray());//add every employee to model to separate row

        employeesTable.setModel(employeesTableModel);//assign the model to the table component

        //additional adjustions
        employeesTable.setRowHeight(30);
        employeesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//can select only one row at a time


        //FONT SET IN setUpFont()
    }

    private void setUp_leaveRequestsTable(){
        leaveRequestList = lrService.getAllLeaveRequests();//refresh the leave requests list

        //table model -> non-editable
        leaveRequestsTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        //setting header
        leaveRequestsTableModel.setColumnIdentifiers(new String[]{"1","ne znam","neki razlog"});

        //setting data
        //for each leave request add to table model
        leaveRequestsTableModel.addRow(new String[]{"1","ne znam","neki razlog"});

        leaveRequestsTable.setModel(leaveRequestsTableModel);//assign the model to the table component

        //additional adjustions
        leaveRequestsTable.setRowHeight(30);
        leaveRequestsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        //FONT SET IN setUpFont()
    }

    private void setUpRadioButtons(){
        adminRadio.addActionListener(e -> {//hide manager_id input field when admin selected
            manager_idCombo.addItem("");
            manager_idCombo.setSelectedItem("");
            manager_idCombo.setVisible(false);
        });
        employeeRadio.addActionListener(e -> {
            manager_idCombo.removeItem("");
            manager_idCombo.setVisible(true);
        });//redraw when employee selected
        managerRadio.addActionListener(e -> {
            manager_idCombo.removeItem("");
            manager_idCombo.setVisible(true);
        });//redraw when manager selected

        //FONT SET IN setUpFont()
    }

    private void setUpManagerIDCombo(){

        //initialize combo box model
        comboBoxModel = new DefaultComboBoxModel<>();

        //put all manager's IDs in model
        for (Employee employee : employeesList)
            if (Objects.equals(employee.getRole(), Employee.ROLE_MANAGER)) comboBoxModel.addElement(employee.getId());

        manager_idCombo.setModel(comboBoxModel);
    }

}
