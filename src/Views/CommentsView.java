package Views;

import java.sql.SQLException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import Main.*;
import Models.*;
public class CommentsView extends View {
    private JLabel title = new JLabel("Comments ...");
	private JPanel mainComponent = new JPanel();
	private GridBagLayout l = new GridBagLayout();
	private JPanel scrollMain = new JPanel(l);
	private JButton backButton = new JButton("Back to home");
	private JScrollPane scroll = new JScrollPane(scrollMain);
	private JTextArea descr = new JTextArea(10, 100);
	@Override
	public JComponent build() {
		mainComponent.setBackground(Color.white);
		scrollMain.removeAll();
		registerComponent(mainComponent, scrollMain, scroll, descr, title, backButton, scrollMain);
		
		try {
			descr.setText(SaleObject.findById(Globals.userId) + "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String  descrStr = "";
		try {
			descrStr = SaleObject.findById(Globals.viewingObject).getDescr();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scrollMain.setLayout(new GridLayout());
		var comments = Comments.getByObjectId(Globals.viewingObject);
		for(int i=0;i<comments.length;i++) {
			var c = new JLabel(comments[i].toString());
			GridBagConstraints cons = new GridBagConstraints();
			cons.gridx = 0;
			cons.gridy = i* 20;
			
			l.setConstraints(c, cons);
			scrollMain.add(c);
		}
		// TODO Auto-generated method stub
		mainComponent.add(title, BorderLayout.NORTH);
		if(descrStr != null) { 
			descr.setText(descrStr);
			mainComponent.add(descr);
		}
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					//Main.Main.mainWindow.clear();
					Main.mainWindow.goToView("/main");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mainComponent.add(backButton, BorderLayout.EAST);
		mainComponent.add(scroll, BorderLayout.SOUTH);
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
