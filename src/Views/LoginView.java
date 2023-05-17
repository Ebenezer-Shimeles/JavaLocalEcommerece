package Views;

import javax.swing.*;

import Main.Main;
import Main.Globals;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Interactors.UserInteractor;
import Models.User;

public class LoginView extends View implements ActionListener{
     static final int inputLen = 300;
     
     String email="", password = "";
   
     JPanel panel = new JPanel();
     private JButton goToSignup = new JButton("Or register");
     private JButton loginButton = new JButton("Login");
     private JPanel inputPanel = new JPanel();
    
     private JLabel label = new JLabel("Hello, Welcome to our GEBEYA COMERCE");
     private JTextField emailField = new JTextField("Email");
     private JTextField passwordField = new JTextField("Password");
     private JLabel girlImage = new JLabel(
         new ImageIcon(getClass().getClassLoader().getResource("girl.jpg"))
     );
     //
     ImageIcon im =  new ImageIcon(getClass().getClassLoader().getResource("q1.png"));
     private ImageIcon im2 = new ImageIcon(
           im.getImage().getScaledInstance(1000,700, 0)
             );
     JLabel bgImage = new JLabel("",im2,JLabel.CENTER);
        //
     public JComponent getMainComponent(){
       return inputPanel;
     }
     
     public LoginView(){
     //  registerComponent(panel);
       
     registerComponent(goToSignup);
       registerComponent(loginButton);
       registerComponent(emailField);
       registerComponent(passwordField);
       registerComponent(girlImage);
       registerComponent(inputPanel);
       init();
       
       inputPanel.setLayout(null);
       girlImage.setBounds(600, 0, 600, 500);
       label.setBounds(80, 50, 800 ,20);
       Font newLabelFont = label.getFont();
       //label.setFont(new Font(newLabelFont.getName(), Font.ITALIC, newLabelFont.getSize() + 10));
       label.setFont(new Font("Serif",Font.ITALIC,25));
       emailField.setBounds(80, 160, inputLen, 30);
       passwordField.setBounds(80, 200, inputLen, 30);
       goToSignup.setBounds(600, 600, 200, 30);
       //mainComponent.setLayout(null);
       loginButton.setBounds(110,240,150,30);
       bgImage.add(label);
       bgImage.add(goToSignup);
       bgImage.add(loginButton);
       bgImage.add(emailField);
       bgImage.add(passwordField);
       bgImage.add(girlImage);
       bgImage.setBounds(0,0,1000,700);
       inputPanel.add(bgImage);
      // inputPanel.setBackground(new Color(173,193,240));
     
       loginButton.addActionListener(this);
       goToSignup.addActionListener(new GoToSignUp());
      
       //loginButton.addActionListener(new LoginButtonListener());

     }
       public JComponent build() {
         return  inputPanel;
       }
   
       public void show() {
         inputPanel.setVisible(true);
      
       }
       @Override 
       public void actionPerformed(ActionEvent e) {
         email = emailField.getText();
         password= passwordField.getText();
         if(email == "" || password == "") {
           this.showMessage("An empty field is detected");
           return;
         }
         try {
             UserInteractor.loginUser(email, password);
             String id = User.findByEmail(email).getId() + "";
             if (id != null) {
                 Globals.userId = id;
                 showMessage("Welcome!");
                 try {
                     Main.mainWindow.goToView("/main");
                 } catch (Exception v) {
                     v.printStackTrace();
                 }
             } else {
                 showMessage("Wrong password");
             }
         }catch(Exception ev){
             showMessage(ev.toString());
         }
         
       }
}

class GoToSignUp implements ActionListener{
  @Override public void actionPerformed(ActionEvent e) {
      try{
        Main.mainWindow.goToView("/signup");
      }catch(Exception v) {
        v.printStackTrace();
      }
  }
}