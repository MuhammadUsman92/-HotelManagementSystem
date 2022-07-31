import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainMenuForEmpolyee extends JFrame implements ActionListener {
    private JLabel topLabel;
    private JLabel background;
    //--------------------------------------------------------------------------//
    private ImageIcon icon;
    private ImageIcon bGImage;
    private ImageIcon roomIcon;
    private ImageIcon bookedIcon;
    private ImageIcon billIcon;
    private ImageIcon foodOrderIcon;
    //--------------------------------------------------------------------------//
    private JButton btnRoom;
    private JButton btnBooked;
    private JButton btnBill;
    private JButton btnFoodOrder;
    private JButton btnLogout;
    //--------------------------------------------------------------------------//
    private Choose choose;


    public MainMenuForEmpolyee() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Main Menu");
        setResizable(false);
        //--------------------------------------------------------------------------//
        initi();
        //--------------------------------------------------------------------------//
        bGImage = new ImageIcon("Backgroundimage.jpg");
        background = new JLabel("", bGImage, JLabel.CENTER);
        background.setBounds(0, 0, 800,750 );
        add(background);
        //--------------------------------------------------------------------------//
        background.add(btnRoom);
        background.add(btnBooked);
        background.add(btnFoodOrder);
        background.add(topLabel);
        background.add(btnLogout);
        background.add(btnBill);

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
        topLabel=new JLabel("Main Menu");
        Font font = new Font("Arial",Font.BOLD,28);
        topLabel.setFont(font);
        topLabel.setForeground(Color.WHITE);
        topLabel.setBounds(330,50,300,100);
        //--------------------------------------------------------------------------//
        roomIcon=new ImageIcon("roomIcon.png");
        btnRoom=new JButton(roomIcon);
        btnRoom.setBorder(null);
        btnRoom.setBorderPainted(false);
        btnRoom.setContentAreaFilled(false);
        btnRoom.setOpaque(false);
        btnRoom.setBounds(130,130,230,250);
        btnRoom.addActionListener(this);
        //--------------------------------------------------------------------------//
        bookedIcon=new ImageIcon("bookedImage.png");
        btnBooked=new JButton(bookedIcon);
        btnBooked.setBorder(null);
        btnBooked.setBorderPainted(false);
        btnBooked.setContentAreaFilled(false);
        btnBooked.setOpaque(false);
        btnBooked.setBounds(460,130,230,250);
        btnBooked.addActionListener(this);
        billIcon =new ImageIcon("Bill.png");
        btnBill =new JButton("",billIcon);
        btnBill.setBounds(120,370,230,250);
        btnBill.setBorder(null);
        btnBill.setBorderPainted(false);
        btnBill.setContentAreaFilled(false);
        btnBill.setOpaque(false);
        btnBill.addActionListener(this);
        //--------------------------------------------------------------------------//
        foodOrderIcon=new ImageIcon("foodIcon.png");
        btnFoodOrder = new JButton("",foodOrderIcon);
        btnFoodOrder.setBounds(450,350,230,250);
        btnFoodOrder.setBorder(null);
        btnFoodOrder.setBorderPainted(false);
        btnFoodOrder.setContentAreaFilled(false);
        btnFoodOrder.setOpaque(false);
        btnFoodOrder.addActionListener(this);
        //--------------------------------------------------------------------------//
        btnLogout = new JButton("Log out");
        btnLogout.setBounds(680, 660, 80, 30);
        btnLogout.addActionListener(this);
        //--------------------------------------------------------------------------//
    }
    public void removeWindow(){
        dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnLogout) {
            removeWindow();
            new Choose();
        }
        if(e.getSource()==btnBooked){
            removeWindow();
            new RoomDetails(false);
        }
        if(e.getSource()==btnRoom){
            removeWindow();
            new RoomBooking(false);
        }
        if(e.getSource()==btnFoodOrder){
            removeWindow();
            try {
                new FoodOrder(false);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(e.getSource()==btnBill){
            removeWindow();
            new BillGenerate(true);
        }
    }
}

