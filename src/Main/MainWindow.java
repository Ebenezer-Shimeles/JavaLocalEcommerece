package Main;

import javax.swing.*;
import Views.View;

public class MainWindow extends JFrame{
	 private View currentView = null;
	 private String[] viewNames = new String[100];
	 private View[] views = new View[100];
	 private int viewLen = 0;
	 private boolean first = true;
	 
	 public void showMessage(String msg) {
		 if(currentView!= null) currentView.showMessage(msg);
	 }
 
	 private int viewIndex(String vn) {
		 for(int i=0;i<viewLen;i++) {
			 System.out.println("Comparing " + viewNames[i]+ " " + vn);
			 if(viewNames[i].equals(vn)) return i;
		 }
		 return -1;
	 }
	 private boolean viewExists(String vn) {
		 if(viewLen == 0) return false;
		 for(String viewName: viewNames) {
			 if(viewName != null && viewName.equals(vn)) {
				 System.out.println("Found page");
				 return true;
			 }
		 }
		 System.out.println("Page not found!: "+vn);
		 return false;
	 }
	 public void registerView(String viewName, View view) {
		if(viewExists(viewName)) {
			System.out.println("Error this view is already registered");
			return;
		}
		view.init();
		viewNames[viewLen] = viewName;
		views[viewLen] = view;
		viewLen++;
		
	 }
	 public void goToView(String viewName) throws Exception {
		 if(!viewExists(viewName)) return;
		
		 if(viewLen > 0 && currentView != null) { 
			 System.out.println("Clearing page");
			 currentView.clear();
			  this.remove(this.currentView.getMainComponent());
		 }
		 int indexOfView = viewIndex(viewName);
		 if(indexOfView == -1) throw new Exception("Page does not exist");
		 
	     this.add( views[indexOfView].build());
		 currentView = views[indexOfView];
		 currentView.show();
		 currentView.init();
		 first = false;
	 }
    public  MainWindow(String title){
    	 super(title);
    	 this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("icon.jpg")).getImage());
     }
}
