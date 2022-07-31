import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.SQLException;

public class SignUpForm extends JFrame implements ActionListener, KeyListener {
    private JLabel topLabel;
    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private JLabel confirmPasswordLabel;
    private JLabel userTypedLabel;
    private JLabel background;
    //---------------------------------------------------------------------------------------//
    private ImageIcon icon;
    private ImageIcon bGImage;
    //---------------------------------------------------------------------------------------//
    private JTextField tFUserName;
    //---------------------------------------------------------------------------------------//
    private JPasswordField pFPassword;
    private JPasswordField pFConfirmPassword;
    //---------------------------------------------------------------------------------------//
    private JComboBox tFUserType;
    //---------------------------------------------------------------------------------------//
    private JButton btnSignUpForm;
    private JButton btnBack;
    //---------------------------------------------------------------------------------------//
    private DatabaseHelper dbHelper;
    private Connection connection;

    public SignUpForm() {
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Sign UP Form");
        setBounds(300, 100, 400, 350);
        //---------------------------------------------------------------------------------------//
        init();
        //---------------------------------------------------------------------------------------//
        icon = new ImageIcon("LoginIcon.jpg");
        setIconImage(icon.getImage());
        //---------------------------------------------------------------------------------------//
        bGImage = new ImageIcon("Backgroundimage.jpg");
        background = new JLabel("", bGImage, JLabel.CENTER);
        background.setBounds(0, 0, 400, 350);
        add(background);
        //---------------------------------------------------------------------------------------//
        background.add(topLabel);
        background.add(userNameLabel);
        background.add(passwordLabel);
        background.add(tFUserName);
        background.add(pFPassword);
        background.add(btnSignUpForm);
        background.add(btnBack);
        background.add(confirmPasswordLabel);
        background.add(pFConfirmPassword);
        background.add(userTypedLabel);
        background.add(tFUserType);
        //---------------------------------------------------------------------------------------//
        setVisible(true);
    }

    public void init() {
        setLayout(null);
        //---------------------------------------------------------------------------------------//
        topLabel = new JLabel();
        topLabel.setText("Register Here");
        Font font = new Font("Arial", Font.BOLD, 18);
        topLabel.setFont(font);
        topLabel.setBounds(60, 20, 300, 30);
        topLabel.setForeground(Color.white);
        //---------------------------------------------------------------------------------------//
        Font labelSize=new Font("Arial",Font.BOLD,16);
        userNameLabel = new JLabel();
        userNameLabel.setText("User Name");
        userNameLabel.setBounds(20, 70, 100, 20);
        userNameLabel.setFont(labelSize);
        userNameLabel.setForeground(Color.WHITE);
        //---------------------------------------------------------------------------------------//
        passwordLabel = new JLabel();
        passwordLabel.setText("Password");
        passwordLabel.setBounds(20, 130, 100, 20);
        passwordLabel.setFont(labelSize);
        passwordLabel.setForeground(Color.WHITE);
        //---------------------------------------------------------------------------------------//
        confirmPasswordLabel=new JLabel();
        confirmPasswordLabel.setText("Confirm Password");
        confirmPasswordLabel.setBounds(20, 180, 150, 20);
        confirmPasswordLabel.setFont(labelSize);
        confirmPasswordLabel.setForeground(Color.WHITE);
        //---------------------------------------------------------------------------------------//
        userTypedLabel = new JLabel("User Type");
        userTypedLabel.setBounds(20, 220, 150, 20);
        userTypedLabel.setForeground(Color.WHITE);
        userTypedLabel.setFont(font);
        //---------------------------------------------------------------------------------------//
        tFUserName = new JTextField();
        tFUserName.setBounds(180, 70, 150, 20);
        tFUserName.addKeyListener(this);
        //---------------------------------------------------------------------------------------//
        pFPassword = new JPasswordField();
        pFPassword.setBounds(180, 130, 150, 20);
        pFPassword.addKeyListener(this);
        //---------------------------------------------------------------------------------------//
        pFConfirmPassword=new JPasswordField();
        pFConfirmPassword.setBounds(180, 180, 150, 20);
        pFConfirmPassword.addKeyListener(this);
        //---------------------------------------------------------------------------------------//
        tFUserType = new JComboBox();
        tFUserType.addItem("Empolyee");
        tFUserType.addItem("Owner");
        tFUserType.setBounds(180, 220, 150, 20);
        tFUserType.addKeyListener(this);
        //---------------------------------------------------------------------------------------//
        btnSignUpForm = new JButton("Sign Up");
        btnSignUpForm.setBounds(250, 270, 90, 30);
        btnSignUpForm.addKeyListener(this);
        btnSignUpForm.addActionListener(this);
        //---------------------------------------------------------------------------------------//
        btnBack = new JButton("Back");
        btnBack.setBounds(10, 270, 65, 30);
        btnBack.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSignUpForm) {
            String userName = tFUserName.getText().toString();
            String password = pFPassword.getText().toString();
            String conPassword = pFConfirmPassword.getText().toString();
            String userType = tFUserType.getSelectedItem().toString();
            if (userName.isEmpty() || password.isEmpty() || conPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Something is missing");
            }
            else{
                if(password.equals(conPassword)){
                    connection=ConnectionConfiguration.getConnection();
                    dbHelper=new DatabaseHelper(connection);
                    if(dbHelper.searchRegistration(userName) != null){
                        JOptionPane.showMessageDialog(this, "User Name Already Exist");
                    } else {
                        if(connection!=null) {
                            try {
                                dbHelper.addRegistrationRecord(userName,password,userType);
                                JOptionPane.showMessageDialog(this, "Created");
                                dispose();
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        }
                        else{
                            System.out.println("Not Connected");
                        }
                    }
                }else {
                    JOptionPane.showMessageDialog(this, "Password is not matched");
                }
            }
        }
        else if(e.getSource()==btnBack){
            dispose();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }



    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (e.getSource() == tFUserName)
                pFPassword.requestFocus();
            if (e.getSource() == pFPassword)
                pFConfirmPassword.requestFocus();
            if (e.getSource() == pFConfirmPassword)
                tFUserType.requestFocus();
            if(e.getSource()==pFConfirmPassword)
                btnSignUpForm.requestFocus();
            if (e.getSource() == btnSignUpForm) {
                String userName = tFUserName.getText().toString();
                String password = pFPassword.getText().toString();
                String conPassword = pFConfirmPassword.getText().toString();
                String userType = tFUserType.getSelectedItem().toString();
                if (userName.isEmpty() || password.isEmpty() || conPassword.isEmpty()||userName.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Something is missing");
                }
                else{
                    if(password.equals(conPassword)){
                        connection=ConnectionConfiguration.getConnection();
                        dbHelper=new DatabaseHelper(connection);
                        if(dbHelper.searchRegistration(userName) != null){
                            JOptionPane.showMessageDialog(this, "User Name Already Exist");
                        } else {
                            if(connection!=null) {
                                try {
                                    dbHelper.addRegistrationRecord(userName,password,userType);
                                    JOptionPane.showMessageDialog(this, "Created");
                                    dispose();
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }
                            }
                            else{
                                System.out.println("Not Connected");
                            }
                        }
                    }else {
                        JOptionPane.showMessageDialog(this, "Password is not matched");
                    }
                }
            }
        }
    }




    @Override
    public void keyReleased(KeyEvent e) {

    }
}


