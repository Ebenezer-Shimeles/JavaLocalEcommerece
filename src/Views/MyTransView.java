package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

import Main.Globals;
import Main.Main;
import Models.*;

public class MyTransView extends View implements ActionListener{
    private JPanel main = new JPanel();
	private Transaction[] transactions;
	private JTextArea transDesc = new JTextArea(30, 30);
	public MyTransView(){
	      registerComponent(main, transDesc);
		  init();
		   main.setLayout(new BorderLayout());
	}
	@Override
	public JComponent build() {
		// TODO Auto-generated method stub
		transDesc.setText("TRANSCATIONS: ");
		main.setBackground(Color.white);
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
		   			  transactions = Transaction.findByUserId(Globals.userId);
		   		  }
		   	 }catch(SQLException b) {
		   		 b.printStackTrace();
		   	 }
		     main.add(transDesc);
		     var back = new JButton("Go Back");
		    back.addActionListener(this);
		     main.add(back, BorderLayout.SOUTH
		    		 
		    );
		     main.add(new JLabel("Your transactions"), BorderLayout.NORTH);
		     transDesc.setText("");
		     for(Transaction t: transactions) {
		    	 
		    	 transDesc.append(""+ t.toString()+"\n");
		    	 
		     }
		return main;
	}
    
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Main.mainWindow.goToView("/main");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public JComponent getMainComponent() {
		// TODO Auto-generated method stub
		return main;
	}
      
}
