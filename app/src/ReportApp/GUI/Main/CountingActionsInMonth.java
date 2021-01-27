package ReportApp.GUI.Main;

import ReportApp.Database.DatabaseHandler;
import ReportApp.GUI.tools.Actions;
import ReportApp.GUI.tools.Departments;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CountingActionsInMonth extends JPanel
{
    private JCheckBox
            groupCheckBox = new JCheckBox("grupuj działy");
    private JCheckBox
            actionCheckBox = new JCheckBox("policz tylko zaznaczoną akcje");
    private JCheckBox
            departmentCheckBox = new JCheckBox("policz tylko w zaznaczonym dziale");
    private JCheckBox
            monthCheckBox = new JCheckBox("policz tylko w zaznaczonym miesiacu");
    private JCheckBox
            yearCheckBox = new JCheckBox("policz tylko w zaznaczonym roku");
    String[] actionList = {"Potwierdzenie faktury","Wypelnienie dokument", "Wyslanie dokumentu do urzedu",
            "Wykonanie przelewu","Zakup na potrzeby firmy","Usunięcie konta użytkownika","Dodanie konta użytkownika",
            "Zawieszenie konta użytkownika","Zarawcie umowy z klientem","Przekazanie produktu klientowi",
            "Przeprowadzenie spotkania z kandydatem","Podpisanie umowy z nowym pracownikiem",
            "Awans pracownika","Zwolnienie pracownika","Podwyżka pensji pracownika"
    };
    String[] departmentList = {"IT","Finansowy","Zarzadzanie","Marketing"};
    String[] monthsList = {"Styczeń","Luty","Marzec","Kwiecień","Maj","Czerwiec","Lipiec","Sierpień","Wrzesień",
    "Październik","Listopad","Grudzień"};
    String[] yearsList = {"2021","2020","2019","2018","2017","2016","2015","2014","2013",
            "2012","2011","2010"};
    private JComboBox actionsComboBox = new JComboBox(actionList);
    private JComboBox departmentComboBox = new JComboBox(departmentList);
    private JComboBox monthsComboBox = new JComboBox(monthsList);
    private JComboBox yearsComboBox = new JComboBox(yearsList);
    private JButton generateButton = new JButton("Wygeneruj Raport");
    private DatabaseHandler database = new DatabaseHandler();
    private JTextArea textArea = new JTextArea(16,40);



    public  CountingActionsInMonth()
    {
        setLayout(new FlowLayout());
        add(groupCheckBox);
        add(actionCheckBox);
        add(monthCheckBox);
        add(yearCheckBox);
        add(departmentCheckBox);
        add(actionsComboBox);
        add(departmentComboBox);
        add(monthsComboBox);
        add(yearsComboBox);
        add(generateButton);
        add(textArea);

        textArea.setLineWrap(true);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String text = database.generateActionInMonthReport(groupCheckBox.isSelected(),
                        actionCheckBox.isSelected(),
                        departmentCheckBox.isSelected(),
                        monthCheckBox.isSelected(),
                        yearCheckBox.isSelected(),
                        (String) actionsComboBox.getSelectedItem(),
                        (String) departmentComboBox.getSelectedItem(),
                        (String) monthsComboBox.getSelectedItem(),
                        (String) yearsComboBox.getSelectedItem());

                textArea.setText(text);
//                textArea.insert(text,0);
            }
        });

    }

}
