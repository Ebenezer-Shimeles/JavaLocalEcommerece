package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Main.Main;

public class SignupView extends View{
	 private JPanel mainComponent = new JPanel();
	 private JButton signupButton = new JButton("Hello, Welcome to our Gebeya Ecommerce");
	 private JLabel label = new JLabel("Sign up");
	 
	 private JTextField emailField = new JTextField("This is my email");
	 private JTextField nameField = new JTextField("This is my user name");
	 private JTextField passwordField = new JTextField("This is my user name");
	 private JTextField lastNameField = new JTextField("This is my user name");
	 private JComboBox genderMaleRadio = new JComboBox();
	 
	 
	 public JComponent getMainComponent(){
		 return mainComponent;
	 }
	 

	 
	 public SignupView(){
//		 mainComponent.setVisible(false);
//		 signupButton.setVisible(false);
//		 label.setVisible(false);
		 label.setBackground(Color.blue);
		 mainComponent.setLayout(null);
		 registerComponent(mainComponent);
		 registerComponent(signupButton);
		 registerComponent(label);
		 registerComponent(emailField);
		 registerComponent(passwordField);
		 registerComponent(genderMaleRadio);
		 registerComponent(nameField);
		 registerComponent(lastNameField);
		 
		 init();
		 
		 
		 
		 //mainComponent.setLayout(null);
		
		 label.setBounds(0, 0, 30,10);
		 signupButton.setBounds(0, 50, 150, 100);
		 nameField.setBounds(150, 80, 80, 30);
		 emailField.setBounds(150, 100, 80, 60);
		 genderMaleRadio.setBounds(150, 130, 80, 60);
		 passwordField.setBounds(150, 150, 80, 60);
		 
		 mainComponent.add(signupButton);
		 mainComponent.add(label);
		 mainComponent.add(emailField);
		 mainComponent.add(passwordField);
		 mainComponent.add(genderMaleRadio);
		 mainComponent.add(nameField);
		 mainComponent.add(lastNameField);
		 
		 signupButton.addActionListener(new SignUpButtonListener());
	 }
     public JComponent build() {
    	 return mainComponent;
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