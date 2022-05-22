package Views;

import javax.swing.*;

import Main.*;
import Models.User;

import java.awt.*;
import java.awt.event.*;

public class MyAccountView extends View{
    private JPanel mainComponent = new JPanel(new BorderLayout());
    private JPanel low = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JButton back = new JButton("Back to home");
    private JButton deposit = new JButton("Deposit");
    private JTextField howMuch = new JTextField("How much ?");

    
	@Override
	public JComponent build() {
		registerComponent(low, deposit, back, mainComponent);
		mainComponent.setBackground(Color.white);
		low.add(deposit);
		low.add(back);
		mainComponent.add(howMuch);
		low.setBackground(Color.white);
		mainComponent.add(low, BorderLayout.EAST);
		deposit.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
				   try {
					   User.findByPk(Globals.userId).deposit(Double.parseDouble(howMuch.getText()));
					    Main.mainWindow.goToView("/main");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					showMessage("Invalid format!");
					System.out.print(e1);
					e1.printStackTrace();
				}
			   
		   }
	   });
		back.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			
				   try {
					 mainComponent.removeAll();
					Main.mainWindow.goToView("/main");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.print(e1);
					e1.printStackTrace();
				}
			   
		   }
	   });
		
		return mainComponent;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JComponent getMainComponent() {
		// TODO Auto-generated method stub
		return mainComponent;
	}

}
