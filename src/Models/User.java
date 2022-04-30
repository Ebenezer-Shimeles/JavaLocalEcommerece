package Models;

import java.sql.*;

public class User extends Model {
	private int id;
	private String name;
	private String lastName;
	private char gender;
	private String password;
	private int age;
	private String email;
	
	
	public void setEmail(String email) {this.email = email;}
	public String getEmail() {return this.email;}
	
	public void setId(int id) {this.id = id;}
	public int getId() {return this.id;}
	
	public String getName() {return this.name;}
	public void setName(String name) {this.name = name;}
	
	public String getLastName() {return this.lastName;}
	public void setLastName(String lastName) {this.lastName = lastName;}
	
	public char getGender() {return this.gender;}
	public void setGender(char gender) {this.gender = gender;}
	
	public String getPassword() {return this.password;}
	public void setPassword(String newPassword) {this.password = newPassword;} 
	
	public void setAge(int age) {this.age = age;}
	public int getAge() {return this.age;}
	
	public void print() {
	   System.out.println(
			   id + " " + name + " " +lastName+ " " + gender+ " " + password + " " + age + " ");
	}
	public static User findByEmail(String email) throws SQLException{
		ResultSet result = query("select top 1 * from users where email = '"+ email+ "'");
    	if(!result.next()) return null;
    	//System.out.println("Results Number: " + getCount(result));
    	var user = new User(
    			result.getString("name"),
    			result.getString("last_name"),
    			result.getString("email"),
    			result.getString("gender").charAt(0),
    			result.getString("password"),
    			result.getInt("age"),
    			result.getInt("id")
    	);
    	System.out.print("Found user     ");
    	user.print();
    	return user;
	}
    public static User findByPk(String primaryKey) throws SQLException{
    	ResultSet result = query("select top 1 * from users where id = "+ primaryKey);
    	if(!result.next()) return null;
    	//System.out.println("Results Number: " + getCount(result));
    	var user = new User(
    			result.getString("name"),
    			result.getString("last_name"),
    			result.getString("email"),
    			result.getString("gender").charAt(0),
    			result.getString("password"),
    			result.getInt("age"),
    			result.getInt("id")
    	);
    	System.out.print("Found user     ");
    	user.print();
    	return user;
    }
    public boolean checkPassword(String password) {
    	try {
    		refresh();
    		return password.equals(this.password);
    	}
    	catch(Exception e) {
    		System.out.println("Error whil checking password");
    		return false;
    	}
    }
    public boolean refresh() {
    	try {
    		var user = findByPk("" + this.id);
    		this.name = user.name;
    		this.age = user.age;
    		this.gender = user.gender;
    		this.password = user.password;
    		this.id= user.id;
    		this.lastName = user.lastName;
    		return true;
    	}
    	catch(Exception e) {
    		System.out.println("Could not refresh the user :(");
    		return false;
    	}
    }
    public boolean update() {
    	try {
    		query("update users set name = '"+name+"' last_name = '"+lastName+"' "
    				+ "gender = '"+gender+"' password = '"+password+"' "
    				+ " age = "+age+"id = "+ id);
    		return true;
    	}
    	catch(Exception e) {
    		System.out.println("Cannot update the user!");
    		return false;
    	}
    }
    public boolean create() {
    	try {
    	    if(this.exists()) {
    		    System.out.println("Error the user you requested exists");
    		    return false;
    	    }
    	    query("Insert into users(name, email,last_name, gender, password, age) "
    	    		+ "values('"+name+"','"+email+"' ,'"+lastName+"', '"+gender+"', '"+password+"', "+age+" ) ");
    	    return true;
    	}
    	catch(Exception e) {
    		System.out.println("Error something went wrong when creating the user");
    		e.printStackTrace();
    		return false;
    	}
    }
    public boolean exists(){
    	//return 
    	try {
    		return findByEmail(this.email) == null? false: true;
    	}
     	catch(Exception e) {
     		System.out.println("Something went wrong when checking if the user exists");
     		return false;
     	}
     	finally {
     		System.out.println("Exists called");
     	}
    }
    public boolean delete(){
    	try {
    	    query("delete from users where id = " + this.id);
     	    return true;
    	}
     	catch(Exception e) {
     		System.out.println("Something went wrong when deleting the user");
     		return false;
     	}
     	finally {
     		System.out.println("Delete called");
     	}
    }
    public User(
    		String n,
    		String ln,
    		String e,
    		char g,
    		String p,
    		int a,
    		int i
    		
    ){
    	email = e;
    	id = i;   name =n; lastName = ln; gender=g; password = p; age =a;
    }
}
