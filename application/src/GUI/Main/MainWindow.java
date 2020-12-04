package GUI.Main;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame
{
    private JTabbedPane tabs = new JTabbedPane();
    private JPanel tab1 = new MainTab();
    private JPanel tab2 = new JPanel();
    private String user;




    public MainWindow(String user)
    {
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                super.windowClosing(e);
                dispose();
            }
        });
        this.user = user;
        tabs.addTab("main tab",tab1);
        tabs.addTab("tab 2", tab2);
        add(tabs);

    }

}
