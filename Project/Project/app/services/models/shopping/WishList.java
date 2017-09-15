package models.shopping;

import java.util.Iterator;
import java.util.List;

import javax.persistence.*;

import com.avaje.ebean.*;

import models.Product;
import models.users.*;


// Product entity managed by Ebean
@Entity
public class WishList extends Model {

    @Id
    private Long id;
    
    @OneToMany(mappedBy = "wishList", cascade = CascadeType.PERSIST)
    private List<OrderItem> wishListItems;
    
    @OneToOne
    private Customer customer;

    // Default constructor
    public  WishList() {
    }
    
    // Add product to basket
    // Either update existing order item or ad a new one.
    public void addProduct(Product p) {
        
        boolean itemFound = false;
        // Check if product already in this basket
        // Check if item in basket
        // Find orderitem with this product
        // if found increment quantity
        for (OrderItem i : wishListItems) {
            if (i.getProduct(). getProduct_id() == p. getProduct_id()) {
                i.increaseQty();
                itemFound = true;
                break;
            }
        }
        if (itemFound == false) {
            // Add orderItem to list
            OrderItem newItem = new OrderItem(p);
            // Add to items
            wishListItems.add(newItem);
        }
    }
    
    public void removeItem(OrderItem item) {

        // Using an iterator ensures 'safe' removal of list objects
        // Removal of list items is unreliable as index can change if an item is added or removed elsewhere
        // iterator works with an object reference which does not change
        for (Iterator<OrderItem> iter = wishListItems.iterator(); iter.hasNext();) {
            OrderItem i = iter.next();
            if (i.getId().equals(item.getId()))
            {
                // If more than one of these items in the basket then decrement
                if (i.getQuantity() > 1 ) {
                    i.decreaseQty();
                }
                // If only one left, remove this item from the basket (via the iterator)
                else {
                    // delete object from db
                    i.delete();
                    // remove object from list
                    iter.remove();
                    break;
                }             
            }
		}
    }
    
    public void removeAllItems() {
        for(OrderItem i: this.wishListItems) {
            i.delete();
        }
        this.wishListItems = null;
    }


	
	//Generic query helper
    public static Finder<Long,WishList> find = new Finder<Long,WishList>(WishList.class);

    //Find all Products in the database
    public static List<WishList> findAll() {
        return WishList.find.all();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItem> getWishListItems() {
        return wishListItems;
    }

    public void setWishListItems(List<OrderItem> wishListItems) {
        this.wishListItems = wishListItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

