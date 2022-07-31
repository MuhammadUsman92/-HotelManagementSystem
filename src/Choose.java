import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Choose extends JFrame implements ActionListener {
    //---------------------------------------------------------------------------------------//
    private JButton btnOwner;
    private JButton btnEmpolyee;
    //---------------------------------------------------------------------------------------//
    private ImageIcon icon;
    //---------------------------------------------------------------------------------------//
    private JLabel topLabel;
    private JLabel background;
    //---------------------------------------------------------------------------------------//
    private ImageIcon bGImage;
    private ImageIcon ownerIcon;
    private ImageIcon empolyeeIcon;
    //---------------------------------------------------------------------------------------//


    public Choose() {
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Choose");
        setBounds(300, 100, 500, 450);
        //---------------------------------------------------------------------------------------//
        init();
        //---------------------------------------------------------------------------------------//
        icon = new ImageIcon("LoginIcon.jpg");
        bGImage = new ImageIcon("Backgroundimage.jpg");
        setIconImage(icon.getImage());
        //---------------------------------------------------------------------------------------//
        background = new JLabel("", bGImage, JLabel.CENTER);
        background.setBounds(0, 0, 500, 450);
        add(background);
        //---------------------------------------------------------------------------------------//
        background.add(btnOwner);
        background.add(btnEmpolyee);
        background.add(topLabel);
        setVisible(true);
    }
    //---------------------------------------------------------------------------------------//

    public void init() {
        setLayout(null);
        topLabel=new JLabel("Choose");
        Font font=new Font("Arial",Font.BOLD,45);
        topLabel.setFont(font);
        topLabel.setForeground(Color.WHITE);
        topLabel.setBounds(165,90,200,50);
        //---------------------------------------------------------------------------------------//
        ownerIcon=new ImageIcon("ownerIcon.png");
        btnOwner=new JButton(ownerIcon);
        btnOwner.setBorder(null);
        btnOwner.setBorderPainted(false);
        btnOwner.setContentAreaFilled(false);
        btnOwner.setOpaque(false);
        btnOwner.setBounds(50,150,150,200);
        btnOwner.addActionListener(this);
        //---------------------------------------------------------------------------------------//
        empolyeeIcon=new ImageIcon("empolyeeIcon.png");
        btnEmpolyee=new JButton(empolyeeIcon);
        btnEmpolyee.setBorder(null);
        btnEmpolyee.setBorderPainted(false);
        btnEmpolyee.setContentAreaFilled(false);
        btnEmpolyee.setOpaque(false);
        btnEmpolyee.setBounds(300,163,150,200);
        btnEmpolyee.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //---------------------------------------------------------------------------------------//
        if(e.getSource()==btnEmpolyee) {
            new EmployeeLogin();
            dispose();
        }
        //---------------------------------------------------------------------------------------//
        if(e.getSource()==btnOwner){
            new Login();
            dispose();
        }

    }
}