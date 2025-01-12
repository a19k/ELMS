package views;

import com.formdev.flatlaf.fonts.jetbrains_mono.FlatJetBrainsMonoFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class employeeForm {

    public JPanel employeePanel;
    private JLabel idValueLabel;
    private JLabel usernameValueLabel;
    private JLabel nameValueLabel;
    private JLabel roleValueLabel;
    private JLabel manager_idValueLabel;
    private JLabel leaveBalanceValueLabel;

    private JLabel reasonLabel;
    private JLabel startDateLabel;
    private JLabel endDateLabel;

    private JTextArea reasonTextArea;
    private JLabel employeeTitleLabel;
    private JLabel leaveRequestTitleLabel;
    private JComboBox startDayCombo;
    private JComboBox startMonthCombo;
    private JComboBox startYearCombo;
    private JComboBox endDayCombo;
    private JComboBox endMonthCombo;
    private JComboBox endYearCombo;
    private JLabel dayLabel;
    private JLabel monthLabel;
    private JLabel yearLabel;
    private JLabel idLabel;
    private JLabel usernameLabel;
    private JLabel nameLabel;
    private JLabel roleLabel;
    private JLabel manager_idLabel;
    private JLabel leaveBalanceLabel;
    private JButton saveButton;

    public employeeForm(String[] args){
        setUpFont();
        setUpButtonResponsivity();
        setUpEmployeeInfo(args);
        setUpMonthChangeListener();
    }

    public static void main(String[] args) {
        try {//System Look and Feel set for the window, default is very old
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception ignored){}

        JFrame frame = new JFrame("employeeForm");
        frame.setContentPane(new employeeForm(args).employeePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    private void setUpFont(){
        FlatJetBrainsMonoFont.install();

        Font customFont18 = new Font("JetBrains Mono",Font.PLAIN,18);
        Font customFont16 = new Font("JetBrains Mono",Font.PLAIN,16);
        Font customFont12 = new Font("JetBrains Mono",Font.PLAIN,12);

        employeeTitleLabel.setFont(customFont18);

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
    }

    private void setUpEmployeeInfo(String[] args){
        idValueLabel.setText(args[0]);
        usernameValueLabel.setText(args[1]);
        nameValueLabel.setText(args[3]);
        roleValueLabel.setText(args[4]);
        manager_idValueLabel.setText(args[5]);
        leaveBalanceValueLabel.setText(args[6]);
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

    }

    public void setUpMonthChangeListener(){
        ActionListener startMonthChange = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultComboBoxModel comboBoxModel31 = new DefaultComboBoxModel<String>();
                String[] days = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
                comboBoxModel31.addAll(List.of(days));

                DefaultComboBoxModel comboBoxModel30 = new DefaultComboBoxModel<String>();
                days = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
                comboBoxModel30.addAll(List.of(days));

                DefaultComboBoxModel comboBoxModel28 = new DefaultComboBoxModel<String>();
                days = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28"};
                comboBoxModel28.addAll(List.of(days));

                DefaultComboBoxModel comboBoxModel29 = new DefaultComboBoxModel<String>();
                days = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29"};
                comboBoxModel29.addAll(List.of(days));

                switch(String.valueOf(startMonthCombo.getSelectedItem())){
                    case "1":
                    case "3":
                    case "5":
                    case "7":
                    case "8":
                    case "10":
                    case "12":startDayCombo.setModel(comboBoxModel31);
                        break;

                    case "4":
                    case "6":
                    case "9":
                    case "11":startDayCombo.setModel(comboBoxModel30);
                        break;

                    case "2":int year = Integer.parseInt(String.valueOf(startYearCombo.getSelectedItem()));
                        if(year%4==0 || (year%100==0 && year%400==0))startDayCombo.setModel(comboBoxModel29);
                        else startDayCombo.setModel(comboBoxModel28);
                        break;

                    case null:
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + String.valueOf(startMonthCombo.getSelectedItem()));
                }
            }
        };
        startMonthCombo.addActionListener(startMonthChange);
        startYearCombo.addActionListener(startMonthChange);

        ActionListener endMonthChange = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultComboBoxModel comboBoxModel31 = new DefaultComboBoxModel<String>();
                String[] days = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
                comboBoxModel31.addAll(List.of(days));

                DefaultComboBoxModel comboBoxModel30 = new DefaultComboBoxModel<String>();
                days = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
                comboBoxModel30.addAll(List.of(days));

                DefaultComboBoxModel comboBoxModel28 = new DefaultComboBoxModel<String>();
                days = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28"};
                comboBoxModel28.addAll(List.of(days));

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

        endMonthCombo.addActionListener(endMonthChange);
        endYearCombo.addActionListener(endMonthChange);
    }

}
