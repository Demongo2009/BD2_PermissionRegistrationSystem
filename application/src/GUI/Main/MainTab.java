package GUI.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainTab extends JPanel
{
    private JCheckBox
        privilegesCheckBox = new JCheckBox("privileges"),
        employeesCheckBox = new JCheckBox("employees"),
        privilegesCheckBox2 = new JCheckBox("privileges2"),
        employeesCheckBox2 = new JCheckBox("employees2");
    private JButton selectButton = new JButton("Generate Select");




    private String checkSelects()
    {
        int cnt=0;
        String tmp = "SELECT ";
        if (privilegesCheckBox.isSelected())
        {
            tmp +="PRIVILEGES P ";
            cnt++;
        }
        if(privilegesCheckBox2.isSelected())
        {
            tmp+="UPRAWNIENIA U ";
            cnt++;
        }
        if (employeesCheckBox.isSelected())
        {
            tmp +="EMPLOYEES E ";
            cnt++;
        }
        if(employeesCheckBox2.isSelected())
        {
            tmp+="PRACOWNICY PR ";
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
        add(privilegesCheckBox);
        add(privilegesCheckBox2);
        add(employeesCheckBox);
        add(employeesCheckBox2);
        add(selectButton);

        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(checkSelects());
            }
        });
    }

}
