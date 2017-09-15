package models.users;

/**
 * Created by wdd on 13/03/17.
 */
public class Login {

    private String email;
    private String password;

    public String validate(){

        if(User.authenticate(getEmail(), getPassword()) == null){
            return "Invalid User or Password";
        }
        return null;

    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }


}





