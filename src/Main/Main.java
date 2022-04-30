package Main;

import java.sql.*;
import java.awt.*;
import javax.swing.*;
import Views.*;

import Models.User;
public class Main {
	public static MainWindow mainWindow = new MainWindow("Gebeya Ecommerece");
	
    public static void main(String args[]) {
    	try {
    		//String iconLocation = "C:\\Users\\natan\\eclipse-workspace\\Ecommerece\\src\\Views\\icon.jpg";
    		var user = new User("Ebenezer", "Shimeles", "eye@yahoo.com",'M', "muze", 20, 7);
    		
    		System.out.println(user.exists());
    		
    	
    		
    		mainWindow.setSize(1000, 700);
    		mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    		//mainWindow.setIconImage(icon.getImage());
    		mainWindow.setBackground(Color.white);
    		mainWindow.getContentPane().setBackground(Color.white);
    	
      		mainWindow.registerView("/login", new LoginView());
      		mainWindow.registerView("/signup", new SignupView());
      		mainWindow.registerView("/main", new MainView());
      		//mainWindow.setLayout(new FlowLayout());
    		
    		//mainWindow.registerView("/login", new LoginView());
    		mainWindow.setVisible(true);
    		
    		mainWindow.goToView("/signup");
    		
    	
    		//mainWindow.setIconImage(new Image(""));
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
}
