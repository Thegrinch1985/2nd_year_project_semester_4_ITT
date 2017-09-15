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


public class Review extends Model {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "product_id", updatable = false, nullable = false)
    private Long review_id;
    private String review;
  

    public Review( String review){
        this.review = review;
  
    }


    public static Finder<Long,Review> find = new Finder<Long,Review>(Review.class);

    public static List<Review> findAllReviews() {
        return Review.find.all();
    }

    public String getReview(){
        return review;
    }

    public void setReview(String review){
        this.review = review;
    }


}