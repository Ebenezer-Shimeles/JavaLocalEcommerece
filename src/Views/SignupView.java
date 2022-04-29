package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Main.Main;

public class SignupView extends View{
	 private JPanel mainComponent = new JPanel();
	 private JButton signupButton = new JButton("Sign up");
	 private JLabel label = new JLabel("Sign up");
	 
	 public JComponent getMainComponent(){
		 return mainComponent;
	 }
	 

	 
	 public SignupView(){
		 mainComponent.setVisible(false);
		 signupButton.setVisible(false);
		 label.setVisible(false);
		 mainComponent.add(signupButton, BorderLayout.SOUTH);
		 mainComponent.add(label, BorderLayout.NORTH);
		 signupButton.addActionListener(new SignUpButtonListener());
	 }
     public JComponent build() {
    	 return mainComponent;
     }
     public void clear() {
    	 mainComponent.setVisible(false);
		 signupButton.setVisible(false);
		 label.setVisible(false);
     }
     public void show() {
    	 System.out.println("Showing signup");
    	 mainComponent.setVisible(true);
    	 signupButton.setVisible(true);
    	 label.setVisible(true);
     }
}


class SignUpButtonListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		 try {
             Main.mainWindow.goToView("/login");
		 }catch(Exception ee) {
			 System.out.print("Page does not exist");
		 }
	}
	
}