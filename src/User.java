public class User {
    private String user_name;
    private String password;
    private String type;

    public User() {
    }

    public User(User user) {
        this.user_name = user.user_name;
        this.password = user.password;
        this.type = user.type;
    }

    @Override
    public String toString() {
        return
                "User Name:\t" + user_name + '\n' +
                "Password:\t" + password + '\n' +
                "User Type:\t" + type + '\n';
    }

    public String getPassword() {
        return password;
    }

    public User(String user_name, String password, String type) {
        this.user_name = user_name;
        this.password = password;
        this.type = type;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
