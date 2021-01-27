package GUI.StartingScreen;



import GUI.Main.MainWindow;
import Database.DatabaseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import static GUI.tools.SwingConsole.run;


public class StartingScreen extends JFrame
{
    private String STARTING_SCREEN_TITLE = "BAZA BAZY DANYCH";
    private JPanel panel = new JPanel();
    private JButton loginButton = new JButton("LOGIN");
    private JButton registerButton = new JButton("REGISTER");
    private JLabel OperationState =  new JLabel("");
    private String login="";
    private String pass="";
    DatabaseHandler dbh = null;
    Connection connection = null;

    /**
     * Once user entered all his data, its send to the server and decides what to do next
     * @return true when login successes
     */
    private boolean CheckLoginPassword()
    {


        if(dbh.checkLoginPassword(login, pass)) {
            JOptionPane.showMessageDialog(null, "Logowanie powiodło się.",
                    "Informacja", JOptionPane.INFORMATION_MESSAGE, null);
        }else{
            return false;
        }
        if(connection != null) {
            MainWindow app = new MainWindow(login);
            run(app, "APLIKACJA", 600, 800);
            dispose();
            return true;
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Niepoprawne dane",
                    "Informacja", JOptionPane.INFORMATION_MESSAGE, null);
            return false;
        }


    }

    /**
     *
     */
    private boolean CheckRegister()
    {
        if(dbh.checkLoginPassword(login, pass)) {
            JOptionPane.showMessageDialog(null, "Logowanie powiodło się.",
                    "Informacja", JOptionPane.INFORMATION_MESSAGE, null);
        }else{
            return false;
        }
        if(connection != null) {
            MainWindow app = new MainWindow(login);
            run(app, "APLIKACJA", 600, 800);
            dispose();
            return true;
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Niepoprawne dane",
                    "Informacja", JOptionPane.INFORMATION_MESSAGE, null);
            return false;
        }
    }

    public StartingScreen( )
    {
        setTitle("CHOOSE LOGIN OR REGISTER");
        panel.setLayout(null);

        loginButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                boolean cond = false;
                while(!cond) {
                    LoginScreenDialog dlg = new LoginScreenDialog(null);
                    dlg.setSize(300,180);
                    dlg.setVisible(true);
                    login = dlg.getLogin();
                    pass = dlg.getPassword();
                    cond = CheckLoginPassword();
                    if(!cond)
                        JOptionPane.showMessageDialog(null, "Niepoprawne dane logowania.",
                                "Informacja", JOptionPane.INFORMATION_MESSAGE, null);

                }
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                boolean cond = false;
                while(!cond) {
                    RegisterScreenDialog dlg = new RegisterScreenDialog(null);
                    dlg.setSize(300,250);
                    dlg.setVisible(true);
                    login = dlg.getLogin();
                    pass = dlg.getPassword();
                    cond = CheckRegister();
                    if(!cond)
                        JOptionPane.showMessageDialog(null, "Niepoprawne dane logowania.",
                                "Informacja", JOptionPane.INFORMATION_MESSAGE, null);

                }
            }
        });


        loginButton.setBounds(30,10,100,40);
        registerButton.setBounds(160,10,100,40);
        OperationState.setBounds(30,60,240,60);
        OperationState.setForeground(Color.RED);
        OperationState.setVerticalAlignment(JLabel.TOP);


        panel.add(loginButton);
        panel.add(registerButton);
        panel.add(OperationState);
        add(panel);
        loginButton.requestFocus();
        getRootPane().setDefaultButton(loginButton);

        dbh = new DatabaseHandler();
        connection = dbh.connect();
    }

    public static void main(String[] args)
    {
        run(new StartingScreen(), 600, 800);
        //run(new MainWindow("login"), 600, 800);
    }

}
