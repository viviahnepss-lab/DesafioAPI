package Model;

public class Users {
     private String token;
     private String password;


     public Users(String firstName, String password) {
          this.token = firstName;
          this.password = password;
     }

     public Users() {

     }

     public String getToken() {
          return token;
     }

     public void setToken(String token) {
          this.token = token;
     }

     public String getPassword() {
          return password;
     }

     public void setPassword(String password) {
          this.password = password;
     }



     }
