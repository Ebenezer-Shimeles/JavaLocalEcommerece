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
	char gender='M';
	
	
	private ImageIcon im =  new ImageIcon(getClass().getClassLoader().getResource("q.jpg"));
    private ImageIcon im2 = new ImageIcon(
          im.getImage().getScaledInstance(1000,700, 0)
            );
    private JLabel bgImage = new JLabel("",im2,JLabel.CENTER);
	 private String[] javaOptions = {"Male", "Female"};
	 private JButton goToSignup = new JButton("Or Login");
	 private JPanel mainComponent = new JPanel();
	 private JPanel inputPanel = new JPanel();
	 private JButton signupButton = new JButton("Register");
	 private JLabel label = new JLabel("Hello, Welcome to our GEBEYA COMERCE");
	 private final static int inputLen = 300; 
	 private JTextField emailField = new JTextField("Email");
	 private JTextField nameField = new JTextField("First Name");
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

	 }
     public JComponent build() {
		 label.setBackground(Color.blue);
		// inputPanel.setLayout(null);
		 
		 Font newLabelFont=new Font(label.getFont().getName(),Font.ITALIC,30);
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
		  label.setFont(new Font("serif", Font.ITALIC, 25));
		 label.setBounds(50, 50, 800,20);
		 
		 
		 nameField.setBounds(50, 160, inputLen, 30);
		 lastNameField.setBounds(50, 200, inputLen, 30);
		 emailField.setBounds(50, 260, inputLen, 30);
		 genderMaleRadio.setBounds(50, 300, inputLen, 30);
		 ageField.setBounds(50, 350, inputLen, 30);
		 passwordField.setBounds(50, 400, inputLen, 30);
		 signupButton.setBounds(50, 600, inputLen, 30);
		 girlImage.setBounds(600, 0, 1000, 500);
		 inputPanel.setBounds(0,0, 1000, 1000);
		 genderMaleRadio.addItemListener(this);
		 bgImage.add(signupButton);
		 bgImage.add(label);
		 bgImage.add(emailField);
		 bgImage.add(goToSignup);
		 bgImage.add(ageField);
		 bgImage.add(passwordField);
		 bgImage.add(genderMaleRadio);
		 bgImage.add(nameField);
		 bgImage.add(lastNameField);
		 bgImage.setBackground(Color.white);
		 bgImage.setBounds(0,0,1000,700);
		 inputPanel.add(bgImage);
		 mainComponent.setLayout(null);
		 
		 mainComponent.add(inputPanel);
		 
		// nameField.addActionListener(this);
		 
		// inputPanel.add(girlImage);
		 goToSignup.addActionListener(new GoToLogin());
		 signupButton.addActionListener(this);
    	 return mainComponent;
     }
  
     public void show() {
    	// new JApplet();
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
               if(age  < 18) {
            	   showMessage("You must be at least 18 years old to continue");
            	   return;
               }
           }catch(NumberFormatException ex) {
        	   showMessage("The age is not a number");
        	   return;
           }
           
           if(!email.contains(".") || !email.contains("@")) {
        	   showMessage("Email is invalid");
        	   return;
           }
           if(password.length() < 5) {
        	   showMessage("Password is short :(");
        	   return;
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
			  Main.mainWindow.showMessage("Error going to /main");
			  //v.printStackTrace();
		  }
	}
}
