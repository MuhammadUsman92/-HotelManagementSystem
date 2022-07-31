import javax.swing .*;
import java.awt .*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
public class FoodOrder extends JFrame implements ActionListener {
    private ImageIcon icon;
    private ImageIcon bGImage;
    private JLabel topLabel;
    private JLabel background;
    private JTextPane tPShow;
    private JButton btnBack;
    private JButton btnOrder;
    private JScrollPane Sp;
    private JLabel drinksLabel;
    private JLabel mealLabel;
    private JLabel desertsLabel;
    private JComboBox cBoxDrink;
    private JComboBox cBoxMeal;
    private JComboBox cBoxDeserts;
    private JComboBox comboBox;
    private String[] drink;
    private String[] meal;
    private String[] deserts;
    private Boolean flag;
    private int countMeal = 0;
    private int countDrink = 0;
    private int countDeserts = 0;
    private String[] booked;
    private  Connection con;
    private ArrayList<Room> roomDetals;
    private DatabaseHelper dbhelper;
    private long charges=0;
    private String[] temp2;

    public FoodOrder(Boolean flag) throws SQLException {
        this.flag = flag;
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Food Menu");
        setBounds(300, 30, 800, 600);
        dataSet();
        init();
        icon = new ImageIcon("LoginIcon.jpg");
        bGImage = new ImageIcon("Backgroundimage.jpg");
        setIconImage(icon.getImage());
        background = new JLabel("", bGImage, JLabel.CENTER);
        background.setBounds(0, 0, 800, 750);
        add(background);
        background.add(topLabel);
        background.add(btnBack);
        background.add(Sp);
        background.add(drinksLabel);
        background.add(mealLabel);
        background.add(desertsLabel);
        background.add(cBoxDrink);
        background.add(cBoxMeal);
        background.add(cBoxDeserts);
        background.add(comboBox);
        background.add(btnOrder);
        setVisible(true);
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


    public void setRecord() throws SQLException {
        con = ConnectionConfiguration.getConnection();
        dbhelper = new DatabaseHelper(con);
        ArrayList<Food> food = dbhelper.getFoodByType("Drink");
        drink = new String[food.size()];
        for (int i = 0; i < food.size(); i++) {
            cBoxDrink.addItem(food.get(i).getItemName());
        }
        food = dbhelper.getFoodByType("Meal");
        meal = new String[food.size()];
        for (int i = 0; i < food.size(); i++) {
            cBoxMeal.addItem(food.get(i).getItemName());
        }
        food = dbhelper.getFoodByType("Deserts");
        deserts = new String[food.size()];
        for (int i = 0; i < food.size(); i++) {
            cBoxDeserts.addItem(food.get(i).getItemName());
        }

    }

    public void init() throws SQLException {
        setLayout(null);
        topLabel = new JLabel("Order Here");
        Font font = new Font("Arial", Font.BOLD, 35);
        Font font2 = new Font("Arial", Font.BOLD, 30);
        topLabel.setFont(font);
        topLabel.setForeground(Color.WHITE);
        topLabel.setBounds(300, 70, 330, 40);
        drinksLabel = new JLabel("Drinks");
        drinksLabel.setBounds(200, 150, 50, 20);
        drinksLabel.setForeground(Color.RED);
        mealLabel = new JLabel("Meals");
        mealLabel.setBounds(350, 150, 50, 20);
        mealLabel.setForeground(Color.RED);
        desertsLabel = new JLabel("Deserts");
        desertsLabel.setBounds(520, 150, 50, 20);
        desertsLabel.setForeground(Color.RED);
        cBoxDrink = new JComboBox();
        cBoxDrink.addActionListener(this);
        cBoxDrink.setBounds(150, 170, 150, 30);
        cBoxMeal = new JComboBox();
        cBoxMeal.setBounds(320, 170, 150, 30);
        cBoxMeal.addActionListener(this);
        cBoxDeserts = new JComboBox();
        cBoxDeserts.setBounds(490, 170, 150, 30);
        cBoxDeserts.addActionListener(this);
        tPShow = new JTextPane();
        tPShow.setEditable(false);
        tPShow.setFont(font2);
        Sp = new JScrollPane(tPShow);
        Sp.setBounds(220, 220, 300, 200);
        btnOrder = new JButton("Order");
        btnOrder.setBounds(330, 450, 70, 30);
        btnOrder.addActionListener(this);
        btnBack = new JButton("Back");
        btnBack.setBounds(610, 500, 80, 30);
        btnBack.addActionListener(this);
        setRecord();
        comboBox = new JComboBox(booked);
        comboBox.setSelectedItem(null);
        comboBox.setBounds(290, 110, 200, 30);
        comboBox.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            dispose();
            if (flag == true)
                new MainMenu();
            else
                new MainMenu();
        }
        if(e.getSource()==btnOrder){
            if(comboBox.getSelectedItem() == null){
                JOptionPane.showMessageDialog(this,"Please Select Room");
            }else {
                String temp1 = comboBox.getSelectedItem().toString();
                temp2 = temp1.split(":");
                try {
                    dbhelper.addFoodCharges(temp2[1],charges+"");
                    JOptionPane.showMessageDialog(this,"Order Down Price is "+charges);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        if (e.getSource() == cBoxMeal) {
            if (countMeal != 0) {
                String temp = null;
                try {
                    temp = dbhelper.getfood(cBoxMeal.getSelectedItem().toString()).toString();
                    charges=charges + Integer.parseInt(dbhelper.getfood(cBoxMeal.getSelectedItem().toString()).getPrice());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                if (!(tPShow.getText().isEmpty())) {
                    temp = tPShow.getText() + "\n" + temp;
                }
                tPShow.setText(temp);
            }
            countMeal++;
        }
        if (e.getSource() == cBoxDrink) {
            if (countDrink != 0) {
                String temp = null;
                try {
                    temp = dbhelper.getfood(cBoxDrink.getSelectedItem().toString()).toString();
                    charges=charges + Integer.parseInt(dbhelper.getfood(cBoxDrink.getSelectedItem().toString()).getPrice());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                if (!(tPShow.getText().isEmpty())) {
                    temp = tPShow.getText() + "\n" + temp;
                }
                tPShow.setText(temp);
            }
            countDrink++;
        }
        if (e.getSource() == cBoxDeserts) {
            if (countDeserts != 0) {
                String temp = null;
                try {
                    temp = dbhelper.getfood(cBoxDeserts.getSelectedItem().toString()).toString();
                    charges=charges + Integer.parseInt(dbhelper.getfood(cBoxDeserts.getSelectedItem().toString()).getPrice());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                if (!(tPShow.getText().isEmpty())) {
                        temp = tPShow.getText() + "\n" + temp;
                }
                    tPShow.setText(temp);
            }
            countDeserts++;
        }

    }
}