import javax.swing .*;
import java.awt .*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeRecord extends JFrame implements ActionListener{
    private ImageIcon icon;
    private ImageIcon bGImage;
    //--------------------------------------------------------------------------//
    private JLabel topLabel;
    private JLabel background;
    private JTextPane jTPRecord;
    //--------------------------------------------------------------------------//
    private JButton btnBack;
    //--------------------------------------------------------------------------//
    private JScrollPane Sp;


    public EmployeeRecord() throws SQLException {
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Choose");
        setBounds(300, 30, 800, 750);
        //--------------------------------------------------------------------------//
        init();
        setRecord();
        //--------------------------------------------------------------------------//
        icon = new ImageIcon("LoginIcon.jpg");
        bGImage = new ImageIcon("Backgroundimage.jpg");
        setIconImage(icon.getImage());
        background = new JLabel("", bGImage, JLabel.CENTER);
        background.setBounds(0, 0, 800, 750);
        //--------------------------------------------------------------------------//
        add(background);
        background.add(topLabel);
        background.add(btnBack);
        background.add(Sp);
        //--------------------------------------------------------------------------//
        setVisible(true);
    }
    public void setRecord() throws SQLException {
        Connection con;
        con=ConnectionConfiguration.getConnection();
        DatabaseHelper dbHelper=new DatabaseHelper(con);
        ArrayList<User> alluser;
        alluser=dbHelper.getRegisterationRecord();
        for(int i=0;i<alluser.size();i++){
            if(i==0) {
                jTPRecord.setText("\t\t  Record No. " + (i+1) + "\n" + alluser.get(i).toString()+"\n");
            }else{
                String temp=jTPRecord.getText();
                jTPRecord.setText(temp+"\t\t  Record No. " + (i+1) + "\n" + alluser.get(i).toString()+"\n");
            }
        }
    }
    public void init() {
        setLayout(null);
        topLabel = new JLabel("Employees Details");
        Font font = new Font("Arial", Font.BOLD, 35);
        Font font2 = new Font("Arial", Font.BOLD, 30);
        //--------------------------------------------------------------------------//
        topLabel.setFont(font);
        topLabel.setForeground(Color.WHITE);
        topLabel.setBounds(220, 70, 330, 40);
        //--------------------------------------------------------------------------//
        jTPRecord=new JTextPane();
        jTPRecord.setEditable(false);
        jTPRecord.setFont(font2);
        //--------------------------------------------------------------------------//
        Sp=new JScrollPane(jTPRecord);
        Sp.setBounds(100,150,580,500);
        //--------------------------------------------------------------------------//
        btnBack=new JButton("Back");
        btnBack.setBounds(680, 660, 80, 30);
        btnBack.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnBack){
            dispose();
            new MainMenu();
        }
    }
}
