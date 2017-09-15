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


public class RequestProduct extends Model {

    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "request_product_id", updatable = false, nullable = false)
    private Long request_product_id;
    private String category;
    private String productTitle;
    private String productDescription;
    private String name;
    private String mobile;
    private String email;

    public RequestProduct(String category, String productTitle, String productDescription, String name, String mobile, String email){
        this.category = category;
        this.productTitle = productTitle;
        this.productDescription = productDescription;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
    }


    public static Finder<Long,RequestProduct> find = new Finder<Long,RequestProduct>(RequestProduct.class);

    public static List<RequestProduct> findAllRequests() {
        return RequestProduct.find.all();
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public String getProductTitle(){
        return productTitle;
    }

    public void setProductTitle(String productTitle){
        this.productTitle = productTitle;
    }

    public String getProductDescription(){
        return productDescription;
    }

    public void setProductDescription(String productDescription){
        this.productDescription = productDescription;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
       
    public String getMobile(){
        return mobile;
    }

    public void setMobile(String mobile){
        this.mobile = mobile;
    }  

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }    


}