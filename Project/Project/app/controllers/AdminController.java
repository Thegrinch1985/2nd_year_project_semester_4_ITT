package controllers;

import controllers.security.AuthAdmin;
import controllers.security.Secured;
import play.data.*;
import play.db.ebean.Transactional;
import play.mvc.*;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import play.api.Environment;

import play.mvc.Http.*;
import play.mvc.Http.MultipartFormData.FilePart;
import java.io.File;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;

import views.html.*;

import models.*;
import models.users.*;

// Require Login
@Security.Authenticated(Secured.class)
// Authorise user (check if admin)
@With(AuthAdmin.class)
public class AdminController extends Controller {

    // Declare a private FormFactory instance
    private FormFactory formFactory;
    private Environment env;

    //  Inject an instance of FormFactory it into the controller via its constructor
    @Inject
    public AdminController(FormFactory f, Environment env) {
        this.formFactory = f;
        this.env = env;
    }

    // Method returns the logged in user (or null)
    private User getUserFromSession() {
        return User.getUserById(session().get("email"));
    }

    public Result listUsers() {

        List<User> userList = User.findAllUsers();
        return ok(listUsers.render(userList,getUserFromSession()));
    }
    public Result admin_page(){

        
        List<Administrator> administratorList = Administrator.findAllAdmin();
        return ok(admin_page.render(getUserFromSession()));


    }
    
    public Result listCustomers() {

        List<Customer> customerList = Customer.findAllCustomer();
        return ok(listCustomers.render(customerList,getUserFromSession()));
    }

    public Result displayRequests(){
        List<RequestProduct> requestProductList = RequestProduct.findAllRequests();
        return ok(requestList.render(requestProductList,getUserFromSession()));
    } 

    public Result displayFeedback(){
        List<Feedback> feedbackL = Feedback.findAllFeedback();
        return ok(feedbackList.render(feedbackL,getUserFromSession()));
    } 						


    public Result products(Long cat) {

        // Get list of all categories in ascending order
        List<Category> categoriesList = Category.findAll();
        List<Product> productsList = new ArrayList<Product>();

        if (cat == 0) {
            // Get list of all categories in ascending order
            productsList = Product.findAll1();
        }
        else {
            // Get products for selected category
            // Find category first then extract products for that cat.
            productsList = Category.find.ref(cat).getProducts();
        }

        return ok(listProducts.render(productsList, getUserFromSession(), env));
    }

    // Render and return  the add new product page
    // The page will load and display an empty add product form

    public Result addProduct() {

        // Create a form by wrapping the Product class
        // in a FormFactory form instance
        Form<Product> addProductForm = formFactory.form(Product.class);

        // Render the Add Product View, passing the form object
        return ok(addProduct.render(addProductForm, getUserFromSession(),env));
    }

    @Transactional
    public Result addProductSubmit() {

        String saveImageMsg;

        // Create a product form object (to hold submitted data)
        // 'Bind' the object to the submitted form (this copies the filled form)
        Form<Product> newProductForm = formFactory.form(Product.class).bindFromRequest();

        // Check for errors (based on Product class annotations)
        if(newProductForm.hasErrors()) {
            // Display the form again
            return badRequest(addProduct.render(newProductForm, getUserFromSession(),env));
        }

        // Extract the product from the form object
        Product p = newProductForm.get();

        if (p.getProduct_id() == null) {
            // Save to the database via Ebean (remember Product extends Model)
            
            p.save();
                 flash("success", "Product Created");
        }
        // Product already exists so update
        else if (p.getProduct_id() != null) {
            p.update();
             flash("success", "Product Updated");
        }
        //p.save();
        MultipartFormData data = request().body().asMultipartFormData();
        FilePart image = data.getFile("upload");

        saveImageMsg = saveFile(p.getProduct_id(), image);

        // Set a success message in temporary flash
        // for display in return view
        flash("success", "Product " + p.getProduct_name() + " has been created/ updated" + saveImageMsg);

        // Redirect to the admin home
        return redirect(routes.AdminController.listProducts(0));
    }

    // Update a product by ID
    // called when edit button is pressed
    @Transactional
    public Result updateProduct(Long id) {

        Product p;
        Form<Product> productForm;

        try {
            // Find the product by id
            p = Product.find.byId(id);

            // Create a form based on the Product class and fill using p
            productForm = formFactory.form(Product.class).fill(p);

            } catch (Exception ex) {
                // Display an error message or page
                return badRequest("error");
        }
        // Render the updateProduct view - pass form as parameter
        return ok(addProduct.render(productForm, getUserFromSession(),env));
    }

    // Delete Product by id
    @Transactional
    public Result deleteProduct(Long id) {

        // find product by id and call delete method
        Product.find.ref(id).delete();
        // Add message to flash session
        flash("success", "Product has been deleted");

        // Redirect to products page
        return redirect(routes.AdminController.listProducts(0));
    }

 
     public Result listProducts(Long cat) {

        // Get list of all categories in ascending order
        List<Category> categoriesList = Category.findAll();
        List<Product> productsList = new ArrayList<Product>();

        if (cat == 0) {
            // Get list of all categories in ascending order
            productsList = Product.findAll1();
        }
        else {
            // Get products for selected category
            // Find category first then extract products for that cat.
            productsList = Category.find.ref(cat).getProducts();
        }

        return ok(listProducts.render(productsList, getUserFromSession(),env));
    }
    
    public Result sale(Long cat) {

        // Get list of all categories in ascending order
        List<Category> categoriesList = Category.findAll();
        List<Product> productsList = new ArrayList<Product>();

        if (cat == 0) {
            // Get list of all categories in ascending order
            productsList = Product.findAll1();
        }
        else {
            // Get products for selected category
            // Find category first then extract products for that cat.
            productsList = Category.find.ref(cat).getProducts();
        }

        return ok(sale.render(productsList, getUserFromSession()));
    }
           public Result all_Sale(Long cat) {

        // Get list of all categories in ascending order
        List<Category> categoriesList = Category.findAll();
        List<Product> productsList = new ArrayList<Product>();

        if (cat == 0) {
            // Get list of all categories in ascending order
            productsList = Product.findAll1();
        }
        else {
            // Get products for selected category
            // Find category first then extract products for that cat.
            productsList = Category.find.ref(cat).getProducts();
        }

        return ok(all_Sale.render(productsList, getUserFromSession()));
    }
              public Result revert_from_sale(Long cat) {

        // Get list of all categories in ascending order
        List<Category> categoriesList = Category.findAll();
        List<Product> productsList = new ArrayList<Product>();

        if (cat == 0) {
            // Get list of all categories in ascending order
            productsList = Product.findAll1();
        }
        else {
            // Get products for selected category
            // Find category first then extract products for that cat.
            productsList = Category.find.ref(cat).getProducts();
        }

        return ok(revert_from_sale.render(productsList, getUserFromSession()));
    }
      public Result order(Long cat) {

        // Get list of all categories in ascending order
        List<Category> categoriesList = Category.findAll();
        List<Product> productsList = new ArrayList<Product>();

        if (cat == 0) {
            // Get list of all categories in ascending order
            productsList = Product.findAll1();
        }
        else {
            // Get products for selected category
            // Find category first then extract products for that cat.
            productsList = Category.find.ref(cat).getProducts();
        }

        return ok(order.render(productsList, getUserFromSession()));
    }
        public String saveFile(Long id, FilePart<File> image) {
        if (image != null) {

            String mimeType = image.getContentType();

            if (mimeType.startsWith("/image")) {
                File file = image.getFile();

                ConvertCmd cmd = new ConvertCmd();
                IMOperation op = new IMOperation();

                op.addImage(file.getAbsolutePath());
                op.resize(300, 200);
                op.addImage("public/images/productImages/thumbnail/" + id + ".jpg");

                IMOperation thumb = new IMOperation();

                thumb.addImage(file.getAbsolutePath());
                thumb.thumbnail(60);
                thumb.addImage("public/images/productImages/thumbnail/" + id + ".jpg");

                try{
                    cmd.run(op);
                    cmd.run(thumb);
                } 
                catch(Exception e){
                    e.printStackTrace();
                }
                return " and image saved";
            }
        }
        return "image file missing";
        }
}
