package models.users;

import java.util.*;
import javax.persistence.*;
import play.data.format.*;
import play.data.validation.*;
import com.avaje.ebean.*;

import models.shopping.*;

@Entity

// This is a Customer of type admin
@DiscriminatorValue("customer")

// Customer inherits from the User class
public class Customer extends User{
	
	private String confirmPassword;
	private String phone;
    private String address;
    private String dateOfBirth;
   
    
    // Customer has one basket.
    // Customer is the owner (foreign key will be added to Basket table)
    // All changes to Customer are cascaded.
    @OneToOne(mappedBy="customer", cascade = CascadeType.ALL)
    private Basket basket;

    @OneToOne(mappedBy="customer", cascade = CascadeType.ALL)
    private WishList wishList;
    // Customer can habe many ShopOrders.
    // Customer is the owner (forieng key will be added to Basket table)
    // All changes to Customer are cascaded
    @OneToMany(mappedBy="customer", cascade = CascadeType.ALL)
    private List<ShopOrder> orders;
	
	public Customer(String email, String role, String name, String password, String confirmPassword, 
    String phone,String address, String dateOfBirth)
	{
		super(email, role, name, password);
        this.confirmPassword = confirmPassword;
        this.phone = phone;
        this.address = address;
      
		this.dateOfBirth = dateOfBirth;
	}
    	//Generic query helper for entity User with unique id String
        	//Generic query helper for entity User with unique id String
    public static Finder<String,Customer> find = new Finder<String,Customer>(Customer.class);



    public static List<Customer> findAllCustomer() {
        return Customer.find.all();
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword= confirmPassword;
    }



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public List<ShopOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<ShopOrder> orders) {
        this.orders = orders;
    }
    public WishList getWishList() {
        return wishList;
    }

    public void setWishList(WishList wishList) {
        this.wishList = wishList;
    }
}