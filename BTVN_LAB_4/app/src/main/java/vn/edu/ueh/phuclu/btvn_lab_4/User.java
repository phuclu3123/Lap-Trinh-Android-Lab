package vn.edu.ueh.phuclu.btvn_lab_4;
import java.io.Serializable;

public class User implements Serializable {
    public String username;
    public String email;
    public String dob;
    public String hobby;
    public String gender;

    public User(String username, String email, String dob,
                String hobby, String gender) {

        this.username = username;
        this.email = email;
        this.dob = dob;
        this.hobby = hobby;
        this.gender = gender;
    }
}
