package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Main.Main;

public class SignupView extends View implements ActionListener{
	
	private String name, email, lastName, password; //add getters and setter latter
	
	
	
	 private String[] javaOptions = {"Male", "Female"};
	 private JPanel mainComponent = new JPanel();
	 private JPanel inputPanel = new JPanel();
	 private JButton signupButton = new JButton("Register");
	 private JLabel label = new JLabel("Hello, Welcome to our GEBEYA COMERCE");
	 private final static int inputLen = 300; 
	 private JTextField emailField = new JTextField("Email");
	 private JTextField nameField = new JTextField("Name");
	 private JTextField passwordField = new JTextField("Password");
	 private JTextField lastNameField = new JTextField("Last Name");
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
		 Font newLabelFont=new Font(label.getFont().getName(),Font.ITALIC,label.getFont().getSize());
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
		 init();
		 
		 
		 
		 inputPanel.setLayout(null);
		
		 label.setBounds(50, 50, 800,20);
		 
		 
		 nameField.setBounds(50, 160, inputLen, 30);
		 emailField.setBounds(50, 200, inputLen, 30);
		 genderMaleRadio.setBounds(50, 260, inputLen, 30);
		 passwordField.setBounds(50, 310, inputLen, 30);
		 signupButton.setBounds(50, 360, inputLen, 30);
		 girlImage.setBounds(600, 0, 600, 500);
		 inputPanel.setBounds(0,0, 1000, 1000);
		 
		 inputPanel.add(signupButton);
		 inputPanel.add(label);
		 inputPanel.add(emailField);
		 inputPanel.add(passwordField);
		 inputPanel.add(genderMaleRadio);
		 inputPanel.add(nameField);
		 inputPanel.add(lastNameField);
		 inputPanel.setBackground(Color.white);
		 
		 girlImage.setBackground(Color.GRAY);
		 mainComponent.setLayout(null);
		 
		 mainComponent.add(inputPanel);
		 
		// nameField.addActionListener(this);
		 
		 inputPanel.add(girlImage);
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
          
           showMessage(name + " " + lastName + " "+email + " " + password);
     }
}


