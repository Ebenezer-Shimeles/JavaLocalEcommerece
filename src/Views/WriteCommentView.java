package Views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Models.*;
import Main.*;
import javax.swing.*;

import Main.Main;

public class WriteCommentView extends View{
    private JPanel mainComponent = new JPanel(new BorderLayout());
    private JTextArea commentBox = new JTextArea("Comments ...");
    private JButton goBack = new JButton("Back to home");
    private JButton send = new JButton("Send");
    
	@Override
	public JComponent build() {
		// TODO Auto-generated method stub
		registerComponent(goBack, send, commentBox);
		
		mainComponent.add(commentBox);
		JPanel sub = new JPanel();
		sub.add(goBack, BorderLayout.SOUTH);
		sub.add(send, BorderLayout.EAST);
		mainComponent.add(sub, BorderLayout.SOUTH);
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					goBack.setVisible(false);
					mainComponent.setVisible(false);
					var comment = new Comments();
					comment.setMsg(commentBox.getText());
					comment.setObjectId(Globals.viewingObject);
					comment.setWriterId(Globals.userId);
					comment.create();
					//Main.Main.mainWindow.clear();
					Main.mainWindow.goToView("/main");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					goBack.setVisible(false);
					mainComponent.setVisible(false);
					
					//Main.Main.mainWindow.clear();
					Main.mainWindow.goToView("/main");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
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
