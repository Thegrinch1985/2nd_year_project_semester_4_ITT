package models;

import java.util.*;
import javax.persistence.*;
import play.data.format.*;
import play.data.validation.*;
import com.avaje.ebean.*;


/**
 * Created by wdd on 13/03/17.
 */

@Entity


public class Feedback extends Model {

    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "feedback_id", updatable = false, nullable = false)
    private Long feedback_id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String message;


    public Feedback(String firstName, String lastName, String email, String phoneNumber, String message){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.message = message;
    }


    public static Finder<Long,Feedback> find = new Finder<Long,Feedback>(Feedback.class);

    public static List<Feedback> findAllFeedback() {
        return Feedback.find.all();
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

}