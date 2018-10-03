package Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User implements Parcelable {

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(aimsID);
        dest.writeString(email);

    }

    private User(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        aimsID = in.readString();
        email = in.readString();
    }


    public static final Parcelable.Creator<User> CREATOR
            = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };


}