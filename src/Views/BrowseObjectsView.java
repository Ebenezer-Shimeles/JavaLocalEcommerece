package Views;

import java.awt.BorderLayout;
import java.awt.*;

import javax.swing.*;
import Models.SaleObject;
import Main.Globals;

public class BrowseObjectsView extends View{
    private JPanel mainComponent = new JPanel();
    private JLabel title = new JLabel("Objects For Sale");
  
    private JPanel pane = new JPanel();
    private JScrollPane objScroll = new JScrollPane(pane);
    private JButton backButton = new JButton("Back to main menu");
    
	@Override
	public JComponent build() {
		mainComponent.setLayout(new BorderLayout());
		mainComponent.setBackground(Color.white);
		registerComponent(title, objScroll, backButton, pane);
		SaleObject[] objs;
	    pane.setLayout(new BorderLayout());
//		objScroll.add(pane);

	//	title.setBounds(500, 10, 500, 100);
		if(Globals.searchTerm.equals("")) {
			objs = SaleObject.all();
		}
		else {
			objs = SaleObject.search(Globals.searchTerm);
		}
		var l = new GridBagLayout();
		pane.setLayout(l);
		for(int i=0;i<objs.length&& objs[i] != null;i++) {
			
			var obj = objs[i];
			//objScroll.add(new JLabel("" + obj));
		
			var row = new JPanel();
			row.setLayout(new BorderLayout());
			var label = new JLabel(""+ obj);
			var button = new JButton("Buy");

			row.add(button, BorderLayout.EAST);
			row.add(label);
			var cons = new GridBagConstraints();
			cons.gridx = 0;
			cons.gridy = i* 20;
			l.setConstraints(row, cons);
			pane.add(row);
			
			//pane.setBackground(Color.BLACK);
			System.out.print("Loop=> " + obj);
		}
		mainComponent.add(title, BorderLayout.NORTH);
		mainComponent.add(objScroll);
		return mainComponent;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stu
	}

	@Override
	public JComponent getMainComponent() {
		// TODO Auto-generated method stub
		return mainComponent;
	}

}
