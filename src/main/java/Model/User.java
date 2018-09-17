package Model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String firstName;
    public String lastName;
    public String aimsID;
    public String email;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }


    public User(String firstName, String lastName, String aimsID, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.aimsID = aimsID;
        this.email = email;
    }


}