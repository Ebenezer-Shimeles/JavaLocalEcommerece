package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Main.Main;
import Main.Globals;
import Interactors.UserInteractor;

public class SignupView extends View implements ActionListener, ItemListener{
	
	private String name, email, lastName, password; //add getters and setter latter
	int age;
	char gender;
	
	
	
	 private String[] javaOptions = {"Male", "Female"};
	 private JButton goToSignup = new JButton("Or Login");
	 private JPanel mainComponent = new JPanel();
	 private JPanel inputPanel = new JPanel();
	 private JButton signupButton = new JButton("Register");
	 private JLabel label = new JLabel("Hello, Welcome to our GEBEYA COMERCE");
	 private final static int inputLen = 300; 
	 private JTextField emailField = new JTextField("Email");
	 private JTextField nameField = new JTextField("Name");
	 private JTextField passwordField = new JTextField("Password");
	 private JTextField lastNameField = new JTextField("Last Name");
	 private JTextField ageField = new JTextField("age");
	 private JComboBox genderMaleRadio = new JComboBox(javaOptions);
	 private JLabel girlImage = new JLabel(
			 new ImageIcon(getClass().getClassLoader().getResource("girl.jpg"))
			 );
	 
	 public JComponent getMainComponent(){
		 return mainComponent;
	 }
	 

	 
	 public SignupView(){
//		 inputPanel.setVisible(false);
//		 signupButton.setVisible(false);
//		 label.setVisible(false);
		 label.setBackground(Color.blue);
		// inputPanel.setLayout(null);
		 Font newLabelFont=new Font(label.getFont().getName(),Font.ITALIC,label.getFont().getSize() + 10);
		 label.setFont(newLabelFont);
		 registerComponent(inputPanel);
		 registerComponent(signupButton);
		 registerComponent(label);
		 registerComponent(emailField);
		 registerComponent(passwordField);
		 registerComponent(genderMaleRadio);
		 registerComponent(nameField);
		 registerComponent(lastNameField);
		 registerComponent(girlImage);
		 registerComponent(goToSignup);
		 
		 goToSignup.setBounds(600, 600, 200, 30);
		 registerComponent(ageField);
		 init();
		 
		 
		 
		 inputPanel.setLayout(null);
		
		 label.setBounds(50, 50, 800,20);
		 
		 
		 nameField.setBounds(50, 160, inputLen, 30);
		 emailField.setBounds(50, 200, inputLen, 30);
		 genderMaleRadio.setBounds(50, 260, inputLen, 30);
		 ageField.setBounds(50, 300, inputLen, 30);
		 passwordField.setBounds(50, 350, inputLen, 30);
		 signupButton.setBounds(50, 400, inputLen, 30);
		 girlImage.setBounds(600, 0, 600, 500);
		 inputPanel.setBounds(0,0, 1000, 1000);
		 genderMaleRadio.addItemListener(this);
		 inputPanel.add(signupButton);
		 inputPanel.add(label);
		 inputPanel.add(emailField);
		 inputPanel.add(goToSignup);
		 inputPanel.add(ageField);
		 inputPanel.add(passwordField);
		 inputPanel.add(genderMaleRadio);
		 inputPanel.add(nameField);
		 inputPanel.add(lastNameField);
		 inputPanel.setBackground(Color.white);
		 
		 mainComponent.setLayout(null);
		 
		 mainComponent.add(inputPanel);
		 
		// nameField.addActionListener(this);
		 
		 inputPanel.add(girlImage);
		 goToSignup.addActionListener(new GoToLogin());
		 signupButton.addActionListener(this);
	 }
     public JComponent build() {
    	 return mainComponent;
     }
 
     public void show() {
    	 System.out.println("Showing signup");
    	 inputPanel.setVisible(true);
    	 signupButton.setVisible(true);
    	 label.setVisible(true);
     }
     @Override
     public void actionPerformed(ActionEvent e) {
    	   
           name = this.nameField.getText();
           email = this.emailField.getText();
           lastName = this.lastNameField.getText();
           password = this.passwordField.getText();
           try {
               age = Integer.parseInt( this.ageField.getText());
           }catch(NumberFormatException ex) {
        	   showMessage("The age is not a number");
           }

           if(name == "" || email == "" || password == "" ) {
        	   showMessage("Empty field detected!");
        	   return;
           }
           String id= UserInteractor.createUser(name, lastName, email, gender, password, age);
          
           if(id != null){
        	     System.out.println("IDDDDDDDD: "+ id);
        	     Globals.userId = id;
      		    try {
	    		    Main.mainWindow.goToView("/login");
	    		 }catch(Exception v) {
	    			 v.printStackTrace();
	    		 }
           }
           //showMessage(name + " " + lastName + " "+email + " " + password);
     }
     public void itemStateChanged(ItemEvent e)
     {
          gender = javaOptions[genderMaleRadio.getSelectedIndex()].charAt(0);
     }
}

class GoToLogin implements ActionListener{
	@Override public void actionPerformed(ActionEvent e) {
		  try{
			
			 Main.mainWindow.goToView("/login");
		  }catch(Exception v) {
			  Main.mainWindow.showMessage("HI");
			  v.printStackTrace();
		  }
	}
}
