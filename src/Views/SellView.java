package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Interactors.SaleObjectInteractor;
import Main.Globals;

public class SellView extends View implements ActionListener{
	@Override public void actionPerformed(ActionEvent e) {
	    try {	
	    	name = nameF.getText();
	    	brand = brandF.getText();
	    	desc = descrF.getText();
	    	money = Double.parseDouble(moneyF.getText());
	    	quantity = Integer.parseInt(quantityF.getText());
	    	int cata = cataBox.getSelectedIndex();
	    	SaleObjectInteractor.addSaleObject(Globals.userId, name, brand, quantity, desc, money, cata + 1);
	    	
	    	
	    	
	    
	    	Main.Main.mainWindow.goToView("/main");
	    }catch(NumberFormatException ex) {
	    	showMessage("Invalid number format");
	    	return;
	    }
	    catch(Exception c) {
	    	showMessage("Something went wrong");
	    	try {
				Main.Main.mainWindow.goToView("/main");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	c.printStackTrace();
	    }
	}
   
	
	private String name = "";
	private String brand = "";
	private String desc = "";
	private double money = 0;
	private int quantity = 0;
	
	private String[] catas = {"Shoes and Cloth", "Beuty and cosmotics", "Electronics", "Furniture", "Custom made","Other"};
	
	private JLabel cataLabel = new JLabel("Catagory");
	private JComboBox cataBox =  new JComboBox(catas);
	private JPanel main = new JPanel();
	private JLabel title = new JLabel("Sell Object");
	private JTextField nameF = new JTextField("Name");
	private JTextField brandF = new JTextField("Brand");
	private JLabel descT = new JLabel("Description: ");
	private JTextArea descrF = new JTextArea("Description....", 5, 5);
	private JTextField moneyF = new JTextField("Cost");
	private JTextField quantityF = new JTextField("Quantity");
	private JButton sendButton = new JButton("Send!  ");
	private JButton backButton = new JButton("Back to home");
	
	
	public SellView(){
		main.setLayout(null);
		backButton.addActionListener(new GoToHome());
		sendButton.addActionListener(this);
	}
	
	
	@Override
	public JComponent build() {
		main.removeAll();
		registerComponent(main, nameF, brandF, descT, descrF, moneyF, quantityF, sendButton, cataLabel, cataBox,
				backButton, title);
		JComponent [] comps = {  title, nameF, brandF,descT,  descrF, moneyF, quantityF, cataLabel, cataBox, sendButton,
				backButton};
	    for(int i=0;i<comps.length;i++) {
	    	comps[i].setBounds(300, 10 + 40 * i, 300, 30);
	    	registerComponent(comps[i]);
	    	main.add(comps[i]);
	    }
		//init();
		return main;
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

class GoToHome implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Main.Main.mainWindow.goToView("/main");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}