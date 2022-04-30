package Interactors;

import Models.User;
import Main.Main;
import java.sql.*;

public class UserInteractor {
    public static String createUser(
    		String n,
    		String ln,
    		String e,
    		char g,
    		String p,
    		int a
    		
    ) {
    	//This creates a user and returns its id
    	var user = new User(n, ln, e, g, p, a, 10);
    	
    	if(user.exists()) {
    		Main.mainWindow.showMessage("There is a user with this credentials");
    		return null;
    	}
    	else {
    		user.create();
    		return user.getId() + "";
    	}
    	
    }
    public static String loginUser(String email, String password) {
    	try {
    	  var user = User.findByEmail(email);
    	  if(user == null) {
    		  Main.mainWindow.showMessage("The user does not exist!");
    		  return null; 
    		 }
    	  if(user.checkPassword(password)) {
    		  return user.getId() + "";
    	   }
    	   return null;
    	}catch(SQLException ex) {
    		System.out.print("Error when checking password");
    		return null;
    	}
    }
}
