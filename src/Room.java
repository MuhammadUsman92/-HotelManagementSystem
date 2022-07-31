public class Room {
    private String roomNumber;
    private String avability;
    private String roomType;
    private String SwimmingPool;
    private String ac;
    private String price;
    private String rented;
    private String phoneNumber;
    private String idCard;
    private String foodCharges;

    public String getFoodCharges() {
        return foodCharges;
    }

    public void setFoodCharges(String foodCharges) {
        this.foodCharges = foodCharges;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    private String inDate;
    private String outDate;

    public String getIdCard() {
        return idCard;
    }

    public Room(String roomNumber, String avability, String roomType, String swimmingPool, String ac, String price, String rented, String phoneNumber, String idCard, String inDate, String outDate, String foodCharges) {
        this.roomNumber = roomNumber;
        this.avability = avability;
        this.roomType = roomType;
        SwimmingPool = swimmingPool;
        this.ac = ac;
        this.price = price;
        this.rented = rented;
        this.phoneNumber = phoneNumber;
        this.idCard = idCard;
        this.inDate = inDate;
        this.outDate = outDate;
        this.foodCharges =foodCharges;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Room() {
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return "Room Number: " + roomNumber + "\t\t" +
                "Room Type: " + roomType + '\n' +
                "Swimming Pool: " + SwimmingPool +"\t\t" +
                "AC= " + ac + '\n' +
                "Price: " + price + "\t\t\t" +
                "Rented by: " + rented + '\n' +
                "Phone Number: " + phoneNumber + "\t\t" +
                "ID Card: " + idCard + '\n' +
                "In Date: " + inDate + "\t\t" +
                "Out Date: " + outDate + '\n'+
                "Food Charges: "+ foodCharges;
    }

    public String getAvability() {
        return avability;
    }

    public void setAvability(String avability) {
        this.avability = avability;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getSwimmingPool() {
        return SwimmingPool;
    }

    public void setSwimmingPool(String swimmingPool) {
        SwimmingPool = swimmingPool;
    }

    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRented() {
        return rented;
    }

    public void setRented(String rented) {
        this.rented = rented;
    }

    public String getphoneNumber() {
        return phoneNumber;
    }

    public void setphoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getinDate() {
        return inDate;
    }

    public void setinDate(String inDate) {
        this.inDate = inDate;
    }
}
