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
	private double balance;
	
	public double getAvgSpend() {
		try {
			var rs = query("select avg(transactions.ammount) as avg from users left join transactions on transactions.buyer_id = users.id or transactions.seller_id = users.id group by users.id having users.id = " + this.id );
		   if(!rs.next()) return 0;
		   return rs.getDouble("avg");
		   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
				
	}
	public double getSumSpend() {
		try {
			var rs = query("select sum(transactions.ammount) as sum from users left join transactions on transactions.buyer_id = users.id or transactions.seller_id = users.id group by users.id having users.id = " + this.id);
			   if(!rs.next()) return 0;
			return rs.getDouble("sum");
		   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
			
	}
	
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
	
	public void setBalance(double b) {this.balance = b;}
	public double getBalance() {return this.balance;}
	
	public void print() {
	   System.out.println(
			   id + " " + name + " " +lastName+ " " + gender+ " " + password + " " + age + " ");
	}
	public static void buyObject(String objId, String sellerId, String buyerId) throws SQLException{
		query("exec buy @buyer_id = "+buyerId+" , @object_id="+objId+" ");
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
    	user.setBalance(result.getDouble("balance"));
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
    	user.setBalance(result.getDouble("balance"));
    	System.out.print("Found user     ");
    	user.print();
    	return user;
    }
    public static void checkPassword(String email, String password) throws Exception{

        System.out.println("CHecking Password");
		query("exec [login_user] @email='"+
				          email +
				"', @password='"+password+"'");
//    	try {
//    		refresh();
//    		return password.equals(this.password);
//    	}
//    	catch(Exception e) {
//    		System.out.println("Error whil checking password");
//    		return false;
//    	}
    }
    public boolean refresh() {
    	try {
    		var user = findByEmail("" + this.email);
    		if(user == null) {
    			System.err.println("User is not found while refreshing!");
    			return false;
    		}
    		this.name = user.getName();
    		this.age = user.getAge();
    		this.gender = user.getGender();
    		this.password = user.getPassword();
    		this.id= user.getId();
    		this.lastName = user.getLastName();
    		this.balance = user.getBalance();
    		return true;
    	}
    	catch(Exception e) {
    		System.out.println("Could not refresh the user :(");
    		e.printStackTrace();
    		return false;
    	}
    }
    public boolean update() {
    	try {
    		query("update users set name = '"+name+"', last_name = '"+lastName+"' ,"
    				+ "gender = '"+gender+"', password = '"+password+"' , balance = " + balance
    				+ ", age = "+age+ " where id = "+ id);
    		return true;
    	}
    	catch(Exception e) {
    		System.out.println("Cannot update the user!");
    		return false;
    	}
    }
    public boolean create() throws  SQLException {

    	    if(this.exists()) {
    		    System.out.println("Error the user you requested exists");
    		    return false;
    	    }
    	    query( "exec [register_user] "+
			                "@name = '" + name + "' ," +
					        "@lastName= '" + lastName + "' ," +
					        "@gender='" + gender + "' ," +
					        "@email='"  + email + "' ," +
					         "@password='" + password+"' ,"+
					        "@age=" + age

			);
    	    return true;

    }
    public void deposit(double m) throws SQLException{
    	refresh();
    
        query("exec deposit @amm = "+m+", @userId = "+this.getId()+"");
    }
    public boolean canBuy(double m) {
    	refresh();
    	return balance >= m;
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
