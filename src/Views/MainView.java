package Views;

import javax.swing.*;
import java.awt.*;
import Main.Globals;
import Main.Main;
import Models.User;
import java.sql.*;

public class MainView extends View{
   private String name;
   private JLabel nameLabel = new JLabel();
   private JPanel mainComponent = new JPanel();
 
   
   public MainView(){
	   mainComponent.setLayout(null);
	   nameLabel.setBounds(0, 0, 50, 50);
	   mainComponent.add(nameLabel);
	   
   }
    public JComponent build() {
    	if(Globals.userId == "") {
   		 try { 
   			  showMessage("No Id found!");
   			  Main.mainWindow.goToView("/login");
   		 }catch(Exception v) {
   			 v.printStackTrace();
   		 }
   	 }
   	 try{
   		  var user = User.findByPk(Globals.userId);
   		  if(user == null) {
   			  try { 
   				  showMessage("user not found!");
   				  Main.mainWindow.goToView("/login");
   			 }catch(Exception v) {
   				 v.printStackTrace();
   			 }
   		  }
   		  else {
   			  name = user.getName();
   		  }
   	 }catch(SQLException b) {
   		 b.printStackTrace();
   	 }
   	nameLabel.setText(name);
   	
    	 Main.mainWindow.showMessage("userID: " + Globals.userId);
          return mainComponent;    	
    }
   // public  void clear();
    public void show() {}
    public JComponent getMainComponent() {
    	return mainComponent;
    }
}
