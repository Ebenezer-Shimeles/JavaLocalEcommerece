package Models;

import java.sql.*;
public class Transaction extends Model{
    /*
     create table transactions(id int primary key identity, buyer_id int foreign key references users(id) not null,
                          seller_id int foreign key references users(id) not null);

     */
	
	private String id;
	private String buyerId;
	private String sellerId;
	private double ammount;
	
	public static Transaction[] findByUserId(String userId) {
		try {
			ResultSet r = query("select * from transactions where (buyer_id = "+userId+") or (seller_id = "+userId+") ");
			int len = 0;
			while(r.next()) len++;
			
			Transaction t[] = new Transaction[len];
			 r = query("select * from transactions where (buyer_id = "+userId+") or (seller_id = "+userId+") ");
			 
			 for(int i = 0;r.next();i++) {
				 t[i] = new Transaction();
				 t[i].setAmmount(r.getDouble("ammount"));
				 t[i].setBuyerId(String.valueOf(r.getInt("buyer_id")));
				 t[i].setSellerId(String.valueOf(r.getInt("seller_id")));
			 }
			
			return t;
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void setBuyerId(String userId) {
		this.buyerId = userId;
	}
	public void setSellerId(String userId) {
		this.sellerId = userId;
	}
	
	public User getSeller()  throws Exception{
		return User.findByPk(this.sellerId);
	}
	public User getBuyer() throws Exception{
		return User.findByPk(this.buyerId);
	}
	
	public void setBuyer(User user) {
		this.buyerId = String.valueOf(user.getId());
	}
	public void setSeller(User user) {
		this.sellerId = String.valueOf(user.getId());
	}
	
	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}
	public double getAmmount() {return ammount;}
	
	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean create(){
		try {
			query("insert into transactions(buyer_id, ammount, seller_id) values("+buyerId+", "+ammount+", "+sellerId+") ");
			return true;
		} 
		catch(Exception e) {e.printStackTrace();return false;}
	}

	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean refresh() {
		// TODO Auto-generated method stub
		return false;
	}
    @Override 
    public String toString() {
    	try {
			return "From: " + User.findByPk(this.buyerId).getEmail() + " to: "+ User.findByPk(this.sellerId).getEmail() + " Ammount: " + ammount;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
	
}
