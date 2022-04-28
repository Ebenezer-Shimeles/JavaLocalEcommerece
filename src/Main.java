import java.sql.*;

import Models.User;
public class Main {
    public static void main(String args[]) {
    	try {
    		var user = User.findByPk("5");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
}
