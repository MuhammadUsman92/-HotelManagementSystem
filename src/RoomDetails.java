import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;

public class RoomDetails extends JFrame implements ActionListener {
    //--------------------------------------------------------------------------//
    private JLabel topLabel;
    private JLabel background;
    private JLabel totalRoomsLabel;
    private JLabel bookedRoomsLabel;
    private JLabel avaliableRoomsLabel;
    private JLabel oneBedLabel;
    private JLabel twoBedLabel;
    private JLabel familyRoomLabel;
    private JLabel swimmingPoolLabel;
    //--------------------------------------------------------------------------//
    private ImageIcon icon;
    private ImageIcon bGImage;
    //--------------------------------------------------------------------------//
    private JButton btnBack;
    //--------------------------------------------------------------------------//
    private Choose choose;
    //--------------------------------------------------------------------------//
    private boolean backFlag;
    private int totalRooms;
    private int bookedRooms;
    private int avaliableRooms;
    private int oneBed;
    private int twoBed;
    private int  familyRoom;
    private int swimmingPool;

    public RoomDetails(boolean flag) {
        this.backFlag=flag;
        //--------------------------------------------------------------------------//
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Main Menu");
        setResizable(false);
        //--------------------------------------------------------------------------//
        initi();
        //--------------------------------------------------------------------------//
        dataSet();
        //--------------------------------------------------------------------------//
        roomsDisplays();
        //--------------------------------------------------------------------------//
        bGImage = new ImageIcon("Backgroundimage.jpg");
        background = new JLabel("", bGImage, JLabel.CENTER);
        background.setBounds(0, 0, 800,750 );
        add(background);
        //--------------------------------------------------------------------------//
        background.add(topLabel);
        background.add(btnBack);
        //--------------------------------------------------------------------------//
        setVisible(true);
    }
    public void initi() {
        setLayout(null);
        setBounds(300, 30, 800, 750);
        //--------------------------------------------------------------------------//
        icon = new ImageIcon("LoginIcon.jpg");
        setIconImage(icon.getImage());
        //--------------------------------------------------------------------------//
        topLabel=new JLabel("Rooms Details");
        Font font = new Font("Arial",Font.BOLD,60);
        topLabel.setFont(font);
        topLabel.setForeground(Color.WHITE);
        topLabel.setBounds(210,20,500,100);
        //--------------------------------------------------------------------------//
        btnBack = new JButton("Back");
        btnBack.setBounds(680, 660, 80, 30);
        btnBack.addActionListener(this);
    }

    public void dataSet(){
        Connection con= ConnectionConfiguration.getConnection();
        if(con != null) {
            totalRooms=0;
            bookedRooms=0;
            avaliableRooms=0;
            oneBed=0;
            twoBed=0;
            familyRoom=0;
            swimmingPool=0;
            DatabaseHelper dbhelper = new DatabaseHelper(con);
            ArrayList<Room> roomDetals=new ArrayList<>(dbhelper.getAllRooms());
            totalRooms =roomDetals.size();
            for(int i=0;i<roomDetals.size();i++) {
                if (roomDetals.get(i).getAvability().equalsIgnoreCase("yes")) {
                    avaliableRooms++;
                }
                if (roomDetals.get(i).getAvability().equalsIgnoreCase("no")) {
                    bookedRooms++;
                }
                if (roomDetals.get(i).getSwimmingPool().equalsIgnoreCase("yes")){
                    swimmingPool++;
                }
                if (roomDetals.get(i).getRoomType().equalsIgnoreCase("ONE BAD")){
                    oneBed++;
                }
                else if (roomDetals.get(i).getRoomType().equalsIgnoreCase("two bad")){
                    twoBed++;
                }
                else if(roomDetals.get(i).getRoomType().equalsIgnoreCase("family room")){
                    familyRoom++;
                }
            }
        } else {
            JOptionPane.showMessageDialog(this,"You are offline");
        }
    }

    public void roomsDisplays()
    {
        setLayout(null);
        Font font = new Font("Arial",Font.BOLD,60);
        //--------------------------------------------------------------------------//
        JLabel totalRoomsLabel = new JLabel("Total Rooms : "+totalRooms);
        totalRoomsLabel.setForeground(Color.RED);
        totalRoomsLabel.setFont(font);
        totalRoomsLabel.setBounds(100,80,800,100);
        add(totalRoomsLabel);
        //--------------------------------------------------------------------------//
        JLabel bookedRoomsLabel = new JLabel("Total Booked Rooms : "+bookedRooms);
        bookedRoomsLabel.setFont(font);
        bookedRoomsLabel.setForeground(Color.RED);
        bookedRoomsLabel.setBounds(20,160,800,100);
        add(bookedRoomsLabel);
        //--------------------------------------------------------------------------//
        JLabel avaliableRoomsLabel = new JLabel("Avaliable Rooms : "+avaliableRooms);
        avaliableRoomsLabel.setForeground(Color.RED);
        avaliableRoomsLabel.setFont(font);
        avaliableRoomsLabel.setBounds(100,240,800,100);
        add(avaliableRoomsLabel);
        //--------------------------------------------------------------------------//
        JLabel oneBedLabel= new JLabel("One Bed Rooms : "+oneBed);
        oneBedLabel.setForeground(Color.RED);
        oneBedLabel.setFont(font);
        oneBedLabel.setBounds(90,320,800,100);
        add(oneBedLabel);
        //--------------------------------------------------------------------------//
        JLabel twoBedLabel= new JLabel("Two Beds Rooms : "+twoBed);
        twoBedLabel.setForeground(Color.RED);
        twoBedLabel.setFont(font);
        twoBedLabel.setBounds(60,400,800,100);
        add(twoBedLabel);
        //--------------------------------------------------------------------------//
        JLabel familyRoomLabel= new JLabel("Family Room : "+familyRoom);
        familyRoomLabel.setForeground(Color.RED);
        familyRoomLabel.setFont(font);
        familyRoomLabel.setBounds(110,480,800,100);
        add(familyRoomLabel);
        //--------------------------------------------------------------------------//
        JLabel swimmingPoolLabel= new JLabel("Swimming Pools : "+swimmingPool);
        swimmingPoolLabel.setForeground(Color.RED);
        swimmingPoolLabel.setFont(font);
        swimmingPoolLabel.setBounds(100,560,800,100);
        add(swimmingPoolLabel);
    }
    public void removeWindow(){
        dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnBack)
        {
            if(backFlag == true){
                removeWindow();
                new MainMenu();
            }
            else if(backFlag == false){
                removeWindow();
                new MainMenuForEmpolyee();
            }
        }
    }
}
