import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


public class Login extends JFrame implements ActionListener, KeyListener {
    //---------------------------------------------------------------------------------------//
    private JLabel topLabel;
    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private JLabel background;
    //---------------------------------------------------------------------------------------//
    private ImageIcon icon;
    private ImageIcon bGImage;
    //---------------------------------------------------------------------------------------//
    private JTextField tFUserName;
    //---------------------------------------------------------------------------------------//
    private JPasswordField pFPassword;
    //---------------------------------------------------------------------------------------//
    private JButton btnLogin;
    private JButton btnBack;
    //---------------------------------------------------------------------------------------//
    private DatabaseHelper dbHelper;
    private Connection connection;

    public Login() {
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Login Form");
        setBounds(300, 100, 370, 350);
        //---------------------------------------------------------------------------------------//
        init();
        //---------------------------------------------------------------------------------------//
        icon = new ImageIcon("LoginIcon.jpg");

        setIconImage(icon.getImage());
        //---------------------------------------------------------------------------------------//
        bGImage = new ImageIcon("Backgroundimage.jpg");
        background = new JLabel("", bGImage, JLabel.CENTER);
        background.setBounds(0, 0, 370, 350);
        add(background);
        //---------------------------------------------------------------------------------------//
        background.add(topLabel);
        background.add(userNameLabel);
        background.add(passwordLabel);
        background.add(tFUserName);
        background.add(pFPassword);
        background.add(btnLogin);
        background.add(btnBack);
        //---------------------------------------------------------------------------------------//
        setVisible(true);
    }



    public void init() {
        setLayout(null);
        //---------------------------------------------------------------------------------------//
        topLabel = new JLabel();
        topLabel.setText("Hotel Management System");
        Font font = new Font("Arial", Font.BOLD, 18);
        topLabel.setFont(font);
        topLabel.setBounds(60, 20, 300, 30);
        topLabel.setForeground(Color.white);
        //---------------------------------------------------------------------------------------//
        userNameLabel = new JLabel();
        userNameLabel.setText("User Name");
        userNameLabel.setBounds(30, 70, 100, 20);
        Font labelSize=new Font("Arial",Font.BOLD,16);
        userNameLabel.setFont(labelSize);
        userNameLabel.setForeground(Color.WHITE);
        //---------------------------------------------------------------------------------------//
        passwordLabel = new JLabel();
        passwordLabel.setText("Password");
        passwordLabel.setBounds(30, 130, 100, 20);
        passwordLabel.setFont(labelSize);
        passwordLabel.setForeground(Color.WHITE);
        //---------------------------------------------------------------------------------------//
        tFUserName = new JTextField();
        tFUserName.setBounds(150, 70, 150, 20);
        tFUserName.addKeyListener(this);
        //---------------------------------------------------------------------------------------//
        pFPassword = new JPasswordField();
        pFPassword.setBounds(150, 130, 150, 20);
        pFPassword.addKeyListener(this);
        //---------------------------------------------------------------------------------------//
        btnLogin = new JButton("Login");
        btnLogin.setBounds(210, 200, 80, 40);
        btnLogin.addActionListener(this);
        btnLogin.addKeyListener(this);
        //---------------------------------------------------------------------------------------//
        btnBack = new JButton("Back");
        btnBack.setBounds(10, 270, 65, 30);
        btnBack.addActionListener(this);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            connection=ConnectionConfiguration.getConnection();
            if(connection!=null) {
                dbHelper=new DatabaseHelper(connection);
                String name = tFUserName.getText().toString();
                String password = pFPassword.getText().toString();
                if(name.isEmpty() || password.isEmpty()){
                    JOptionPane.showMessageDialog(this,"Something is missing");
                }
                else {
                    User user = dbHelper.getRecord(name);
                    if(dbHelper.searchRegistration(name)!= null) {
                        if (user.getType().equalsIgnoreCase("Owner")) {
                            if (user.getUser_name().equals(name) && user.getPassword().equals(password)) {
                                new MainMenu();
                                dispose();
                            } else {
                                JOptionPane.showMessageDialog(this, "Password is wrong");
                            }
                        }
                     else{
                            JOptionPane.showMessageDialog(this, "You are not owner");
                        }
                    }else {
                        JOptionPane.showMessageDialog(this, "User Name is Wrong");
                    }
                }
            }
            else{
                System.out.println("Not Connected");
            }
        }
        else if(e.getSource()==btnBack) {
            new Choose();
            dispose();
        }
    }



    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER) {
            if(e.getSource()==tFUserName)
                pFPassword.requestFocus();
            if(e.getSource()==pFPassword)
                btnLogin.requestFocus();
            if(e.getSource()==btnLogin)
                if (e.getSource() == btnLogin) {
                    connection=ConnectionConfiguration.getConnection();
                    if(connection!=null) {
                        dbHelper=new DatabaseHelper(connection);
                        String name = tFUserName.getText().toString();
                        String password = pFPassword.getText().toString();
                        if(name.isEmpty() || password.isEmpty()){
                            JOptionPane.showMessageDialog(this,"Something is missing");
                        }
                        else {
                            User user = dbHelper.getRecord(name);
                            if(dbHelper.searchRegistration(name)!= null) {
                                if (user.getType().equalsIgnoreCase("Owner")) {
                                    if (user.getUser_name().equals(name) && user.getPassword().equals(password)) {
                                        new MainMenu();
                                        dispose();
                                    } else {
                                        JOptionPane.showMessageDialog(this, "Password is wrong");
                                    }
                                }
                                else{
                                    JOptionPane.showMessageDialog(this, "You are not owner");
                                }
                            }else {
                                JOptionPane.showMessageDialog(this, "User Name is Wrong");
                            }
                        }
                    }
                    else{
                        System.out.println("Not Connected");
                    }
                }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        }
        
        
        
        
}
