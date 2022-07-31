public class Food {
    private String itemName;
    private String price;
    private String type;

    public Food() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return itemName + '\t' + price;
    }

    public Food(String itemName, String price, String type) {
        this.itemName = itemName;
        this.price = price;
        this.type = type;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
}
