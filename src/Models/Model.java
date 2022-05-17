package Models;

import java.sql.*;

public abstract class Model {
	 protected static Connection con;
	 public abstract boolean exists();
	 public abstract boolean create();
	 public abstract boolean delete();
	 public abstract boolean update();
	 public abstract boolean refresh();
     static {
    	 try {
    	    	//  Runtime.getRuntime().loadLibrary("C:/Windows/System32/crypt32.dll");
    	          Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    	          //DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
    	          String  url="jdbc:sqlserver://localhost:1434;databaseName=Ecomm;integratedSecurity=true;"
    	       
    	        		+"encrypt=true;trustServerCertificate=true;";
    	          con= DriverManager.getConnection(url);
    	        
    	          System.out.println("Database Connected");
    	      } catch(Exception e) {
    	    	  System.out.println("Cannot connect :((");
    	    	  e.printStackTrace();
    	      }
    	    
     }
     static public ResultSet query(String sqlQuery) throws SQLException{
    	 System.out.println("Executing "+ sqlQuery);
    	 Statement statement = con.createStatement();
    	 if(sqlQuery.startsWith("select")) return statement.executeQuery(sqlQuery);
    	 else {
    		 statement.execute(sqlQuery);
    		 return null;
    	 }
     }  	 
} 
/**/