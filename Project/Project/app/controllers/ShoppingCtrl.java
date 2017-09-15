package controllers;

import play.mvc.*;
import java.util.*;

import views.html.*;
import play.db.ebean.Transactional;

// Import models

import models.Product;

import models.*;
import models.users.*;
import models.shopping.*;


// Import security controllers
import controllers.security.*;
import play.api.Environment;
import play.data.*;

import javax.inject.Inject;

import views.html.*;

// Authenticate user
@Security.Authenticated(Secured.class)
// Authorise user (check if user is a customer)
@With(CheckIfCustomer.class)

public class ShoppingCtrl extends Controller {

      /** Dependency Injection **/

    /** http://stackoverflow.com/questions/15600186/play-framework-dependency-injection **/
    private FormFactory formFactory;

    /** http://stackoverflow.com/a/37024198 **/
    private play.api.Environment env;

    /** http://stackoverflow.com/a/10159220/6322856 **/
    @Inject
    public ShoppingCtrl(Environment e, FormFactory f) {
        this.env = e;
        this.formFactory = f;
    }

    // Get a user - if logged in email will be set in the session
	private Customer getCurrentUser() {
		return (Customer)User.getLoggedIn(session().get("email"));
	}
    public Result payment() {
        return ok(payment.render(getCurrentUser()));
    }
         public Result creditCard() {

        Form<CreditCard> creditCardForm = formFactory.form(CreditCard.class);
        return ok(creditCard.render(getCurrentUser(),creditCardForm));
    }

    public Result creditCardSubmit() {

        Form<CreditCard> creditCardForm = formFactory.form(CreditCard.class).bindFromRequest();

        if (creditCardForm.hasErrors()) {
            return badRequest(creditCard.render(getCurrentUser(),creditCardForm));
        }

            CreditCard newCC = creditCardForm.get();

            // Save to the database via Ebean (remember Product extends Model)
            newCC.save();

            flash("success");

            return redirect(controllers.routes.ShoppingCtrl.creditCard());
    }



  public Result orderHistory(){
        List<ShopOrder> orderList = ShopOrder.find.all();
        return ok(order_history.render(orderList, getCurrentUser()));
    }

    @Transactional
    public Result showBasket() {

        return ok(basket.render(getCurrentUser()));
    }
      @Transactional
    public Result showWishList() {
        return ok(wishList.render(getCurrentUser()));
    }
    // Add item to customer basket
    @Transactional
    public Result addToBasket(Long id) {

        // Find the product
        Product p = Product.find.byId(id);

        // Get basket for logged in customer
        Customer customer = (Customer)User.getLoggedIn(session().get("email"));

        // Check if item in basket
        if (customer.getBasket() == null) {
            // If no basket, create one
            customer.setBasket(new Basket());
            customer.getBasket().setCustomer(customer);
            customer.update();
        }
        // Add product to the basket and save
        customer.getBasket().addProduct(p);
        customer.update();

        // Show the basket contents
        return ok(basket.render(customer));
    }


        // Add item to customer basket
    @Transactional
    public Result addToWishList(Long id) {

        // Find the product
        Product p = Product.find.byId(id);

        // Get basket for logged in customer
        Customer customer = (Customer)User.getLoggedIn(session().get("email"));

        // Check if item in basket
        if (customer.getWishList() == null) {
            // If no basket, create one
            customer.setWishList(new WishList());
            customer.getWishList().setCustomer(customer);
            customer.update();
        }
        // Add product to the basket and save
        customer.getWishList().addProduct(p);
        customer.update();

        // Show the basket contents
        return ok(wishList.render(customer));
    }

         public Result user() {


        return ok(user.render(getCurrentUser()));
    }

    // Add an item to the basket
    @Transactional
    public Result addOne(Long itemId) {

        // Get the order item
        OrderItem item = OrderItem.find.byId(itemId);
        // Increment quantity
        item.increaseQty();
        // Save
        item.update();
        // Show updated basket
        return redirect(routes.ShoppingCtrl.showBasket());
    }
        // Add an item to the basket
    @Transactional
    public Result addOneWishList(Long itemId) {

        // Get the order item
        OrderItem item = OrderItem.find.byId(itemId);
        // Increment quantity
        item.increaseQty();
        // Save
        item.update();
        // Show updated basket
        return redirect(routes.ShoppingCtrl.showWishList());
    }

    @Transactional
    public Result removeOne(Long itemId) {

        // Get the order item
        OrderItem item = OrderItem.find.byId(itemId);
        // Get user
        Customer c = getCurrentUser();
        // Call basket remove item method
        c.getBasket().removeItem(item);
        c.getBasket().update();
        // back to basket
        return ok(basket.render(c));
    }

    @Transactional
    public Result removeOneWishList(Long itemId) {

        // Get the order item
        OrderItem item = OrderItem.find.byId(itemId);
        // Get user
        Customer c = getCurrentUser();
        // Call basket remove item method
        c.getWishList().removeItem(item);
        c.getWishList().update();
        // back to basket
        return ok(wishList.render(c));
    }

    // Empty Basket
    @Transactional
    public Result emptyBasket() {

        Customer c = getCurrentUser();
        c.getBasket().removeAllItems();
        c.getBasket().update();
        
        return ok(basket.render(c));
    }    // Empty Basket
    @Transactional
    public Result emptyWishList() {

        Customer c = getCurrentUser();
        c.getWishList().removeAllItems();
        c.getWishList().update();

        return ok(wishList.render(c));
    }


    @Transactional
    public Result placeOrder() {
        Customer c = getCurrentUser();

        // Create an order instance
        ShopOrder order = new ShopOrder();

        // Associate order with customer
        order.setCustomer(c);

        // Copy basket to order
        order.setItems(c.getBasket().getBasketItems());

        // Save the order now to generate a new id for this order
        order.save();

       // Move items from basket to order
        for (OrderItem i: order.getItems()) {
            // Associate with order
            i.setOrder(order);
            // Remove from basket
            i.setBasket(null);
            // update item
            i.update();
        }

        // Update the order
        order.update();

        // Clear and update the shopping basket
        c.getBasket().setBasketItems(null);
        c.getBasket().update();

        // Show order confirmed view
        return ok(orderConfirmed.render(c, order));
    }

    // View an individual order
    @Transactional
    public Result viewOrder(long id) {
        ShopOrder order = ShopOrder.find.byId(id);
        return ok(orderConfirmed.render(getCurrentUser(), order));
    }

}
