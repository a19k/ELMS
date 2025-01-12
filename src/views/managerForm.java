package views;

import com.formdev.flatlaf.fonts.jetbrains_mono.FlatJetBrainsMonoFont;
import models.EmployeeService;
import models.LeaveRequest;
import models.LeaveRequestService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;

public class managerForm {
    //COMPONENTS
    private JPanel managerPanel;
    private JTabbedPane managerTabbedPane;
    private JPanel tabPersonal;
    private JPanel tabLRRewiev;
    private JLabel managerTitleLabel;
    private JLabel leaveRequestTitleLabel;
    //field name labels
    private JLabel idLabel;
    private JLabel usernameLabel;
    private JLabel nameLabel;
    private JLabel roleLabel;
    private JLabel manager_idLabel;
    private JLabel leaveBalanceLabel;
    //field value labels
    private JLabel idValueLabel;
    private JLabel usernameValueLabel;
    private JLabel nameValueLabel;
    private JLabel roleValueLabel;
    private JLabel manager_idValueLabel;
    private JLabel leaveBalanceValueLabel;

    //leave request creation labels
    private JLabel reasonLabel;
    private JLabel startDateLabel;
    private JLabel endDateLabel;
    private JLabel dayLabel;
    private JLabel monthLabel;
    private JLabel yearLabel;

    //leave request creation interactive components
    private JTextArea reasonTextArea;
    private JComboBox startDayCombo;
    private JComboBox startMonthCombo;
    private JComboBox startYearCombo;
    private JComboBox endDayCombo;
    private JComboBox endMonthCombo;
    private JComboBox endYearCombo;
    private JButton saveButton;


    private JLabel lr_reviewTitle;
    private JLabel lr_listTitle;
    //leave request review labels
    private JLabel lr_idLabel;
    private JLabel lr_employeeLabel;
    private JLabel lr_reasonLabel;
    private JLabel lr_startDateLabel;
    private JLabel lr_endDateLabel;
    private JLabel lr_typeLabel;
    private JLabel lr_statusLabel;
    //leave request review value labels
    private JLabel lr_idValueLabel;
    private JLabel lr_employeeValueLabel;
    private JLabel lr_reasonValueLabel;
    private JLabel lr_startDateValueLabel;
    private JLabel lr_endDateValueLabel;
    private JLabel lr_statusValueLabel;
    private JComboBox lr_typeValueCombo;

    private JList lr_list;
    private JButton approveButton;
    private JButton denyButton;

    //SERVICES
    private LeaveRequestService lrService = new LeaveRequestService();
    private EmployeeService empService = new EmployeeService();

    //LISTS
    private List<LeaveRequest> leaveRequests;

    //JLIST MODELS
    private DefaultListModel listModel;


    public managerForm(String[] currentManager){
        setUpFont();
        setUpManagerInfo(currentManager);
        setUpButtonResponsivity();
        setUpDaysInMonth();
        setUpButtonBehaviour(currentManager);
        setUpList(currentManager);
        setUpLRInfo();
    }

    public static void main(String[] currentManager) {
        try {//System Look and Feel set for the window, default is very old
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception ignored){}

        JFrame frame = new JFrame("ManagerForm");
        frame.setContentPane(new managerForm(currentManager).managerPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    //SETUP CODE

    private void setUpFont(){
        FlatJetBrainsMonoFont.install();

        Font customFont18 = new Font("JetBrains Mono",Font.PLAIN,18);
        Font customFont16 = new Font("JetBrains Mono",Font.PLAIN,16);
        Font customFont12 = new Font("JetBrains Mono",Font.PLAIN,12);

        managerTitleLabel.setFont(customFont18);

        idLabel.setFont(customFont16);
        idValueLabel.setFont(customFont16);
        usernameLabel.setFont(customFont16);
        usernameValueLabel.setFont(customFont16);
        nameLabel.setFont(customFont16);
        nameValueLabel.setFont(customFont16);
        roleLabel.setFont(customFont16);
        roleValueLabel.setFont(customFont16);
        manager_idLabel.setFont(customFont16);
        manager_idValueLabel.setFont(customFont16);
        leaveBalanceLabel.setFont(customFont16);
        leaveBalanceValueLabel.setFont(customFont16);

        leaveRequestTitleLabel.setFont(customFont18);

        reasonLabel.setFont(customFont16);
        startDateLabel.setFont(customFont16);
        endDateLabel.setFont(customFont16);
        dayLabel.setFont(customFont16);
        monthLabel.setFont(customFont16);
        yearLabel.setFont(customFont16);

        reasonTextArea.setFont(customFont12);
        startDayCombo.setFont(customFont12);
        startMonthCombo.setFont(customFont12);
        startYearCombo.setFont(customFont12);
        endDayCombo.setFont(customFont12);
        endMonthCombo.setFont(customFont12);
        endYearCombo.setFont(customFont12);

        saveButton.setFont(customFont16);

        lr_reviewTitle.setFont(customFont18);
        lr_listTitle.setFont(customFont18);

        lr_idLabel.setFont(customFont16);
        lr_employeeLabel.setFont(customFont16);
        lr_reasonLabel.setFont(customFont16);
        lr_startDateLabel.setFont(customFont16);
        lr_endDateLabel.setFont(customFont16);
        lr_typeLabel.setFont(customFont16);
        lr_statusLabel.setFont(customFont16);

        lr_idValueLabel.setFont(customFont16);
        lr_employeeValueLabel.setFont(customFont16);
        lr_reasonValueLabel.setFont(customFont16);
        lr_startDateValueLabel.setFont(customFont16);
        lr_endDateValueLabel.setFont(customFont16);
        lr_typeValueCombo.setFont(customFont16);
        lr_statusValueLabel.setFont(customFont16);

        lr_list.setFont(customFont12);
        approveButton.setFont(customFont16);
        denyButton.setFont(customFont16);
    }

    private void setUpManagerInfo(String[] currentManager){
        idValueLabel.setText(currentManager[0]);
        usernameValueLabel.setText(currentManager[1]);
        nameValueLabel.setText(currentManager[3]);
        roleValueLabel.setText(currentManager[4]);
        manager_idValueLabel.setText(currentManager[5]);
        leaveBalanceValueLabel.setText(currentManager[6]);
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
        approveButton.addMouseListener(buttonResponse);
        denyButton.addMouseListener(buttonResponse);
    }

    //different months and years should have different amount of days
    private void setUpDaysInMonth(){

        //start date change listener
        ActionListener startDateChange = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //31-day months
                DefaultComboBoxModel comboBoxModel31 = new DefaultComboBoxModel<String>();
                String[] days = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
                comboBoxModel31.addAll(java.util.List.of(days));

                //30-day months
                DefaultComboBoxModel comboBoxModel30 = new DefaultComboBoxModel<String>();
                days = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
                comboBoxModel30.addAll(java.util.List.of(days));

                //regular february
                DefaultComboBoxModel comboBoxModel28 = new DefaultComboBoxModel<String>();
                days = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28"};
                comboBoxModel28.addAll(java.util.List.of(days));

                //leap year february
                DefaultComboBoxModel comboBoxModel29 = new DefaultComboBoxModel<String>();
                days = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29"};
                comboBoxModel29.addAll(List.of(days));

                //depending on selected month
                switch(String.valueOf(startMonthCombo.getSelectedItem())){
                    //give 31 days if these months
                    case "1":
                    case "3":
                    case "5":
                    case "7":
                    case "8":
                    case "10":
                    case "12":startDayCombo.setModel(comboBoxModel31);
                        break;

                    //give 30 days if these months
                    case "4":
                    case "6":
                    case "9":
                    case "11":startDayCombo.setModel(comboBoxModel30);
                        break;

                    //give 29 days to february if leap year, otherwise give 28
                    case "2":
                        int year = Integer.parseInt(String.valueOf(startYearCombo.getSelectedItem()));//year as int

                        if(year%4==0 || (year%100==0 && year%400==0))
                            startDayCombo.setModel(comboBoxModel29);
                        else
                            startDayCombo.setModel(comboBoxModel28);
                        break;

                    default:
                        throw new IllegalStateException("Unexpected value: " + String.valueOf(startMonthCombo.getSelectedItem()));
                }
            }
        };
        //adjusts amount of days in combo box when month or year is changed
        startMonthCombo.addActionListener(startDateChange);
        startYearCombo.addActionListener(startDateChange);

        //end date change listener
        ActionListener endMonthChange = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //31-day months
                DefaultComboBoxModel comboBoxModel31 = new DefaultComboBoxModel<String>();
                String[] days = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
                comboBoxModel31.addAll(java.util.List.of(days));

                //30-day months
                DefaultComboBoxModel comboBoxModel30 = new DefaultComboBoxModel<String>();
                days = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
                comboBoxModel30.addAll(java.util.List.of(days));

                //regular february
                DefaultComboBoxModel comboBoxModel28 = new DefaultComboBoxModel<String>();
                days = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28"};
                comboBoxModel28.addAll(java.util.List.of(days));

                //leap year february
                DefaultComboBoxModel comboBoxModel29 = new DefaultComboBoxModel<String>();
                days = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29"};
                comboBoxModel29.addAll(List.of(days));

                switch(String.valueOf(endMonthCombo.getSelectedItem())){
                    case "1":
                    case "3":
                    case "5":
                    case "7":
                    case "8":
                    case "10":
                    case "12":endDayCombo.setModel(comboBoxModel31);
                        break;

                    case "4":
                    case "6":
                    case "9":
                    case "11":endDayCombo.setModel(comboBoxModel30);
                        break;

                    case "2":int year = Integer.parseInt(String.valueOf(endYearCombo.getSelectedItem()));
                        if(year%4==0 || (year%100==0 && year%400==0))endDayCombo.setModel(comboBoxModel29);
                        else endDayCombo.setModel(comboBoxModel28);
                        break;

                    case null:
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + String.valueOf(endMonthCombo.getSelectedItem()));
                }
            }
        };
        //adjusts amount of days in combo box when month or year is changed
        endMonthCombo.addActionListener(endMonthChange);
        endYearCombo.addActionListener(endMonthChange);
    }

    private void setUpButtonBehaviour(String[] currentManager){
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(Integer.parseInt(currentManager[6])<1) {
                    JOptionPane.showMessageDialog(null,"Your leave balance is insufficient.");
                    return;
                }
                if(reasonTextArea.getText()==null || reasonTextArea.getText().isBlank()){
                    JOptionPane.showMessageDialog(null, "Please enter the reason for leave.");
                    return;
                }
                if(startDayCombo.getSelectedItem()==null || startMonthCombo.getSelectedItem()==null || startYearCombo.getSelectedItem()==null){
                    JOptionPane.showMessageDialog(null,"Please enter a valid starting date.");
                    return;
                }
                if(!(startDayCombo.getSelectedItem() instanceof String) || !(startMonthCombo.getSelectedItem() instanceof String) || !(startYearCombo.getSelectedItem() instanceof String)){
                    JOptionPane.showMessageDialog(null, "Please enter a valid ending date.");
                    return;
                }
                if(endDayCombo.getSelectedItem()==null || endMonthCombo.getSelectedItem()==null || endYearCombo.getSelectedItem()==null){
                    JOptionPane.showMessageDialog(null,"Please enter a valid starting date.");
                    return;
                }
                if(!(endDayCombo.getSelectedItem() instanceof String) || !(endMonthCombo.getSelectedItem() instanceof String) || !(endYearCombo.getSelectedItem() instanceof String)){
                    JOptionPane.showMessageDialog(null, "Please enter a valid ending date.");
                    return;
                }

                String employee_id = currentManager[0];
                String manager_id = currentManager[5];
                String reason = reasonTextArea.getText();

                int startDay = Integer.parseInt(String.valueOf(startDayCombo.getSelectedItem()));
                int startMonth = Integer.parseInt(String.valueOf(startMonthCombo.getSelectedItem()))-1;
                int startYear = Integer.parseInt(String.valueOf(startYearCombo.getSelectedItem()));
                Calendar startDateCalendar = new GregorianCalendar(startYear,startMonth,startDay);
                Date startDate = startDateCalendar.getTime();

                int endDay = Integer.parseInt(String.valueOf(endDayCombo.getSelectedItem()));
                int endMonth = Integer.parseInt(String.valueOf(endMonthCombo.getSelectedItem()))-1;
                int endYear = Integer.parseInt(String.valueOf(endYearCombo.getSelectedItem()));
                Calendar endDateCalendar = new GregorianCalendar(endYear,endMonth,endDay);
                Date endDate = endDateCalendar.getTime();

                if(startDate.before(new Date())){
                    JOptionPane.showMessageDialog(null,"Starting date cannot be in the past!");
                    return;
                }
                if(endDate.before(startDate)){
                    JOptionPane.showMessageDialog(null,"Ending date cannot be before starting date!");
                    return;
                }

                lrService.addLeaveRequest(new LeaveRequest(employee_id,manager_id,reason,startDate,endDate));

            }
        });

        approveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                LeaveRequest leaveRequest = lrService.findOneLeaveRequest("_id", String.valueOf(lr_list.getSelectedValue()));
                leaveRequest.setType(String.valueOf(lr_typeValueCombo.getSelectedItem()));
                leaveRequest.setStatus(LeaveRequest.STATUS_APPROVED);
                lrService.updateLeaveRequest(String.valueOf(lr_list.getSelectedValue()),leaveRequest);

                setUpList(currentManager);
            }
        });

        denyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                LeaveRequest leaveRequest= lrService.findOneLeaveRequest("_id", String.valueOf(lr_list.getSelectedValue()));
                leaveRequest.setType(String.valueOf(lr_typeValueCombo.getSelectedItem()));
                leaveRequest.setStatus(LeaveRequest.STATUS_DENIED);
                lrService.updateLeaveRequest(String.valueOf(lr_list.getSelectedValue()),leaveRequest);

                setUpList(currentManager);
            }
        });

    }

    private void setUpList(String[] currentManager){
        leaveRequests = lrService.getAllLeaveRequests();

        listModel = new DefaultListModel<>();

        for(LeaveRequest leaveRequest : leaveRequests)
            if(Objects.equals(leaveRequest.getManager_id(), currentManager[0]) && Objects.equals(leaveRequest.getStatus(), LeaveRequest.STATUS_WAITING)) listModel.addElement(leaveRequest.getId());

        lr_list.setModel(listModel);

        lr_list.setDragEnabled(false);
    }

    private void setUpLRInfo(){
        lr_list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if(lr_list.getModel().getSize()==0 || lr_list.isSelectionEmpty()) return;

                LeaveRequest leaveRequest = lrService.findOneLeaveRequest("_id", String.valueOf(lr_list.getSelectedValue()));

                if(leaveRequest==null){
                    throw new NullPointerException("Leave request not found!");
                }
                lr_idValueLabel.setText(leaveRequest.getId());
                lr_employeeValueLabel.setText(empService.findOneEmployee("_id",leaveRequest.getEmployee_id()).getUsername());
                lr_reasonValueLabel.setText(leaveRequest.getReason());
                lr_startDateValueLabel.setText(leaveRequest.getStartDate().toString());
                lr_endDateValueLabel.setText(leaveRequest.getEndDate().toString());
            }
        });
    }

}
