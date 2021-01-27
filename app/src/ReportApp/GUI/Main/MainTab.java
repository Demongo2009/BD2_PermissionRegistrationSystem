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
    private JCheckBox
            actionCheckBox = new JCheckBox("policz tylko zaznaczoną akcje");
    private JCheckBox
            departmentCheckBox = new JCheckBox("policz tylko w zaznaczonym dziale");
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
    private JTextArea textArea = new JTextArea(16,40);



    public  MainTab()
    {
        setLayout(new FlowLayout());
        add(groupCheckBox);
        add(actionCheckBox);
        add(departmentCheckBox);
        add(actionsComboBox);
        add(departmentComboBox);
        add(generateButton);
        add(textArea);

        textArea.setLineWrap(true);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String text = database.generateActionCounterReport(groupCheckBox.isSelected(),
                        actionCheckBox.isSelected(),
                        departmentCheckBox.isSelected(),
                        (String)actionsComboBox.getSelectedItem(),
                        (String)departmentComboBox.getSelectedItem());

                textArea.setText(text);
//                textArea.insert(text,0);
            }
        });

    }

}
