package AccessApp.GUI.Main;

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
        tabs.addTab("Dodawanie uprawnienia", tab1);
        tabs.addTab("Usuwanie uprawnienia", tab2);
        tabs.addTab("TAB 1", tab1);
        tabs.addTab("TAB 1", tab1);
        add(tabs);

    }

}
