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
import java.util.*;
import java.util.List;

public class adminForm {
    //INPUT components
    //layout
    private JPanel adminPanel;
    private JTabbedPane adminTabbedPane;
    private JPanel tabInput;
    private JPanel tabEmployees;
    private JPanel tabLeaveRequests;

    //INPUT
    //labels
    private JLabel inputUsernameLabel;
    private JLabel inputPasswordLabel;
    private JLabel inputNameLabel;
    private JLabel inputRoleLabel;
    private JLabel inputManagerIDLabel;
    private JLabel inputLeaveBalanceLabel;

    //interactive components
    private JTextField inputUsernameTextField;
    private JTextField inputPasswordTextField;
    private JTextField inputNameTextField;
    private JComboBox inputManagerIDCombo;
    private JRadioButton inputEmployeeRadio;
    private JRadioButton inputManagerRadio;
    private JRadioButton inputAdminRadio;
    private ButtonGroup roleRadioGroup;
    private JSpinner inputLeaveBalanceSpinner;

    //save
    private JButton inputSaveButton;

    //EMPLOYEES components
    private JTable employeesTable;
    private JButton empEditButton;
    private JButton empRemoveButton;

    //LEAVE REQUESTS components
    private JTable leaveRequestsTable;
    private JButton lrEditButton;
    private JButton lrRemoveButton;

    //UPDATE EMPLOYEE components
    private JPanel tabUpdateEmp;
    private JLabel updateEmpUsernameLabel;
    private JLabel updateEmpPasswordLabel;
    private JLabel updateEmpNameLabel;
    private JLabel updateEmpManagerIDLabel;
    private JLabel updateEmpLeaveBalanceLabel;

    private JLabel updateEmpUsernameValueLabel;
    private JTextField updateEmpPasswordTextField;
    private JTextField updateEmpNameTextField;
    private JComboBox updateEmpManagerIDCombo;
    private JCheckBox updateEmpSpinnerCheck;
    private JSpinner updateEmpLeaveBalanceSpinner;
    private JButton updateEmpSaveButton;

    //UPDATE LEAVE REQUEST components
    private JPanel tabUpdateLR;
    private JLabel updateLRIDLabel;
    private JLabel updateLRReasonLabel;
    private JLabel updateLRStartDateLabel;
    private JLabel updateLREndDateLabel;
    private JLabel updateLRDayLabel;
    private JLabel updateLRMonthLabel;
    private JLabel updateLRYearLabel;
    private JLabel updateLRTypeLabel;
    private JLabel updateLRStatusLabel;

    private JTextArea updateLRReasonTextArea;
    private JComboBox updateLRStartDayCombo;
    private JComboBox updateLRStartMonthCombo;
    private JComboBox updateLRStartYearCombo;
    private JComboBox updateLREndDayCombo;
    private JComboBox updateLREndMonthCombo;
    private JComboBox updateLREndYearCombo;
    private JButton updateLRSaveButton;
    private JComboBox updateLRTypeCombo;
    private JComboBox updateLRStatusCombo;

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
    private DefaultComboBoxModel<String> comboBoxModel_update;


    public adminForm(){
        setUpFont();
        setUpButtonResponsivity();
        setUpRadioBehaviour();
        setUp_employeesTable();
        setUp_leaveRequestsTable();
        setUpButtonBehaviour();
        setUpManagerIDCombo();
        setUpDaysInMonth();

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
        inputUsernameTextField.setFont(customFont18);
        inputPasswordTextField.setFont(customFont18);
        inputNameTextField.setFont(customFont18);
        inputManagerIDCombo.setFont(customFont18);
        inputEmployeeRadio.setFont(customFont16);
        inputManagerRadio.setFont(customFont16);
        inputAdminRadio.setFont(customFont16);
        inputLeaveBalanceSpinner.setFont(customFont18);

        inputUsernameLabel.setFont(customFont16);
        inputPasswordLabel.setFont(customFont16);
        inputNameLabel.setFont(customFont16);
        inputRoleLabel.setFont(customFont16);
        inputManagerIDLabel.setFont(customFont16);
        inputLeaveBalanceLabel.setFont(customFont16);

        inputSaveButton.setFont(customFont18);

        empEditButton.setFont(customFont18);
        empRemoveButton.setFont(customFont18);
        lrEditButton.setFont(customFont18);
        lrRemoveButton.setFont(customFont18);

        employeesTable.setFont(customFont10);
        employeesTable.getTableHeader().setFont(customFont10);
        leaveRequestsTable.setFont(customFont10);
        leaveRequestsTable.getTableHeader().setFont(customFont10);

        updateEmpUsernameLabel.setFont(customFont16);
        updateEmpUsernameValueLabel.setFont(customFont16);
        updateEmpPasswordLabel.setFont(customFont16);
        updateEmpPasswordTextField.setFont(customFont16);
        updateEmpNameLabel.setFont(customFont16);
        updateEmpNameTextField.setFont(customFont16);
        updateEmpManagerIDLabel.setFont(customFont16);
        updateEmpManagerIDCombo.setFont(customFont16);
        updateEmpLeaveBalanceLabel.setFont(customFont16);
        updateEmpLeaveBalanceSpinner.setFont(customFont16);

        updateEmpSaveButton.setFont(customFont16);

        updateLRIDLabel.setFont(customFont16);
        updateLRReasonLabel.setFont(customFont16);
        updateLRStartDateLabel.setFont(customFont16);
        updateLREndDateLabel.setFont(customFont16);
        updateLRDayLabel.setFont(customFont16);
        updateLRMonthLabel.setFont(customFont16);
        updateLRYearLabel.setFont(customFont16);
        updateLRTypeLabel.setFont(customFont16);
        updateLRStatusLabel.setFont(customFont16);

        updateLRReasonTextArea.setFont(customFont16);
        updateLRStartDayCombo.setFont(customFont16);
        updateLRStartMonthCombo.setFont(customFont16);
        updateLRStartYearCombo.setFont(customFont16);
        updateLREndDayCombo.setFont(customFont16);
        updateLREndMonthCombo.setFont(customFont16);
        updateLREndYearCombo.setFont(customFont16);
        updateLRSaveButton.setFont(customFont16);
        updateLRTypeCombo.setFont(customFont16);
        updateLRStatusCombo.setFont(customFont16);

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

        inputSaveButton.addMouseListener(buttonResponse);

        empEditButton.addMouseListener(buttonResponse);
        empRemoveButton.addMouseListener(buttonResponse);

        lrEditButton.addMouseListener(buttonResponse);
        lrRemoveButton.addMouseListener(buttonResponse);

        updateEmpSaveButton.addMouseListener(buttonResponse);
        updateLRSaveButton.addMouseListener(buttonResponse);
    }

    private void setUpButtonBehaviour(){
        //saving is complicated...
        //NEW EMPLOYEE CREATION
        inputSaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isInvalid = false;

                //empty textfield or username not available
                if (inputUsernameTextField.getText().isEmpty() || empService.findOneEmployee("username", inputUsernameTextField.getText())!=null) {
                    inputUsernameTextField.setText("Enter valid username!");
                    isInvalid = true;
                }
                //empty textfield
                if (inputPasswordTextField.getText().isEmpty()) {
                    inputPasswordTextField.setText("Enter valid password!");
                    isInvalid = true;
                }
                //empty textfield
                if (inputNameTextField.getText().isEmpty()) {
                    inputNameTextField.setText("Enter valid name!");
                    isInvalid = true;
                }

                //no role selected
                if (roleRadioGroup.getSelection()==null ){
                    isInvalid = true;
                }

                //value is null for whatever reason or is not Integer
                if (inputLeaveBalanceSpinner.getValue()==null || !(inputLeaveBalanceSpinner.getValue() instanceof Integer) ){
                    isInvalid=true;
                }

                if (isInvalid) {
                    JOptionPane.showMessageDialog(null,"Invalid input");
                    return;
                }

                String username = inputUsernameTextField.getText();
                String password = inputPasswordTextField.getText();
                String name = inputNameTextField.getText();
                String role = roleRadioGroup.getSelection().getActionCommand();
                String manager_id = (String) inputManagerIDCombo.getSelectedItem();
                int leaveBalance = (int) inputLeaveBalanceSpinner.getModel().getValue();

                BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);
                String hashedPassword = Password.hash(password).with(bcrypt).getResult();

                empService.addEmployee(new Employee(username,hashedPassword,name,role,manager_id,leaveBalance));
                setUp_employeesTable();
            }
        });

        //tabEmployees
        empEditButton.addActionListener(e -> {
            if(employeesTable.getSelectedRow()==-1)return;

            adminTabbedPane.setSelectedComponent(tabUpdateEmp);

            int row = employeesTable.getSelectedRow();
            updateEmpUsernameValueLabel.setText(String.valueOf(employeesTable.getModel().getValueAt(row,1)));
            updateEmpManagerIDCombo.removeItem(String.valueOf(employeesTable.getModel().getValueAt(row,0)));
        });

        empRemoveButton.addActionListener(e -> {
            empService.deleteEmployee(String.valueOf(employeesTable.getModel().getValueAt(employeesTable.getSelectedRow(),0)));
            employeesTableModel.removeRow(employeesTable.getSelectedRow());
            });

        //tabLeaveRequests
        lrEditButton.addActionListener(e -> {
            if(leaveRequestsTable.getSelectedRow()==-1)return;

            adminTabbedPane.setSelectedComponent(tabUpdateLR);

            int row = leaveRequestsTable.getSelectedRow();
            updateLRIDLabel.setText(String.valueOf(leaveRequestsTable.getModel().getValueAt(row,0)));

        });
        lrRemoveButton.addActionListener(e -> {
            lrService.deleteLeaveRequest(String.valueOf(leaveRequestsTable.getModel().getValueAt(leaveRequestsTable.getSelectedRow(),0)));
            leaveRequestsTableModel.removeRow(leaveRequestsTable.getSelectedRow());
            });

        updateEmpSaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(employeesTable.getSelectedRow()==-1)return;

                //value is null for whatever reason or is not Integer
                if (inputLeaveBalanceSpinner.getValue()==null || !(inputLeaveBalanceSpinner.getValue() instanceof Integer) ){
                    JOptionPane.showMessageDialog(null,"Invalid input");
                    return;
                }

                String id = String.valueOf(employeesTable.getModel().getValueAt(employeesTable.getSelectedRow(),0));
                Employee employee = empService.findOneEmployee("_id",id);

                if(employee==null) throw new NullPointerException("Employee not found!");

                BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);

                if(!updateEmpPasswordTextField.getText().isBlank())
                    employee.setPassword(Password.hash(updateEmpPasswordTextField.getText()).with(bcrypt).getResult());
                if(!updateEmpNameTextField.getText().isBlank())

                    employee.setName(updateEmpNameTextField.getText());

                if(updateEmpManagerIDCombo.getSelectedItem()!="")
                    employee.setManager_id(String.valueOf(updateEmpManagerIDCombo.getSelectedItem()));

                if(updateEmpSpinnerCheck.isSelected())
                    employee.setLeaveBalance((int) updateEmpLeaveBalanceSpinner.getModel().getValue());

                empService.updateEmployee(id,employee);

                updateEmpPasswordTextField.setText("");
                updateEmpNameTextField.setText("");
                updateEmpManagerIDCombo.setSelectedIndex(0);
                updateEmpSpinnerCheck.setSelected(false);

                setUp_employeesTable();

            }
        });

        updateLRSaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(leaveRequestsTable.getSelectedRow()==-1)return;


                String id = String.valueOf(leaveRequestsTable.getModel().getValueAt(leaveRequestsTable.getSelectedRow(),0));
                LeaveRequest leaveRequest = lrService.findOneLeaveRequest("_id",id);

                if(leaveRequest==null) throw new NullPointerException("Leave request not found!");

                if(!updateLRReasonTextArea.getText().isBlank())
                    leaveRequest.setReason(updateLRReasonTextArea.getText());

                if(updateLRStartDayCombo.getSelectedItem()!=""){
                    int startDay = Integer.parseInt(String.valueOf(updateLRStartDayCombo.getSelectedItem()));
                    int startMonth = Integer.parseInt(String.valueOf(updateLRStartMonthCombo.getSelectedItem()))-1;
                    int startYear = Integer.parseInt(String.valueOf(updateLRStartYearCombo.getSelectedItem()));
                    Calendar startDateCalendar = new GregorianCalendar(startYear,startMonth,startDay);
                    leaveRequest.setStartDate(startDateCalendar.getTime());
                }

                if(updateLREndDayCombo.getSelectedItem()!=""){
                    int endDay = Integer.parseInt(String.valueOf(updateLREndDayCombo.getSelectedItem()));
                    int endMonth = Integer.parseInt(String.valueOf(updateLREndMonthCombo.getSelectedItem()))-1;
                    int endYear = Integer.parseInt(String.valueOf(updateLREndYearCombo.getSelectedItem()));
                    Calendar endDateCalendar = new GregorianCalendar(endYear,endMonth,endDay);
                    leaveRequest.setEndDate(endDateCalendar.getTime());
                }

                if(updateLRTypeCombo.getSelectedItem()!="")
                    leaveRequest.setType(String.valueOf(updateLRTypeCombo.getSelectedItem()));

                if(updateLRStatusCombo.getSelectedItem()!="")
                    leaveRequest.setStatus(String.valueOf(updateLRStatusCombo.getSelectedItem()));

                lrService.updateLeaveRequest(id,leaveRequest);

                updateLRReasonTextArea.setText("");
                updateLRStartDayCombo.setSelectedIndex(0);
                updateLREndDayCombo.setSelectedIndex(0);
                updateLRTypeCombo.setSelectedIndex(0);
                updateLRStatusCombo.setSelectedIndex(0);

                setUp_leaveRequestsTable();
            }
        });

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
        leaveRequestsTableModel.setColumnIdentifiers(new String[]{"ID","Employee ID","Manager ID","Reason","Start date","End date","Type","Status"});

        //setting data
        for (int i = 0; i<leaveRequestList.size(); i++)
            leaveRequestsTableModel.addRow(leaveRequestList.get(i).toStringArray());//add every employee to model to separate row

        leaveRequestsTable.setModel(leaveRequestsTableModel);//assign the model to the table component

        //additional adjustions
        leaveRequestsTable.setRowHeight(30);
        leaveRequestsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        //FONT SET IN setUpFont()
    }

    private void setUpRadioBehaviour(){
        inputAdminRadio.addActionListener(e -> {//hide manager_id input field when admin selected
            inputManagerIDCombo.addItem("");
            inputManagerIDCombo.setSelectedItem("");
            inputManagerIDCombo.setVisible(false);
        });
        inputEmployeeRadio.addActionListener(e -> {
            inputManagerIDCombo.removeItem("");
            inputManagerIDCombo.setVisible(true);
        });//redraw when employee selected
        inputManagerRadio.addActionListener(e -> {
            inputManagerIDCombo.removeItem("");
            inputManagerIDCombo.setVisible(true);
        });//redraw when manager selected

        //FONT SET IN setUpFont()
    }

    private void setUpManagerIDCombo(){

        //initialize combo box model
        comboBoxModel = new DefaultComboBoxModel<>();

        //put all manager's IDs in model
        for (Employee employee : employeesList)
            if (Objects.equals(employee.getRole(), Employee.ROLE_MANAGER)) comboBoxModel.addElement(employee.getId());

        inputManagerIDCombo.setModel(comboBoxModel);

        comboBoxModel_update = new DefaultComboBoxModel<>();
        comboBoxModel_update.addElement("");

        for(Employee employee : employeesList)
            if(Objects.equals(employee.getRole(), Employee.ROLE_MANAGER)) comboBoxModel_update.addElement(employee.getId());

        updateEmpManagerIDCombo.setModel(comboBoxModel_update);
    }

    //different months and years should have different amount of days
    private void setUpDaysInMonth(){

        //start date change listener
        ActionListener startDateChange = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //31-day months
                DefaultComboBoxModel comboBoxModel31 = new DefaultComboBoxModel<String>();
                String[] days = new String[]{"","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
                comboBoxModel31.addAll(java.util.List.of(days));

                //30-day months
                DefaultComboBoxModel comboBoxModel30 = new DefaultComboBoxModel<String>();
                days = new String[]{"","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
                comboBoxModel30.addAll(java.util.List.of(days));

                //regular february
                DefaultComboBoxModel comboBoxModel28 = new DefaultComboBoxModel<String>();
                days = new String[]{"","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28"};
                comboBoxModel28.addAll(java.util.List.of(days));

                //leap year february
                DefaultComboBoxModel comboBoxModel29 = new DefaultComboBoxModel<String>();
                days = new String[]{"","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29"};
                comboBoxModel29.addAll(List.of(days));

                //depending on selected month
                switch(String.valueOf(updateLRStartMonthCombo.getSelectedItem())){
                    //give 31 days if these months
                    case "1":
                    case "3":
                    case "5":
                    case "7":
                    case "8":
                    case "10":
                    case "12":updateLRStartDayCombo.setModel(comboBoxModel31);
                        break;

                    //give 30 days if these months
                    case "4":
                    case "6":
                    case "9":
                    case "11":updateLRStartDayCombo.setModel(comboBoxModel30);
                        break;

                    //give 29 days to february if leap year, otherwise give 28
                    case "2":
                        int year = Integer.parseInt(String.valueOf(updateLRStartYearCombo.getSelectedItem()));//year as int

                        if(year%4==0 || (year%100==0 && year%400==0))
                            updateLRStartDayCombo.setModel(comboBoxModel29);
                        else
                            updateLRStartDayCombo.setModel(comboBoxModel28);
                        break;

                    default:
                        throw new IllegalStateException("Unexpected value: " + String.valueOf(updateLRStartMonthCombo.getSelectedItem()));
                }
            }
        };
        //adjusts amount of days in combo box when month or year is changed
        updateLRStartMonthCombo.addActionListener(startDateChange);
        updateLRStartYearCombo.addActionListener(startDateChange);

        //end date change listener
        ActionListener endMonthChange = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //31-day months
                DefaultComboBoxModel comboBoxModel31 = new DefaultComboBoxModel<String>();
                String[] days = new String[]{"","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
                comboBoxModel31.addAll(java.util.List.of(days));

                //30-day months
                DefaultComboBoxModel comboBoxModel30 = new DefaultComboBoxModel<String>();
                days = new String[]{"","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
                comboBoxModel30.addAll(java.util.List.of(days));

                //regular february
                DefaultComboBoxModel comboBoxModel28 = new DefaultComboBoxModel<String>();
                days = new String[]{"","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28"};
                comboBoxModel28.addAll(java.util.List.of(days));

                //leap year february
                DefaultComboBoxModel comboBoxModel29 = new DefaultComboBoxModel<String>();
                days = new String[]{"","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29"};
                comboBoxModel29.addAll(List.of(days));

                switch(String.valueOf(updateLREndMonthCombo.getSelectedItem())){
                    case "1":
                    case "3":
                    case "5":
                    case "7":
                    case "8":
                    case "10":
                    case "12":updateLREndDayCombo.setModel(comboBoxModel31);
                        break;

                    case "4":
                    case "6":
                    case "9":
                    case "11":updateLREndDayCombo.setModel(comboBoxModel30);
                        break;

                    case "2":int year = Integer.parseInt(String.valueOf(updateLREndYearCombo.getSelectedItem()));
                        if(year%4==0 || (year%100==0 && year%400==0))updateLREndDayCombo.setModel(comboBoxModel29);
                        else updateLREndDayCombo.setModel(comboBoxModel28);
                        break;

                    case null:
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + String.valueOf(updateLREndMonthCombo.getSelectedItem()));
                }
            }
        };
        //adjusts amount of days in combo box when month or year is changed
        updateLREndMonthCombo.addActionListener(endMonthChange);
        updateLREndYearCombo.addActionListener(endMonthChange);
    }

}
