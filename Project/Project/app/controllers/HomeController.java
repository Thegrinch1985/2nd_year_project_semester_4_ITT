package controllers;
import play.api.Environment;
import play.mvc.*;
import play.data.*;
import play.db.ebean.Transactional;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import views.html.*;

// Import models
import models.*;
import models.users.*;


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

      // Declare a private FormFactory instance
    private FormFactory formFactory;
    private Environment env;

    //  Inject an instance of FormFactory it into the controller via its constructor
    @Inject
    public HomeController(FormFactory f, Environment env) {
        this.formFactory = f;
        this.env = env;
    }
    public Result index() {
        Product p = new Product();
        return ok(index.render(getUserFromSession(),p));
    }

    // public Result login() {
    //     return ok(login.render(getUserFromSession()));
    // }



     public Result user_page(){
         return ok(user_page.render(getUserFromSession()));
     }

     public Result listProducts() {

        List<Product> productsList = Product.findAll1();
        return ok(listProducts.render(productsList,getUserFromSession(), env));
    }
        public Result listUsers() {

        List<User> userList = User.findAllUsers();
        return ok(listUsers.render(userList,getUserFromSession()));
    }


        public Result addCustomer() {
        Form<Customer> addCustomerForm = formFactory.form(Customer.class);

        return ok(addCustomer.render(addCustomerForm,getUserFromSession()));
    }
        public Result addCustomerSubmit() {

        Form<Customer> newCustomerForm = formFactory.form(Customer.class).bindFromRequest();

        if (newCustomerForm.hasErrors()) {
            return badRequest(addCustomer.render(newCustomerForm,getUserFromSession()));
        }

            Customer newCustomer = newCustomerForm.get();

                 if (newCustomer.getEmail() == null) {
                 // Save to the database via Ebean (remember Product extends Model)
                 newCustomer.save();
                 } else {
                   newCustomer.update();
                 }

            flash("success", "Customer" + newCustomer.getName() + "has been created");

            return redirect(controllers.routes.HomeController.index());
             //return ok(addCustomer.render(addCustomerForm,getUserFromSession()));
    }


      public Result categories() {
        return ok(categories.render(getUserFromSession()));
    }

    public Result book_1() {
        Product p = new Product();

        return ok(book_1.render(getUserFromSession(),p));
    }



    public Result book_2() {
     Product p = new Product();

        return ok(book_2.render(getUserFromSession(),p));
    }
       public Result book_3() {
           Product p = new Product();

        return ok(book_3.render(getUserFromSession(),p));
    }

    public Result display_all_Games() {
        Product p = new Product();
        return ok(display_all_Games.render(getUserFromSession(),p));
    }
    public Result display_all_Instruments() {
         Product p = new Product();
        return ok(display_all_Instruments.render(getUserFromSession(),p));
    }
    public Result display_all_Mobiles() {
     Product p = new Product();
        return ok(display_all_Mobiles.render(getUserFromSession(),p));
    }
      public Result display_all_Electronics() {
         Product p = new Product();
        return ok(display_all_Electronics.render(getUserFromSession(),p));
    }
    public Result display_all_Books() {

                  Product p = new Product();


        return ok(display_all_Books.render(getUserFromSession(),p));
    }

    public Result game_1() {
          Product p = new Product();
        return ok(game_1.render(getUserFromSession(),p));
    }

    public Result game_2() {
          Product p = new Product();
        return ok(game_2.render(getUserFromSession(),p));
    }

    public Result game_3() {
         Product p = new Product();
        return ok(game_3.render(getUserFromSession(),p));
    }
     public Result mobiles_1() {
               Product p = new Product();
        return ok(mobiles_1.render(getUserFromSession(),p));
    }

 public Result mobiles_2() {
     Product p = new Product();
        return ok(mobiles_2.render(getUserFromSession(),p));
    }
     public Result mobiles_3() {
       Product p = new Product();
        return ok(mobiles_3.render(getUserFromSession(),p));
    }
    public Result instrument_1() {
      Product p = new Product();
        return ok(instrument_1.render(getUserFromSession(),p));
    }

    public Result instrument_2() {
       Product p = new Product();
        return ok(instrument_2.render(getUserFromSession(),p));
    }

    public Result instrument_3() {
        Product p = new Product();
        return ok(instrument_3.render(getUserFromSession(),p));
    }



    public Result electronics_1() {
         Product p = new Product();
        return ok(electronics_1.render(getUserFromSession(),p));
    }

    public Result electronics_2() {
      Product p = new Product();
        return ok(electronics_2.render(getUserFromSession(),p));
    }

    public Result electronics_3() {
          Product p = new Product();
        return ok(electronics_3.render(getUserFromSession(),p));
    }




    @Transactional
    public Result updateProduct(Long id){
        Product p;
        Form<Product> productForm;

        try {
            p = Product.find.byId(id);

            productForm = formFactory.form(Product.class).fill(p);

        }catch (Exception ex){
            return badRequest("error");

        }
        return ok(addProduct.render(productForm,getUserFromSession(),env));

    }
    // Delete Product by id


   public Result deleteProduct(Long id) {

        // find product by id and call delete method
        Product.find.ref(id).delete();
        // Add message to flash session
        flash("success", "Product has been deleted");

        // Redirect to products page
        return redirect(routes.HomeController.listProducts());
    }

       private User getUserFromSession() {
        return User.getUserById(session().get("email"));
    }
     public Result updateDetails(String id){
         Customer c;
      Form<Customer> addCustomerForm = formFactory.form(Customer.class);


      try {
          c = Customer.find.byId(id);

          addCustomerForm = formFactory.form(Customer.class).fill(c);

      }catch (Exception ex){
          return badRequest("error");
      }
        return ok(addCustomer.render(addCustomerForm,getUserFromSession()));
     }

    // @Transactional
    // public Result updateUser(Long id){
    //     Customer c;
    //     Form<Customer> addCustomerForm;

    //     try {

    //         c = Customer.find.byId(id);

    //         addCustomerForm = formFactory.form(Customer.class).fill(c);

    //     }catch (Exception ex){
    //         return badRequest("error");

    //     }
    //     return ok(addCustomer.render(addCustomerForm,getUserFromSession()));

    // }

    public Result requestProduct() {

        Form<RequestProduct> requestProductForm = formFactory.form(RequestProduct.class);

        return ok(request_product.render(requestProductForm, User.getUserById(session().get("email"))));
    }

    public Result requestProductSubmit() {

        Form<RequestProduct> requestProductForm = formFactory.form(RequestProduct.class).bindFromRequest();

        if (requestProductForm.hasErrors()) {
            return badRequest(request_product.render(requestProductForm,getUserFromSession()));
        }

            RequestProduct newRequest = requestProductForm.get();

            // Save to the database via Ebean (remember Product extends Model)
            newRequest.save();

            flash("success");

            return redirect(controllers.routes.HomeController.index());
    }

    public Result feedback() {

        Form<Feedback> feedbackForm = formFactory.form(Feedback.class);

        return ok(feedback.render(feedbackForm, User.getUserById(session().get("email"))));
    }

    public Result feedbackSubmit() {

        Form<Feedback> feedbackForm = formFactory.form(Feedback.class).bindFromRequest();

        if (feedbackForm.hasErrors()) {
            return badRequest(feedback.render(feedbackForm,getUserFromSession()));
        }

            Feedback newFeedback = feedbackForm.get();

            // Save to the database via Ebean (remember Product extends Model)
            newFeedback.save();

            flash("success");

            return redirect(controllers.routes.HomeController.index());
    }


    public Result review() {

        Form<Review> reviewForm = formFactory.form(Review.class);

        return ok(review.render(reviewForm, User.getUserById(session().get("email"))));
    }

    public Result reviewSubmit() {

        Form<Review> reviewForm = formFactory.form(Review.class).bindFromRequest();

        if (reviewForm.hasErrors()) {
            return badRequest(review.render(reviewForm,getUserFromSession()));
        }

            Review newReview = reviewForm.get();

            // Save to the database via Ebean (remember Product extends Model)
            newReview.save();

            flash("success");

            return redirect(controllers.routes.HomeController.index());
    }


}
