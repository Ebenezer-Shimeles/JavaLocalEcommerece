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
   private JButton myAccount = new JButton("Recharge");
   private JLabel ammount = new JLabel("");
   private ImageIcon icon1 = new ImageIcon(getClass().getClassLoader().getResource("icon.jpg"));
   private ImageIcon icon = new ImageIcon(
      icon1.getImage().getScaledInstance(200, 200, 0)
		   );
   ImageIcon im =  new ImageIcon(getClass().getClassLoader().getResource("q.jpg"));
   private ImageIcon im2 = new ImageIcon(
         im.getImage().getScaledInstance(1000,700, 0)
           );
   JLabel lim = new JLabel("",im2,JLabel.CENTER);
   private JLabel top = new JLabel(icon);
   private JButton sellButton = new JButton("Sell!");
  
   public MainView(){
	   registerComponent(logoutButton);
	   logoutButton.setText("Logout");
	   registerComponent(nameLabel, buy, cart, ammount,myAccount,  myObjects, top,sellButton, myTrans,  searchButton, searchInput);
	   init();
	   buy.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   try {
				Main.mainWindow.goToView("/browse");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.print(e1);
				e1.printStackTrace();
			}
		   }
	   });
	   JButton[] buttons = {  buy, myTrans, cart, myObjects, sellButton, myAccount, logoutButton};
	   myAccount.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
				
			   try {
				Main.mainWindow.goToView("/myacc");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.print(e1);
				e1.printStackTrace();
			}
		   
	   }
   });
	   cart.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			
				   try {
					Main.mainWindow.goToView("/mycart");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.print(e1);
					e1.printStackTrace();
				}
			   
		   }
	   });
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
	
	     
	   
	     
	     lim.setBounds(0,0,1000,700);
	     mainComponent.add(lim);
	          
	     nameLabel.setBounds(380, 150, 600, 50);
	   searchInput.setBounds(380, 300, 300, 30);
	   searchButton.setBounds(700, 300, 150, 30);
	   nameLabel.setBounds(380, 200, 600, 50);
	   ammount.setBounds(800, 170, 90, 40);
	   var font = new Font(logoutButton.getFont().getName(), Font.ITALIC, 10);
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
	   lim.add(searchButton);
	   lim.add(searchInput);
	   lim.add(myTrans);
	   lim.add(sellButton);
	   lim.add(myAccount);
	   lim.add(top);
	   lim.add(logoutButton);  
	   lim.add(nameLabel); 
	   lim.add(buy); 
	   lim.add(cart);  
	   lim.add(ammount);
	 //  mainComponent.add( home);  
	  lim.add(myObjects);
	   
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
   			//  user.deposit(1000);
   			//  System.out.print(user.canBuy(1));
   			  ammount.setText("$ " + user.getBalance() + "");
   			  name = user.getName();
   			  lastName = user.getLastName();
   		  }
   	 }catch(SQLException b) {
   		 b.printStackTrace();
   	 }
   	    nameLabel.setText("Welcome, " + name + " " + lastName);
   	    Font nameFont = nameLabel.getFont();
   	    nameLabel.setFont(new Font(nameFont.getName(), Font.ITALIC, 26+ 4));
   	
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
