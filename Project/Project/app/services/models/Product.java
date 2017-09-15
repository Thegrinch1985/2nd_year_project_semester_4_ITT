package models;

import java.util.*;
import javax.persistence.*;

import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
public class Product extends Model{

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "products")
    private List<Category> categories = new ArrayList<Category>();

    //@OneToOne(mappedBy="product")
    //public OrderItem item = new OrderItem();

    // List of category ids - this will be bound to checkboxes in the view form
    private List<Long> catSelect = new ArrayList<Long>();
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "product_id", updatable = false, nullable = false)
//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
private Long productID;
//public Long productID;
@Constraints.Required
public String product_Name;
//@Constraints.Required
public String category;
@Constraints.Required
public double product_price;
@Constraints.Required
public String manufacturer;
@Constraints.Required
public int quantity;


    // Default constructor
    public Product() {
    }
    //constructor
public Product(String product_Name,String category,double product_price,String manufacturer,
int quantity){

    this.product_Name = product_Name;
    this.category = category;
    this.product_price = product_price;
    this.manufacturer = manufacturer;
    this.quantity = quantity;
}



	//Generic query helper for entity Computer with id Long
    public static Finder<Long,Product> find = new Finder<Long,Product>(Product.class);

    // Find all Products in the database
    // Filter product name
       public static List<Product> findAll1() {
        return Product.find.all();
    }
    public static List<Product> findAll(String filter) {
        return Product.find.where()
                        // name like filter value (surrounded by wildcards)
                        .ilike("product_Name", "%" + filter + "%")
                        .orderBy("product_Name asc")
                        .findList();
    }

    // Find all Products for a category
    // Filter product name
    public static List<Product> findFilter(Long catID, String filter) {
        return Product.find.where()
                        // Only include products from the matching cat ID
                        // In this case search the ManyToMany relation
                        .eq("categories.id", catID)
                        // name like filter value (surrounded by wildcards)
                        .ilike("product_Name", "%" + filter + "%")
                        .orderBy("product_Name asc")
                        .findList();
    }


public void setProduct_id(Long productID){
     this.productID = productID;
}
public void setProduct_name(String product_Name){
      this.product_Name = product_Name;
}
public void setCategory(String category){
 this.category = category;
}
public void setPrice(double product_price){
   this.product_price = product_price;
}
public void setManufacturer(String manufacturer){
  this.manufacturer = manufacturer;
}
public void setQuantity(int quantity){
    this.quantity = quantity;
}

    public String help(Long id) {
       
        // Find the product
        Product p = Product.find.byId(id);
   
 
        // Show the basket contents     
        return p.getProduct_name();
    }

        public double help_price(Long id) {
       
        // Find the product
        Product p = Product.find.byId(id);
   
 
        // Show the basket contents     
        return p.getPrice();
    }


public Long getProduct_id(){
    return productID;
}
public String getProduct_name(){
    return product_Name;
}
public String getCategory(){
    return category;
}
public double getPrice(){
    return product_price;
}
public String getManufacturer(){
    return manufacturer;
}
public int getQuantity(){
    return quantity;
}
public void sale(Long id, int q){

    
     Product p = Product.find.byId(id);
   
    
    if (q < 10)
    {
        double sale1 =  (product_price/100)*75;
          p.setPrice(sale1);
        setPrice(sale1);
        p.update();
     


    }



}
public void all_Sale(Long id){

Product p = Product.find.byId(id);
   
 double sale = (product_price/100)*50;
 p.setPrice(sale);
 setPrice(sale);
 p.update();
}
public void revert_from_sale(Long id){

    
Product p = Product.find.byId(id);
   
 double revert_sale = (product_price*2);
 p.setPrice(revert_sale);
 setPrice(revert_sale);
 p.update();
}
public void order(Long id, int q){
      Product p = Product.find.byId(id);
    
    if(q < 10)
    {
        int ordermore = (quantity + 5);
        p.setQuantity(ordermore);
        setQuantity(ordermore);
        p.update();

    }

}


}
