package ReportApp.GUI.Main;

import ReportApp.Database.DatabaseHandler;
import ReportApp.GUI.tools.Actions;
import ReportApp.GUI.tools.Departments;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HistoryOfPermission extends JPanel
{
//    private JCheckBox
//            groupCheckBox = new JCheckBox("grupuj działy");
//    private JCheckBox
//            permissionTypeCheckBox = new JCheckBox("policz tylko zaznaczony rodzaj uprawnien");
//    private JCheckBox
//            departmentCheckBox = new JCheckBox("policz tylko w zaznaczonym dziale");
    String[] employeeList = {""};
//    String[] departmentList = {"IT","Finansowy","Zarzadzanie","Marketing"};
    private JComboBox employeeComboBox = new JComboBox(employeeList);
//    private JComboBox departmentComboBox = new JComboBox(departmentList);
    private JButton generateButton = new JButton("Wygeneruj Raport");
    private JButton refreshEmployeesButton = new JButton("Zaktualizuj pracowników");
    private DatabaseHandler database = new DatabaseHandler();
    private JTextArea textArea = new JTextArea(16,40);



    public  HistoryOfPermission()
    {
        setLayout(new FlowLayout());
//        add(groupCheckBox);
//        add(permissionTypeCheckBox);
//        add(departmentCheckBox);
        add(employeeComboBox);
//        add(departmentComboBox);
        add(generateButton);
        add(refreshEmployeesButton);
        add(textArea);

        textArea.setLineWrap(true);

        refreshEmployeesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String[] employees = database.getEmployees();

                employeeComboBox.setModel(new DefaultComboBoxModel<String>(employees));
            }
        });

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String text = database.generateHistoryOfPermission((String) employeeComboBox.getSelectedItem());

                textArea.setText(text);
            }
        });

    }

}
