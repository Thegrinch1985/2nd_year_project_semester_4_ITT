package models;

import java.util.*;
import javax.persistence.*;
import play.data.format.*;
import play.data.validation.*;
import com.avaje.ebean.*;


// OrderItem entity managed by Ebean
@Entity
public class CreditCard extends Model {

        @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "cc_id", updatable = false, nullable = false)
    private Long cc_id;
    private String name;
    private String cardNumber;
  
    private String month;
    private String year;
    private String card_cvv;
    

    public CreditCard(String name, String cardNumber, String month, String year, String card_cvv){
        this.name = name;
        this.cardNumber = cardNumber;
       
        this.month = month;
        this.year = year;
        this.card_cvv = card_cvv;
    }


    public static Finder<Long,CreditCard> find = new Finder<Long,CreditCard>(CreditCard.class);

    public static List<CreditCard> findAllCreditCard() {
        return CreditCard.find.all();
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getCardNumber(){
        return cardNumber;
    }

    public void setCardNumber(String cardNumber){
        this.cardNumber = cardNumber;
    }

  

    public String getMonth(){
        return month;
    }

    public void setMonth(String month){
        this.month = month;
    }
       
    public String getYear(){
        return year;
    }

    public void setYear(String year){
        this.year = year;
    }  

    public String getCard_cvv(){
        return card_cvv;
    }

    public void setCard_cvv(String card_cvv){
        this.card_cvv = card_cvv;
    }    


}