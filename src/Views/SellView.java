package Views;

import javax.swing.*;

public class SellView extends View{
   
	private JPanel main = new JPanel();
	private JTextField nameF = new JTextField();
	private JTextField brandF = new JTextField();
	private JTextArea descrF = new JTextArea();
	private JTextField moneyF = new JTextField();
	private JTextField quantityF = new JTextField();
	private JButton sendButton = new JButton("Send!  ");
	private JButton backButton = new JButton("Back to home");
	
	SellView(){
		main.setLayout(null);
	}
	
	
	@Override
	public JComponent build() {
		registerComponent(main, nameF, brandF, descrF, moneyF, quantityF, sendButton);
		init();
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
