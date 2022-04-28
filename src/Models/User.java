package Models;

import java.sql.*;

public class User extends Model {
	private int id;
	private String name;
	private String lastName;
	private char gender;
	private String password;
	private int age;
	
	public void print() {
	   System.out.println(
			   id + " " + name + " " +lastName+ " " + gender+ " " + password + " " + age + " ");
	}
    public static User findByPk(String primaryKey) throws SQLException{
    	ResultSet result = Model.query("select top 1* from users where id = "+ primaryKey);
    	result.next();
    	var user = new User(
    			result.getString("name"),
    			result.getString("last_name"),
    			result.getString("gender").charAt(0),
    			result.getString("password"),
    			result.getInt("age"),
    			result.getInt("id")
    	);
    	System.out.print("Found user");
    	user.print();
    	return user;
    }
    User(
    		String n,
    		String ln,
    		char g,
    		String p,
    		int a,
    		int i
    		
    ){
    	id = i;   name =n; lastName = ln; gender=g; password = p; age =a;
    }
}
