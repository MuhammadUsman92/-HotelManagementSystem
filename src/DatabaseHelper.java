import java.sql.*;
import java.util.ArrayList;

public class DatabaseHelper {
    Connection connection;

    public DatabaseHelper(Connection connection) {
        this.connection = connection;
    }
    public void createRegistrationTable() throws SQLException {
        String query ="CREATE TABLE IF NOT EXISTS registeration (user_name varchar(55) primary key unique ,password varchar(55),user_type varchar(55))";
        Statement statement = connection.createStatement();
        statement.execute(query);
    }
    public void  addRegistrationRecord(String name,String password,String type) throws SQLException {
        String query = "INSERT INTO registeration(user_name,password,user_type)VALUES(?,?,?)";
        PreparedStatement ppStatement = connection.prepareStatement(query);
        ppStatement.setString(1,name);
        ppStatement.setString(2,password);
        ppStatement.setString(3,type);
        ppStatement.execute();
    }
    public User getRecord(String name){
        User user = new User();
        String query = "SELECT * FROM registeration where user_name=?";
        try{

            PreparedStatement ppStatement = connection.prepareStatement(query);
            ppStatement.setString(1, name);
            ResultSet rs = ppStatement.executeQuery();
            rs.absolute(1);
            user.setUser_name(rs.getString(1));
            user.setPassword(rs.getString(2));
            user.setType(rs.getString(3));
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }
    public String searchRegistration(String name)  {
        String query = "SELECT * FROM registeration where user_name=?";
        try{
            PreparedStatement ppStatement = connection.prepareStatement(query);
            ppStatement.setString(1, name);
            ResultSet rs = ppStatement.executeQuery();
            rs.absolute(1);
            return rs.getString(1);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<User> getRegisterationRecord() throws SQLException {
        String query ="SELECT * FROM registeration";
        PreparedStatement ppStatement=connection.prepareStatement(query);
        ResultSet rs = ppStatement.executeQuery();
        ArrayList<User> allUsers=new ArrayList<>();
        while(rs.next())
        {
            User user=new User(rs.getString(1),rs.getString(2),rs.getString(3));
            allUsers.add(user);
        }
        return allUsers;
    }

    public void createRoomTable() throws SQLException {
        String query ="CREATE TABLE IF NOT EXISTS room_table (room_number int primary key unique auto_increment," +
                "_avability varchar(55)," +
                "room_type varchar(55),swimming_pool varchar(55),ac varchar(55),price int,rented_by " +
                "varchar(55),number varchar(55),id_card varchar(55),in_date varchar(55),out_date varchar(55),food_charges varchar (55))";
        Statement statement = connection.createStatement();
        try {
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addRoomRecord(String avability,String roomNumber,String swimming, String ac,String rented,String price,String number,String cardID,String inDate,String outDate) throws SQLException {
        String query = "INSERT INTO room_table(_avability,room_type,swimming_pool,ac,price," +
                "rented_by ,number,id_card,in_date,out_date)VALUE (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ppStatement = connection.prepareStatement(query);
        ppStatement.setString(1,avability);
        ppStatement.setString(2,roomNumber);
        ppStatement.setString(3,swimming);
        ppStatement.setString(4,ac);
        ppStatement.setString(5,price);
        ppStatement.setString(6,rented);
        ppStatement.setString(7,number);
        ppStatement.setString(8,cardID);
        ppStatement.setString(9,inDate);
        ppStatement.setString(10,outDate);
        ppStatement.execute();
    }

    public Room getRoomRecord(String number){
         Room room = new Room();
        String query = "SELECT * FROM room_table where room_number=?";
        try{

            PreparedStatement ppStatement = connection.prepareStatement(query);
            ppStatement.setString(1, number);
            ResultSet rs = ppStatement.executeQuery();
            rs.absolute(1);
            room.setRoomNumber(rs.getString(1));
            room.setAvability(rs.getString(2));
            room.setRoomType(rs.getString(3));
            room.setSwimmingPool(rs.getString(4));
            room.setAc(rs.getString(5));
            room.setPrice(rs.getString(6));
            room.setRented(rs.getString(7));
            room.setPhoneNumber(rs.getString(8));
            room.setIdCard(rs.getString(9));
            room.setInDate(rs.getString(10));
            room.setOutDate(rs.getString(11));
            room.setFoodCharges(rs.getString(12));

        }catch (SQLException e){
            e.printStackTrace();
        }
        return room;
    }

    public ArrayList<Room> getAllRooms(){
        ArrayList<Room> array= new ArrayList<>();
        String query = "SELECT * FROM room_table";
        try{

            PreparedStatement ppStatement = connection.prepareStatement(query);
            ResultSet rs = ppStatement.executeQuery();
            while (rs.next()) {
                Room room=new Room(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),
                        rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),
                        rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));
                array.add(room);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return array;
    }

//    public ArrayList<Room> getRoomByAvability(String avability){
//        ArrayList<Room> array= new ArrayList<>();
//        String query = "SELECT * FROM room_table where _avability=?";
//        try{
//
//            PreparedStatement ppStatement = connection.prepareStatement(query);
//            ppStatement.setString(1, avability);
//            ResultSet rs = ppStatement.executeQuery();
//            while (rs.next()) {
//                Room room=new Room(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),
//                        rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),
//                        rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));
//                array.add(room);
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return array;
//    }
//
//    public ArrayList<Room> getRoomByRoomType(String type){
//        ArrayList<Room> array= new ArrayList<>();
//        String query = "SELECT * FROM room_table where room_type=?";
//        try{
//
//            PreparedStatement ppStatement = connection.prepareStatement(query);
//            ppStatement.setString(1, type);
//            ResultSet rs = ppStatement.executeQuery();
//            while (rs.next()) {
//                Room room=new Room(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),
//                        rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),
//                        rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));
//                array.add(room);
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return array;
//    }

    public void updateRoomRecord(String id) throws SQLException {
        String query = "UPDATE room_table SET _avability=?,rented_by=?,number=?,id_card=?,in_date=?,out_date=?,food_charges=? where room_number=?";
        PreparedStatement ppStatement = connection.prepareStatement(query);
        ppStatement.setString(1,"YES");
        ppStatement.setString(2,"");
        ppStatement.setString(3,"");
        ppStatement.setString(4,"");
        ppStatement.setString(5,"");
        ppStatement.setString(6,"");
        ppStatement.setString(7,"");
        ppStatement.setString(8, id);
        ppStatement.execute();
    }


    public void addRoomRecordById(String id,String rented,String number,String cardID,String inDate) throws SQLException {
        String query = "UPDATE room_table SET _avability=?,rented_by=?,number=?,id_card=?,in_date=?,out_date=? where room_number=?";
        PreparedStatement ppStatement = connection.prepareStatement(query);
        ppStatement.setString(1,"NO");
        ppStatement.setString(2,rented);
        ppStatement.setString(3,number);
        ppStatement.setString(4,cardID);
        ppStatement.setString(5,inDate);
        ppStatement.setString(6,"NOT LIVE");
        ppStatement.setString(7,id);
        ppStatement.execute();
    }

    public void addOutDate(String id,String outDate) throws SQLException {
        String query = "UPDATE room_table SET out_date=? where room_number=?";
        PreparedStatement ppStatement = connection.prepareStatement(query);
        ppStatement.setString(1,outDate);
        ppStatement.setString(2, id);
        ppStatement.execute();
    }


    public void addFoodCharges(String id,String Charges) throws SQLException {
        String query2;
        String temp=Charges;
        Room roomDetails=getRoomRecord(id);
        if(!(roomDetails.getFoodCharges().isEmpty())) {
            int temp1=Integer.parseInt(temp)+Integer.parseInt(roomDetails.getFoodCharges());
            temp="";
            temp=temp1+"";
        }
        query2 = "UPDATE room_table SET food_charges=? where room_number=?";
        PreparedStatement ppStatement2 = connection.prepareStatement(query2);
        ppStatement2.setString(1,temp);
        ppStatement2.setString(2, id);
        ppStatement2.execute();
    }

    public  void createFoodTable() throws SQLException {
            String query ="CREATE TABLE IF NOT EXISTS food_table (name varchar(55) primary key unique ,price varchar(55),type varchar(55))";
            Statement statement = connection.createStatement();
            statement.execute(query);
    }
    public Food getfood(String Name) throws SQLException {
        Food food=new Food();
        String query ="SELECT * FROM food_table where name=?";
        try{

            PreparedStatement ppStatement = connection.prepareStatement(query);
            ppStatement.setString(1, Name);
            ResultSet rs = ppStatement.executeQuery();
            rs.absolute(1);
            food.setItemName(rs.getString(1));
            food.setPrice(rs.getString(2));
            food.setType(rs.getString(3));
        }catch (SQLException e){
            e.printStackTrace();
        }
        return food;
    }
    public ArrayList<Food> getFoodByType(String Type) throws SQLException {
        ArrayList<Food> array = new ArrayList<>();
        String query ="SELECT * FROM food_table where type=?";
        try{

            PreparedStatement ppStatement = connection.prepareStatement(query);
            ppStatement.setString(1, Type);
            ResultSet rs = ppStatement.executeQuery();
            while (rs.next()) {
                Food food=new Food(rs.getString(1), rs.getString(2),rs.getString(3));assert array != null;
                array.add(food);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return array;
    }

    public void addFood(String Name,String Price,String Type) throws SQLException {
        String query = "INSERT INTO food_table(name,price,type)VALUE (?,?,?)";
        PreparedStatement ppStatement = connection.prepareStatement(query);
        ppStatement.setString(1,Name);
        ppStatement.setString(2,Price);
        ppStatement.setString(3,Type);
        ppStatement.execute();
    }

}
