package controllers.security;

import play.api.Environment;
import play.mvc.*;
import play.data.*;

import javax.inject.Inject;

import views.html.*;

// Import user models
import models.users.*;

public class LoginController extends Controller {

    /** Dependency Injection **/
    /** http://stackoverflow.com/questions/15600186/play-framework-dependency-injection **/
    private FormFactory formFactory;

    /** http://stackoverflow.com/a/37024198 **/
    private Environment env;

    /** http://stackoverflow.com/a/10159220/6322856 **/
    @Inject
    public LoginController(Environment e, FormFactory f) {
        this.env = e;
        this.formFactory = f;
    }

    // Render and return  the Login view
    public Result login() {

        // Create a form by wrapping the Product class
        // in a FormFactory form instance
        Form<Login> loginForm = formFactory.form(Login.class);

        // Render the Add Product View, passing the form object
        return ok(login.render(loginForm, User.getUserById(session().get("email"))));
    }

    // Handle login submit
    public Result loginSubmit() {
        // Bind form instance to the values submitted from the form
        Form<Login> loginForm = formFactory.form(Login.class).bindFromRequest();

        // Check for errors
        // Uses the validate method defined in the Login class
        if (loginForm.hasErrors()) {
            // If errors, show the form again
            return badRequest(login.render(loginForm, User.getUserById(session().get("email"))));
        }
        else {
            // User Logged in successfully
            // Clear the existing session - resets session id
            session().clear();
            // Store the logged in email in the session (cookie)
            session("email", loginForm.get().getEmail());
        }
        // Return to admin or customer home page
        User u = User.getUserById(session().get("email"));
        if (u.getRole().equals("admin")) {
            return redirect(controllers.routes.AdminController.admin_page());
        }
        else {
            return redirect(controllers.routes.HomeController.index());
        }
    }

    // Logout
    public Result logout() {
        // Delete the current session
        // Generates a new session id
        session().clear();
        flash("success", "You've been logged out");
        return redirect(routes.LoginController.login());
    }

}
