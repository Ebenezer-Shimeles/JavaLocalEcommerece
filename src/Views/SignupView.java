package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Main.Main;

public class SignupView extends View{
	 private JPanel mainComponent = new JPanel();
	 private JPanel inputPanel = new JPanel();
	 private JButton signupButton = new JButton("Signup");
	 private JLabel label = new JLabel("Hello, Welcome to our GEBEYA COMERCE");
	 
	 private JTextField emailField = new JTextField("This is my email");
	 private JTextField nameField = new JTextField("This is my user name");
	 private JTextField passwordField = new JTextField("This is my user name");
	 private JTextField lastNameField = new JTextField("This is my user name");
	 private JComboBox genderMaleRadio = new JComboBox();
	 
	 
	 public JComponent getMainComponent(){
		 return mainComponent;
	 }
	 

	 
	 public SignupView(){
//		 inputPanel.setVisible(false);
//		 signupButton.setVisible(false);
//		 label.setVisible(false);
		 label.setBackground(Color.blue);
		// inputPanel.setLayout(null);
		 registerComponent(inputPanel);
		 registerComponent(signupButton);
		 registerComponent(label);
		 registerComponent(emailField);
		 registerComponent(passwordField);
		 registerComponent(genderMaleRadio);
		 registerComponent(nameField);
		 registerComponent(lastNameField);
		 
		 init();
		 
		 
		 
		 inputPanel.setLayout(null);
		
		 label.setBounds(50, 50, 800,20);
		 
		 nameField.setBounds(50, 160, 600, 30);
		 emailField.setBounds(50, 200, 600, 30);
		 genderMaleRadio.setBounds(50, 260, 600, 30);
		 passwordField.setBounds(50, 310, 600, 30);
		 signupButton.setBounds(50, 360, 600, 30);
		 
		 inputPanel.add(signupButton);
		 inputPanel.add(label);
		 inputPanel.add(emailField);
		 inputPanel.add(passwordField);
		 inputPanel.add(genderMaleRadio);
		 inputPanel.add(nameField);
		 inputPanel.add(lastNameField);
		 inputPanel.setBackground(Color.LIGHT_GRAY);
		 mainComponent.add(inputPanel);
		 
		 signupButton.addActionListener(new SignUpButtonListener());
	 }
     public JComponent build() {
    	 return inputPanel;
     }
 
     public void show() {
    	 System.out.println("Showing signup");
    	 inputPanel.setVisible(true);
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