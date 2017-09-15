package models.users;

import java.util.*;
import javax.persistence.*;
import play.data.format.*;
import play.data.validation.*;
import com.avaje.ebean.*;

@Entity
// This is a User of type admin
@DiscriminatorValue("admin")

// Administrator inherits from the User class
public class Administrator extends User{

	public Administrator() {

	}

	public Administrator(String email, String role, String name, String password)
	{
		super(email, role, name, password);
	}
	    public static Finder<String,Administrator> find = new Finder<String,Administrator>(Administrator.class);


         public static List<Administrator> findAllAdmin() {
        return Administrator.find.all();
    }
	
} 