package Main;

import java.sql.*;
import java.awt.*;
import javax.swing.*;
import Views.*;

import Models.User;
public class Main {
	public static MainWindow mainWindow = new MainWindow("Ecommerece");
	
    public static void main(String args[]) {
    	try {
    		String iconLocation = "C:\\Users\\natan\\eclipse-workspace\\Ecommerece\\src\\Views\\icon.jpg";
    		
    		ImageIcon icon = new ImageIcon(iconLocation);
    	
    		
    		mainWindow.setSize(1200, 600);
    		mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    		mainWindow.setIconImage(icon.getImage());
    		mainWindow.setBackground(Color.white);
    		mainWindow.getContentPane().setBackground(Color.white);
    		mainWindow.registerView("/signup", new SignupView());
      		mainWindow.registerView("/login", new LoginView());
      		//mainWindow.setLayout(new FlowLayout());
    		
    		//mainWindow.registerView("/login", new LoginView());
    		mainWindow.setVisible(true);
    		
    		mainWindow.goToView("/login");
    		
    	
    		//mainWindow.setIconImage(new Image(""));
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
}
