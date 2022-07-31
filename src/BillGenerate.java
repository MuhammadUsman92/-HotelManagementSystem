import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class BillGenerate extends JFrame implements ActionListener {
    private JLabel topLabel;
    private JLabel bookedRoomLabel;
    private JLabel background;
    private JLabel dateLabel;
    //--------------------------------------------------------------------------//
    private JTextPane billShowLabel;
    //--------------------------------------------------------------------------//
    private ImageIcon icon;
    private ImageIcon bGImage;
    //--------------------------------------------------------------------------//
    private JButton btnBack;
    private JButton btnUpdate;
    private JButton btnGenerate;
    //--------------------------------------------------------------------------//
    private JDateChooser dCDate;
    //--------------------------------------------------------------------------//
    private JComboBox comboBox;
    //--------------------------------------------------------------------------//
    private String[] booked;
    private ArrayList<Room> roomDetals;
    private boolean backFlag;
    private String[] temp2;
    private long  totalBill;
    //--------------------------------------------------------------------------//
    private SimpleDateFormat simpleDateFormat;

    private DaysFinder dayFinder;

    public BillGenerate(boolean flag) {
        this.backFlag = flag;
        //--------------------------------------------------------------------------//
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Main Menu");
        setResizable(false);
        //--------------------------------------------------------------------------//
        dataSet();
        //--------------------------------------------------------------------------//
        initi();
        //--------------------------------------------------------------------------//
        bGImage = new ImageIcon("Backgroundimage.jpg");
        background = new JLabel("", bGImage, JLabel.CENTER);
        background.setBounds(0, 0, 800, 750);
        add(background);
        //--------------------------------------------------------------------------//
        background.add(topLabel);
        background.add(btnBack);
        background.add(comboBox);
        background.add(dateLabel);
        background.add(dCDate);
        background.add(btnBack);
        background.add(btnUpdate);
        background.add(comboBox);
        background.add(bookedRoomLabel);
        background.add(billShowLabel);
        background.add(btnGenerate);
        //--------------------------------------------------------------------------//
        setVisible(true);
    }

    public void initi() {
        setLayout(null);
        setBounds(300, 30, 800, 700);
        //--------------------------------------------------------------------------//
        Font font = new Font("Arial", Font.BOLD, 30);
        topLabel = new JLabel("ROOMS BILL");
        topLabel.setFont(font);
        topLabel.setForeground(Color.WHITE);
        topLabel.setBounds(300, 30, 500, 100);
        //--------------------------------------------------------------------------//
        comboBox = new JComboBox(booked);
        comboBox.setSelectedItem(null);
        comboBox.setBounds(290, 130, 200, 30);
        comboBox.addActionListener(this);
        //--------------------------------------------------------------------------//
        icon = new ImageIcon("LoginIcon.jpg");
        setIconImage(icon.getImage());
        //--------------------------------------------------------------------------//
        bookedRoomLabel =new JLabel("Rooms : ");
        bookedRoomLabel.setBounds(330, 100, 200, 30);
        bookedRoomLabel.setForeground(Color.RED);
        bookedRoomLabel.setFont(font);
        //--------------------------------------------------------------------------//
        dateLabel = new JLabel("DATE : ");
        dateLabel.setBounds(330, 190, 120, 30);
        dateLabel.setForeground(Color.RED);
        dateLabel.setFont(font);
        //--------------------------------------------------------------------------//
        Font fontSize = new Font("Arial", Font.BOLD, 20);
        billShowLabel=new JTextPane();
        billShowLabel.setBounds(100,380,600,200);
        billShowLabel.setForeground(Color.RED);
        billShowLabel.setFont(fontSize);
        billShowLabel.setEditable(false);
        //--------------------------------------------------------------------------//
        dCDate = new JDateChooser();
        dCDate.setBounds(290, 220, 200, 30);
        //--------------------------------------------------------------------------//
        btnBack = new JButton("Back");
        btnBack.setBounds(640, 620, 80, 30);
        btnBack.addActionListener(this);
        //--------------------------------------------------------------------------//
        btnGenerate=new JButton("Generate");
        btnGenerate.setBounds(330,580,100,30);
        btnGenerate.addActionListener(this);
        //--------------------------------------------------------------------------//
        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(330, 620, 100, 30);
        btnUpdate.addActionListener(this);
        //--------------------------------------------------------------------------//
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

    }


    public void dataSet() {
        Connection con = ConnectionConfiguration.getConnection();
        if (con != null) {
            DatabaseHelper dbHelper = new DatabaseHelper(con);
            roomDetals = new ArrayList<>(dbHelper.getAllRooms());
            booked = new String[roomDetals.size()];
            for (int i = 0; i < roomDetals.size(); i++) {
                if (roomDetals.get(i).getAvability().equalsIgnoreCase("No")) {
                    booked[i] = "Room Number :" + roomDetals.get(i).getRoomNumber();
                }
            }
        }
    }


    @Override

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGenerate) {
            Connection con = ConnectionConfiguration.getConnection();
            if (con != null) {
                DatabaseHelper dbHelper = new DatabaseHelper(con);
                String temp1 = comboBox.getSelectedItem().toString();
                temp2 = temp1.split(":");
                    String date=simpleDateFormat.format(dCDate.getDate());
                try {
                    dbHelper.addOutDate(temp2[1],date);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                Room record=dbHelper.getRoomRecord(temp2[1]);
                SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date dateBefore = null;
                try {
                    dateBefore = myFormat.parse(record.getinDate());
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
                Date dateAfter = null;
                try {
                    dateAfter = myFormat.parse(record.getOutDate());
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
                long days = Math.abs((dateAfter.getTime() - dateBefore.getTime())/ (1000 * 60 * 60 * 24));;
                if(days==0){
                    days=1;
                }
                long totalBill =(Integer.parseInt(record.getPrice())) *days;
                totalBill=totalBill+Integer.parseInt(record.getFoodCharges());
                billShowLabel.setText(record.toString()+"\n"+"\t\t"+"Total Bill:\t"+totalBill);
            }
        }
        if(e.getSource() == btnUpdate){
            Connection con= ConnectionConfiguration.getConnection();
            if(con != null) {
                DatabaseHelper dbHelper = new DatabaseHelper(con);
                String temp1=comboBox.getSelectedItem().toString();
                temp2=temp1.split(":");
                try {
                    dbHelper.updateRoomRecord(temp2[1]);
                    JOptionPane.showMessageDialog(this,"Updated Thank you!");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        if (e.getSource() == btnBack) {
                if (backFlag == true) {
                    dispose();
                    new MainMenu();
                } else if (backFlag == false) {
                    dispose();
                    new MainMenuForEmpolyee();
                }
            }

        }

    }


