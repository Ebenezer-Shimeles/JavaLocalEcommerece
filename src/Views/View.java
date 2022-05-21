package Views;
import javax.swing.*;

import Main.Main;

public abstract class View {
      abstract public JComponent build();
      //abstract public  void clear();
      abstract public void show();
      abstract public JComponent getMainComponent();
      
      protected JComponent[] components = new JComponent[10000];
      protected int componentLen = 0;
      
 	  protected void registerComponent(JComponent ...component) {
//		components[componentLen] = component;
//		componentLen++;
//		component.setVisible(false);
 		 for(JComponent comp: component) {
 			 components[componentLen] = comp;
 			 componentLen++;
 			 comp.setVisible(false);
 		 }
 	  }
 	  public void clear() {
 		  
 		  for(int i=0;i<componentLen;i++) {
 			  
 			  if(components[i] == null) continue;
 			 components[i].setVisible(false); 
 			  //getMainComponent().remove( components[i]);
 			 }
 	  }
 	  public void init() {
 		 for(int i=0;i<componentLen;i++) {
 			 components[i].setVisible(true);
 			 
 		 }
 	  }
 	  public void showMessage(String msg) {
 		 JOptionPane.showMessageDialog(Main.mainWindow, msg);
 	  }
}
