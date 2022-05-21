package Views;
import javax.swing.*;
import java.awt.*;
public class CataImage{
	//String img;
	public static String[] map = {
			"a.jpg", //shoes
			"download-_6_.jpg", //makeup
		"download-_7_.jpg", //elec
		"download-_8_.jpg", //fur
	
		"download-_1_.jpg",	 //custom
		
		"download-_9_.jpg" //other
	};
	

    public ImageIcon build(int c) {
    	String img = map[c];
    	 ImageIcon icon1 = new ImageIcon(getClass().getClassLoader().getResource(img));
    	 return  new ImageIcon(
    		      icon1.getImage().getScaledInstance(200, 200, 0)
    	 );
    }
}
