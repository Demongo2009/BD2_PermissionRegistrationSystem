package ReportApp.GUI.Main;

import ReportApp.Database.DatabaseHandler;
import ReportApp.GUI.tools.Actions;
import ReportApp.GUI.tools.Departments;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainTab extends JPanel
{
    private JCheckBox
            groupCheckBox = new JCheckBox("grupuj działy");
    String[] actionList = {"Potwierdzenie faktury","Wypelnienie dokument", "Wyslanie dokumentu do urzedu",
            "Wykonanie przelewu","Zakup na potrzeby firmy","Usunięcie konta użytkownika","Dodanie konta użytkownika",
            "Zawieszenie konta użytkownika","Zarawcie umowy z klientem","Przekazanie produktu klientowi",
            "Przeprowadzenie spotkania z kandydatem","Podpisanie umowy z nowym pracownikiem",
            "Awans pracownika","Zwolnienie pracownika","Podwyżka pensji pracownika"
    };
    String[] departmentList = {"IT","Finansowy","Zarzadzanie","Marketing"};
    private JComboBox actionsComboBox = new JComboBox(actionList);
    private JComboBox departmentComboBox = new JComboBox(departmentList);
    private JButton generateButton = new JButton("Wygeneruj Raport");
    private DatabaseHandler database = new DatabaseHandler();


    private String checkSelects()
    {
        int cnt=0;
        String tmp = "SELECT ";
        if (groupCheckBox.isSelected())
        {
            tmp +="PRIVILEGES P ";
            cnt++;
        }
        tmp += "\t FROM BAZA DANYCH";
        if(cnt>0)
        {
            return tmp;
        }
        else
        {
            return "SELECT NOTHING";
        }
    }

    public  MainTab()
    {
        setLayout(new FlowLayout());
        add(groupCheckBox);
        add(actionsComboBox);
        add(departmentComboBox);
        add(generateButton);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String text = database.generateActionCounterReport(groupCheckBox.isSelected(),
                        Actions.intToAction(actionsComboBox.getSelectedIndex()),
                        Departments.intToDepartment(departmentComboBox.getSelectedIndex()));
            }
        });
//        addButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                database.addEmployee(id++,nameField.getText(),surnameField.getText());
//            }
//        });
//        getButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                database.printEmployees();
//            }
//        });
    }

}
