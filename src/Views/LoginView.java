package Views;

import javax.swing.*;

import Main.Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends View{
	 private JButton loginButton = new JButton("lofddfdgin");
	 
	 public JComponent getMainComponent(){
		 return loginButton;
	 }
	 
	 public LoginView(){
		 loginButton.setVisible(false);
		 loginButton.addActionListener(new LoginButtonListener());
	 }
     public JComponent build() {
    	 return  loginButton;
     }
     public void clear() {
    	 loginButton.setVisible(false);
     }
     public void show() {
    	 loginButton.setVisible(true);
     }
}
class LoginButtonListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		 try {
             Main.mainWindow.goToView("/signup");
		 }catch(Exception ee) {
			 System.out.print("Page does not exist");
		 }
	}
	
}