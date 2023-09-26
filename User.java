import java.io.Serializable;

class User implements Serializable {
    private String username;
    private String email;

    public User(){
        username = "NULL";
        email = "NULL";
    }
    public User(String username, String number) {
        this.username = username;
        this.email = number;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}