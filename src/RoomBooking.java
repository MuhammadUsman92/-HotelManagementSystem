import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class RoomBooking<SimpleDateFormomat> extends JFrame implements ActionListener , KeyListener {
    private JLabel roomTypeLabel;
    private JLabel roomPoolLabel;
    private JLabel roomNumberLabel;
    private JLabel background;
    private JLabel nameLabel;
    private JLabel phoneNumberLabel;
    private JLabel cnicLabel;
    private JLabel dateLable;
    //--------------------------------------------------------------------------//
    private ImageIcon icon;
    private ImageIcon bGImage;
    //--------------------------------------------------------------------------//
    private JButton btnBack;
    private JButton btnSave;
    //--------------------------------------------------------------------------//
    private JTextField tFName;
    private JTextField tFPhoneNumber;
    private JTextField tFCnic;

    private JTextPane showBill;
    //--------------------------------------------------------------------------//
    private JDateChooser dCDate;
    //--------------------------------------------------------------------------//
    private JComboBox cBoxRAvialiblity;
    private JComboBox cBoxRoomType;
    private JComboBox cBoxPool;
    //--------------------------------------------------------------------------//
    private String[] roomType;
    private String[] swimmingPool;
    private ArrayList<Room> roomDetals;
    private boolean backFlag;
    private String[] temp2;
    private String price;
    //--------------------------------------------------------------------------//
    private SimpleDateFormat simpleDateFormat;

    public RoomBooking(boolean flag) {
        this.backFlag=flag;
        //--------------------------------------------------------------------------//
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Main Menu");
        setResizable(false);
        //--------------------------------------------------------------------------//
        bGImage = new ImageIcon("Backgroundimage.jpg");
        background = new JLabel("", bGImage, JLabel.CENTER);
        background.setBounds(0, 0, 800,750 );
        add(background);
        //--------------------------------------------------------------------------//
        initi();
        //--------------------------------------------------------------------------//
        background.add(roomTypeLabel);
        background.add(roomPoolLabel);
        background.add(roomNumberLabel);
        background.add(btnBack);
        background.add(nameLabel);
        background.add(phoneNumberLabel);
        background.add(cnicLabel);
        background.add(cBoxRoomType);
        background.add(cBoxPool);
        background.add(cBoxRAvialiblity);
        background.add(tFName);
        background.add(tFPhoneNumber);
        background.add(tFCnic);
        background.add(dateLable);
        background.add(dCDate);
        background.add(showBill);
        background.add(btnBack);
        background.add(btnSave);
        //--------------------------------------------------------------------------//
        setVisible(true);
    }

    public void initi() {
        setLayout(null);
        setBounds(300, 30, 800, 700);
        //--------------------------------------------------------------------------//
        roomType=new String[]{"ONE BAD","TWO BAD","FAMILY ROOM"};
        //--------------------------------------------------------------------------//
        cBoxRoomType = new JComboBox(roomType);
        cBoxRoomType.setBounds(440, 60, 200, 30);
        cBoxRoomType.addActionListener(this);
        //--------------------------------------------------------------------------//
        swimmingPool=new String[]{"NO","YES"};
        //--------------------------------------------------------------------------//
        cBoxPool = new JComboBox(swimmingPool);
        cBoxPool.setBounds(440, 140, 200, 30);
        cBoxPool.addActionListener(this);
        //--------------------------------------------------------------------------//
        icon = new ImageIcon("LoginIcon.jpg");
        setIconImage(icon.getImage());
        //--------------------------------------------------------------------------//
        Font font = new Font("Arial", Font.BOLD, 30);
        roomTypeLabel = new JLabel("Room Type");
        roomTypeLabel.setFont(font);
        roomTypeLabel.setForeground(Color.RED);
        roomTypeLabel.setBounds(120, 60, 500, 30);
        //--------------------------------------------------------------------------//
        btnBack = new JButton("Back");
        btnBack.setBounds(600, 580, 80, 30);
        btnBack.addActionListener(this);
        //--------------------------------------------------------------------------//
        roomPoolLabel=new JLabel("Swimming Pool");
        roomPoolLabel.setBounds(120,140,300,30);
        roomPoolLabel.setFont(font);
        roomPoolLabel.setForeground(Color.RED);
        //--------------------------------------------------------------------------//
        roomNumberLabel=new JLabel("Room Number");
        roomNumberLabel.setBounds(120,200,300,30);
        roomNumberLabel.setFont(font);
        roomNumberLabel.setForeground(Color.RED);
        //--------------------------------------------------------------------------//
        cBoxRAvialiblity = new JComboBox();
        cBoxRAvialiblity.setSelectedItem(null);
        cBoxRAvialiblity.setBounds(440, 200, 200, 30);
        //--------------------------------------------------------------------------//
        nameLabel=new JLabel("Customer Name");
        nameLabel.setBounds(120,260,300,30);
        nameLabel.setFont(font);
        nameLabel.setForeground(Color.RED);
        //--------------------------------------------------------------------------//
        phoneNumberLabel=new JLabel("Phone Number");
        phoneNumberLabel.setBounds(120,320,300,30);
        phoneNumberLabel.setFont(font);
        phoneNumberLabel.setForeground(Color.RED);
        //--------------------------------------------------------------------------//
        cnicLabel=new JLabel("CNIC Number");
        cnicLabel.setBounds(120,380,200,30);
        cnicLabel.setFont(font);
        cnicLabel.setForeground(Color.RED);
        //--------------------------------------------------------------------------//
        dateLable=new JLabel("DATE : ");
        dateLable.setBounds(120,440,120,30);
        dateLable.setForeground(Color.RED);
        dateLable.setFont(font);
        //--------------------------------------------------------------------------//
        tFName=new JTextField();
        tFName.setBounds(440,260,200,30);
        tFName.addKeyListener(this);
        //--------------------------------------------------------------------------//
        tFPhoneNumber=new JTextField();
        tFPhoneNumber.setBounds(440,320,200,30);
        tFPhoneNumber.addKeyListener(this);
        //--------------------------------------------------------------------------//
        tFCnic=new JTextField();
        tFCnic.setBounds(440,380,200,30);
        tFCnic.addKeyListener(this);
        //--------------------------------------------------------------------------//
        dCDate=new JDateChooser();
        dCDate.setBounds(440,440,200,30);
        dCDate.addKeyListener(this);
        //--------------------------------------------------------------------------//
        showBill=new JTextPane();
        showBill.setEditable(false);
        showBill.setBounds(310,500,130,20);
        //--------------------------------------------------------------------------//
        btnSave=new JButton("Save");
        btnSave.setBounds(310,550,100,30);
        btnSave.addActionListener(this);
        //--------------------------------------------------------------------------//
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

    }



    public void dataSet(){
        Connection con= ConnectionConfiguration.getConnection();
        if(con != null) {
            DatabaseHelper dbHelper = new DatabaseHelper(con);
            roomDetals = new ArrayList<>(dbHelper.getAllRooms());
            cBoxRAvialiblity.removeAllItems();
            for (int i = 0; i < roomDetals.size(); i++) {
                if (roomDetals.get(i).getRoomType().equalsIgnoreCase(cBoxRoomType.getSelectedItem().toString())) {
                    if (roomDetals.get(i).getSwimmingPool().equalsIgnoreCase(cBoxPool.getSelectedItem().toString())) {
                        if (roomDetals.get(i).getAvability().equalsIgnoreCase("yes")) {
                            cBoxRAvialiblity.addItem("Room Number :" + roomDetals.get(i).getRoomNumber());
                        }
                    }
                }
            }
        }
    }
    @Override

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cBoxRAvialiblity){
            Connection con = ConnectionConfiguration.getConnection();
            if (con != null) {
                if (cBoxRAvialiblity.getSelectedItem() != null) {
                    cBoxRAvialiblity.addActionListener(this);
                    DatabaseHelper dbHelper = new DatabaseHelper(con);
                    String temp1 = cBoxRAvialiblity.getSelectedItem().toString();
                    temp2 = temp1.split(":");
                    price = dbHelper.getRoomRecord(temp2[1]).getPrice();
                    showBill.setText("Price per day " + price);
                } else {
                    showBill.setText("Price per day 0");
                }
            }
        }
        if(e.getSource()==cBoxRoomType){
            dataSet();
            Connection con = ConnectionConfiguration.getConnection();
            if (con != null) {
                if (cBoxRAvialiblity.getSelectedItem() != null) {
                    cBoxRAvialiblity.addActionListener(this);
                    DatabaseHelper dbHelper = new DatabaseHelper(con);
                    String temp1 = cBoxRAvialiblity.getSelectedItem().toString();
                    temp2 = temp1.split(":");
                    price = dbHelper.getRoomRecord(temp2[1]).getPrice();
                    showBill.setText("Price per day "+price);
                }
                else{
                    showBill.setText("Price per day 0");
                }
            }
        }
        if(e.getSource()==cBoxPool){
            dataSet();
            Connection con = ConnectionConfiguration.getConnection();
            if (con != null) {
                if (cBoxRAvialiblity.getSelectedItem() != null) {
                    cBoxRAvialiblity.addActionListener(this);
                    DatabaseHelper dbHelper = new DatabaseHelper(con);
                    String temp1 = cBoxRAvialiblity.getSelectedItem().toString();
                    temp2 = temp1.split(":");
                    price = dbHelper.getRoomRecord(temp2[1]).getPrice();
                    showBill.setText("Price per day "+price);
                }
                else{
                    showBill.setText("Price per day 0");
                }
            }
        }
        if(e.getSource()==btnSave){
            if(cBoxRAvialiblity.getSelectedItem()!=null) {
                if (!(tFName.getText().isEmpty() && tFCnic.getText().isEmpty() && tFPhoneNumber.getText().isEmpty()) ){
                    Connection con = ConnectionConfiguration.getConnection();
                    if (con != null) {
                        DatabaseHelper dbHelper = new DatabaseHelper(con);
                        String temp1 = cBoxRAvialiblity.getSelectedItem().toString();
                        temp2 = temp1.split(":");
                        try {
                            dbHelper.updateRoomRecord(temp2[1]);

                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        try {
                            String date = simpleDateFormat.format(dCDate.getDate());
                            System.out.println(tFName.toString());
                            dbHelper.addRoomRecordById(temp2[1], tFName.getText().toString(), tFPhoneNumber.getText().toString(), tFCnic.getText().toString(), date);
                            JOptionPane.showMessageDialog(this,"Room Booked");
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                }
                else {
                    JOptionPane.showMessageDialog(this,"Something is missing");
                }
            }
            else {
                JOptionPane.showMessageDialog(this,"No Room is Available");
            }
        }
        if(e.getSource()==btnBack){
            if(backFlag == true){
                dispose();
                new MainMenu();
            }
            else if(backFlag == false){
                dispose();
                new MainMenuForEmpolyee();
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (Character.isLetter(e.getKeyChar())) {
            if (e.getSource() == tFPhoneNumber) {
                tFPhoneNumber.setEditable(false);
                JOptionPane.showMessageDialog(this, "Enter Only Number");
            } else if (e.getSource() == tFCnic) {
                tFCnic.setEditable(false);
                JOptionPane.showMessageDialog(this, "Enter Only Number");
            } else if (e.getSource() == dCDate) {

                JOptionPane.showMessageDialog(this, "Enter Only Number");
            }
        } else {
            if (e.getSource() == tFPhoneNumber) {
                tFPhoneNumber.setEditable(true);
            } else if (e.getSource() == tFCnic) {
                tFCnic.setEditable(true);
            } else if (e.getSource() == dCDate) {
                //dCDate.setEditable(true);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (e.getSource() == tFName) {
                tFPhoneNumber.requestFocus();
            } else if (e.getSource() == tFPhoneNumber) {
                tFCnic.requestFocus();
            } else if (e.getSource() == tFCnic) {
                dCDate.requestFocus();
            } else if (e.getSource() == dCDate) {
                btnSave.requestFocus();
            } else if (e.getSource() == btnSave) {
                if(cBoxRAvialiblity.getSelectedItem()!=null) {
                    if (!(tFName.getText().isEmpty() && tFCnic.getText().isEmpty() && tFPhoneNumber.getText().isEmpty()) ){
                        Connection con = ConnectionConfiguration.getConnection();
                        if (con != null) {
                            DatabaseHelper dbHelper = new DatabaseHelper(con);
                            String temp1 = cBoxRAvialiblity.getSelectedItem().toString();
                            temp2 = temp1.split(":");
                            try {
                                dbHelper.updateRoomRecord(temp2[1]);

                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                            try {
                                String date = simpleDateFormat.format(dCDate.getDate());
                                System.out.println(tFName.toString());
                                dbHelper.addRoomRecordById(temp2[1], tFName.getText().toString(), tFPhoneNumber.getText().toString(), tFCnic.getText().toString(), date);
                                JOptionPane.showMessageDialog(this,"Room Booked");
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(this,"Something is missing");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(this,"No Room is Available");
                }
            }
        }
    }
        @Override
        public void keyReleased (KeyEvent e){

        }
    }

