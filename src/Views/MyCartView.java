package Views;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;
import Interactors.SaleObjectInteractor;
import Main.*;
import Models.Cart;
import Models.SaleObject;
public class MyCartView extends View{
    private JPanel mainComponent = new JPanel(new BorderLayout());
    private JPanel objects= new JPanel();
    private JButton backButton = new JButton("Back To Home");
	private JScrollPane scroll = new JScrollPane(objects);
	
	
	@Override
	public JComponent build() {
		mainComponent.setBackground(Color.white);
	    objects.removeAll();
		//mainComponent.removeAll();
		//scroll.removeAll();
		// TODO Auto-generated method stub
		mainComponent.add(backButton, BorderLayout.SOUTH);
		mainComponent.add(scroll, BorderLayout.CENTER);
	   
		registerComponent(mainComponent, backButton, scroll, objects);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Main.mainWindow.goToView("/main");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		var cart = new Cart(Globals.userId);
		SaleObject[] objs = cart.getSaleObjects();
		
	  //  mainComponent.setLayout(new BorderLayout());
//		objScroll.add(mainComponent);
       
	//	title.setBounds(500, 
		var l = new GridBagLayout();
		objects.setLayout(l);
		for(int i=0;i<objs.length&& objs[i] != null;i++) {
			
			var obj = objs[i];
			//objScroll.add(new JLabel("" + obj));
		
			var row = new JPanel();
			row.setLayout(new FlowLayout());
			var label = new JLabel(""+ obj);
			var button = new JButton("Remove from cart");
            var buy = new JButton("Buy");
            buy.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						SaleObjectInteractor.buyObject(obj.getId(), obj.getOwnerId(), Globals.userId);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						// Main.Main.mainWindow.clear();
						Main.mainWindow.showMessage("Can not buy!");
						e1.printStackTrace();
					}

				}
			});
            
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				//	obj.delete();
					try {
						SaleObjectInteractor.removeFromCart(obj.getId(), Globals.userId);
						Main.mainWindow.goToView("/main");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			row.add(new JLabel(new CataImage().build(obj.getCata() - 1)));
			row.add(label);
			row.add(buy);
	       	row.add(button);
			
			var cons = new GridBagConstraints();
			cons.gridx = 0;
			cons.gridy = i* 40;
			l.setConstraints(row, cons);
			objects.add(row);
			//registerComponent(row);
			//mainComponent.setBackground(Color.BLACK);
			System.out.print("Loop=> " + obj);
		}
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
