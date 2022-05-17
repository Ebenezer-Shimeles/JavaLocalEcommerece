package Views;

import javax.swing.*;
import java.awt.*;
import Main.Globals;
import Main.Main;
import Models.User;
import java.sql.*;
import java.awt.event.*;

public class MainView extends View{
   private String searchTerm = "";
   private JTextField searchInput = new JTextField("Search for anything to buy...");
   private JButton searchButton =  new JButton("Search");
   private String name;
   private String lastName;
   private JLabel nameLabel = new JLabel();
   private JPanel mainComponent = new JPanel();
   private Icon logoutIcon = new ImageIcon("download.png");
   private JButton logoutButton = new JButton();
   private JButton buy = new JButton("Buy!");
   private JButton myTrans = new JButton("My Transcations");
   private JButton cart= new JButton("My Cart");
   private JButton home= new JButton("Home ");
   private JButton myObjects = new JButton("My Objects");
   private ImageIcon icon1 = new ImageIcon(getClass().getClassLoader().getResource("icon.jpg"));
   private ImageIcon icon = new ImageIcon(
      icon1.getImage().getScaledInstance(200, 200, 0)
		   );
   private JLabel top = new JLabel(icon);
   private JButton sellButton = new JButton("Sell!");
  
   public MainView(){
	   registerComponent(logoutButton);
	   logoutButton.setText("Logout");
	   registerComponent(nameLabel, buy, cart,  myObjects, top,sellButton, myTrans,  searchButton, searchInput);
	   init();
	   buy.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   try {
				Main.mainWindow.goToView("/browse");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		   }
	   });
	   JButton[] buttons = {  buy, myTrans, cart, myObjects, sellButton, logoutButton,};
	   searchButton.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   Globals.searchTerm = searchInput.getText();
			   try {
				Main.mainWindow.goToView("/browse");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		   }
	   });
	   myObjects.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   try {
				Main.mainWindow.goToView("/myobjs");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		   }
	   });
	   searchInput.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (searchInput.getText().equals("Search for anything to buy...")) {
		            searchInput.setText("");
		            searchInput.setForeground(Color.BLACK);
		        }
		    }
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (searchInput.getText().isEmpty()) {
		            searchInput.setForeground(Color.GRAY);
		            searchInput.setText("Search for anything to buy...");
		        }
		    }
		    });
	   mainComponent.setLayout(null);
	   searchInput.setBounds(380, 300, 300, 30);
	   searchButton.setBounds(700, 300, 150, 30);
	   nameLabel.setBounds(380, 200, 600, 50);
	   var font = new Font(logoutButton.getFont().getName(), Font.ITALIC, logoutButton.getFont().getSize()-3);
	   mainComponent.setBackground(Color.white);
	   top.setBounds(0,0, 200, 200);
	   for(int i=0;i < buttons.length; i++) {
		   JButton btn =  buttons[buttons.length - i -1 ];
	         btn.setFont(font);
	  
	        btn.setBounds(870 - 100*i , 40, 100, 30);
	   }
	   
	   sellButton.addActionListener(new ActionListener() {
	                  @Override
	                  public void actionPerformed(ActionEvent n) {
	                	  try {
							Main.mainWindow.goToView("/sell");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                  }
	   });
	   logoutButton.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent n) {
			   Globals.userId = "";
			   try {
			       Main.mainWindow.goToView("/login");
			   }catch(Exception v) {
				  v.printStackTrace();
			   }
		   }
	   });
	   myTrans.addActionListener(new ActionListener() {
		   @Override
		   public void actionPerformed(ActionEvent e) {
			 try {
				Main.mainWindow.goToView("/transactions");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}   
		   }
	   });
	   mainComponent.add(searchButton);
	   mainComponent.add(searchInput);
	   mainComponent.add(myTrans);
	   mainComponent.add(sellButton);
	   mainComponent.add(top);
	   mainComponent.add(logoutButton);  
	   mainComponent.add(nameLabel); 
	   mainComponent.add(buy); 
	   mainComponent.add(cart);  
	 //  mainComponent.add( home);  
	   mainComponent.add(myObjects);
	   
   }
    public JComponent build() {
    	mainComponent.setLayout(null);
    	if(Globals.userId == "") {
   		 try { 
   			  showMessage("No Id found!");
   			  Main.mainWindow.goToView("/login");
   		 }catch(Exception v) {
   			 v.printStackTrace();
   		 }
   	 }
    User user;
   	 try{
   		   user = User.findByPk(Globals.userId);
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
   			  lastName = user.getLastName();
   		  }
   	 }catch(SQLException b) {
   		 b.printStackTrace();
   	 }
   	    nameLabel.setText("Welcome, " + name + " " + lastName);
   	    Font nameFont = nameLabel.getFont();
   	    nameLabel.setFont(new Font(nameFont.getName(), Font.ITALIC, nameFont.getSize() + 4));
   	
    	// Main.mainWindow.showMessage("userID: " + Globals.userId);
          return mainComponent;    	
    }
   // public  void clear();
    public void show() {}
    public JComponent getMainComponent() {
    	return mainComponent;
    }
}
class GoToMyTranscation implements ActionListener{
	@Override 
	public void actionPerformed(ActionEvent e) {
	      try {
			Main.mainWindow.goToView("/transactions");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}
}
