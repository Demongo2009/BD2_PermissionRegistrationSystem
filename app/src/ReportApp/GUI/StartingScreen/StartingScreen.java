package ReportApp.GUI.StartingScreen;



import ReportApp.GUI.Main.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ReportApp.GUI.tools.SwingConsole.run;


//import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;

public class StartingScreen extends JFrame
{
    private String STARTING_SCREEN_TITLE = "BAZA BAZY DANYCH";
    private JPanel panel = new JPanel();
    private JButton loginButton = new JButton("LOGIN");
    private JButton registerButton = new JButton("REGISTER");
    private JLabel OperationState =  new JLabel("");
    private String login="";
    private String pass="";
    static String username = null;

    /**
     * Once user entered all his data, its send to the server and decides what to do next
     * @return true when login successes
     */
    private boolean CheckLoginPassword()
    {
        boolean loginSuccesful=false;

        if(login.equals("igor"))
        {
            if (pass.equals("haslo"))
            {
                loginSuccesful = true;
                MainWindow app = new MainWindow(login);
                run(app, "APLIKACJA", 600, 800);
                dispose();
            }
        }
        else
        {
            System.out.println("ZLE PASSY PACANIE");
        }

        return loginSuccesful;

//            boolean loginSuccesful=false;
//            try
//            {
//                sendLoginOrRegisterRequest(login, pass, ClientToServerMessageType.REQUEST_LOGIN);
//
//                if(receiveLoginAnswer())
//                {
//                    OperationState.setText("Succesfully signed in :)");
//                    MainWindow MainClientApp = new MainWindow(login);
//                    run(MainClientApp, STARTING_SCREEN_TITLE, 600, 600);
//                    dispose();
//                    loginSuccesful=true;
//                }
//                else
//                {
//                    OperationState.setForeground(Color.RED);
//                    OperationState.setText("TRY AGAIN");
//                    loginSuccesful=false;
//                }
//            }
//            catch (Exception e)
//            {
//                OperationState.setText("<html>"+e.getMessage()+"</html>");
//                //e.printStackTrace();
//            }
//            return loginSuccesful;

    }

    /**
     *
     */
    private void CheckRegister()
    {
//            try
//            {
//                sendLoginOrRegisterRequest(login, pass, ClientToServerMessageType.REQUEST_REGISTER);
//                MainWindow MainClientApp = new MainWindow(login);
//                receiveLoginAnswer();
//                OperationState.setText("Succesfully signed up");
//                run(MainClientApp, STARTING_SCREEN_TITLE, 600, 600);
//                dispose();
//            }
//            catch (Exception e)
//            {
//                OperationState.setText("<html>"+e.getMessage()+"</html>");
//                //e.printStackTrace();
//            }
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
                LoginScreenDialog dlg = new LoginScreenDialog(null);
                dlg.setSize(300,180);
                dlg.setVisible(true);
                login = dlg.getLogin();
                pass = dlg.getPassword();
                CheckLoginPassword();
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                RegisterScreenDialog dlg = new RegisterScreenDialog(null);
                dlg.setSize(300,250);
                dlg.setVisible(true);
                login = dlg.getLogin();
                pass = dlg.getPassword();
                CheckRegister();
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
    }

    public static void main(String[] args) {
        run(new MainWindow("login"), 1280, 720);
    }

}
