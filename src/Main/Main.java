package Main;

import java.sql.*;
import Models.*;
import java.awt.*;
import javax.swing.*;
import Views.*;

import Models.User;
import Models.SaleObject; 

public class Main {
	public static MainWindow mainWindow = new MainWindow("Gebeya Ecommerece");

	public static void main(String args[]) {
		try {
			// String iconLocation =
			// "C:\\Users\\natan\\eclipse-workspace\\Ecommerece\\src\\Views\\icon.jpg";
//			var user = new User("Ebenezer", "Shimeles", "eye@yahoo.com", 'M', "muze", 20, 7);
//   
//			System.out.println(user.exists());
//            for(SaleObject obj: SaleObject.search("nu")) {
//            	System.out.println(obj);
//            }
			// mainWindow.setBounds(10, 10, 1000, 700);
			mainWindow.setSize(1000, 700);
			mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			// mainWindow.setIconImage(icon.getImage());
			mainWindow.setBackground(Color.white);
			mainWindow.getContentPane().setBackground(Color.white);

			mainWindow.registerView("/login", new LoginView());
			mainWindow.registerView("/signup", new SignupView());
			mainWindow.registerView("/main", new MainView());
			mainWindow.registerView("/transactions", new MyTransView());
			mainWindow.registerView("/sell", new SellView());
			mainWindow.registerView("/browse", new BrowseObjectsView());
			mainWindow.registerView("/myobjs", new MyObjectsView());
			// mainWindow.setLayout(new FlowLayout());

			// mainWindow.registerView("/login", new LoginView());
			mainWindow.setVisible(true);
			Globals.userId = "1";
			mainWindow.goToView("/main");
//    		var t = new Transaction();
//    		t.setAmmount(5000);
//    		t.setBuyer(User.findByPk("" + 1));
//    		t.setSellerId("1");
//    		t.create();

//    		var b = new Comments();
//    		b.setMsg("This app is awesome");
//    		b.setObjectId("1");
//    		b.setWriterId("1");
//    	    b.create();
			// System.out.println(Comments.getByObjectId("1")[0]);
//    		System.out.println(Transaction.findByUserId("1")[1]);
			// Return to signup

			// mainWindow.setIconImage(new Image(""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
