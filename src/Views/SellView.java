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
	    	if(money <= 0) {
	    		showMessage("Error: Money shouldn't be less than or equal to zero");
	    		return;
	    	}
	    	
	    	quantity = Integer.parseInt(quantityF.getText());
	    	if(quantity <= 0)
	    	{
	    		showMessage("Error: Quantity shouldn't be less than or equal to zero");
	    		return;
	    	}
	    	int cata = cataBox.getSelectedIndex();
			try{
	    	    SaleObjectInteractor.addSaleObject(Globals.userId, name, brand, quantity, desc, money, cata + 1);
				Main.Main.mainWindow.goToView("/main");
			}catch (Exception ev){
				showMessage(ev.toString());
			}
	    	
	    	
	    	
	    

	    }catch(NumberFormatException ex) {
	    	showMessage("Invalid number format given in price or quantity!");
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
	
	ImageIcon im =  new ImageIcon(getClass().getClassLoader().getResource("2.jpg"));
    private ImageIcon bgImage = new ImageIcon(
          im.getImage().getScaledInstance(1000,700, 0)
            );
    ImageIcon im1 =  new ImageIcon(getClass().getClassLoader().getResource("ImageA.png"));
    private ImageIcon inputI = new ImageIcon(
          im1.getImage().getScaledInstance(200,200, 0)
            );
    
	
	public SellView(){
		main.setLayout(null);
		backButton.addActionListener(new GoToHome());
		sendButton.addActionListener(this);
	}
	
	
	@Override
	public JComponent build() {
		
		JLabel All= new JLabel("",bgImage,JLabel.CENTER) ;
		 JButton ImageInput= new JButton(inputI) ;
		 JLabel ImageText= new JLabel("Image") ;
		    All.setBounds(0,0,1000,700);
		    ImageText.setBounds(500,10,300,30);
		    ImageInput.setBounds(500,300,150,150);
		    
		    All.add(ImageText);
		    All.add(ImageInput);
		
		main.removeAll();
		registerComponent(main, nameF, brandF, descT, descrF, moneyF, quantityF, sendButton, cataLabel, cataBox,All,
				backButton, title);
		JComponent [] comps = {  title, nameF, brandF,descT,  descrF, moneyF, quantityF, cataLabel, cataBox, sendButton,
				backButton};
	    for(int i=0;i<comps.length;i++) {
	    	comps[i].setBounds(150, 10 + 40 * i, 300, 30);
	    	registerComponent(comps[i]);
	    	All.add(comps[i]);
	    }
		//init();
	    main.add(All);
		return main;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	  public void clear() {
		   /*
		    * 	private JTextField nameF = new JTextField("Name");
	private JTextField brandF = new JTextField("Brand");
	private JLabel descT = new JLabel("Description: ");
	private JTextArea descrF = new JTextArea("Description....", 5, 5);
	private JTextField moneyF = new JTextField("Cost");
	private JTextField quantityF = new JTextField("Quantity");
		    * */
		  nameF.setText("Name");
		  brandF.setText("Brand");
		  descrF.setText("Description");
		  moneyF.setText("Cost");
		  quantityF.setText("Quantity");
		  
		   super.clear();
	
		   
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