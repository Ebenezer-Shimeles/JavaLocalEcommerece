package Models;

import java.sql.*;
public class Cart extends Model{

	private String userId;

   /*
    * create table cart(id int primary key identity, obj_id int foreign key references objects(id) not null, 
                  owner_id int foreign key references users(id) not null);*/
	public Cart(String uId) {this.userId = uId;}
	
//	public static Cart findByUserId(String userId) {
//		return null;
//	}
	public boolean addObject(SaleObject obj) {
		try {
			
		 
			query("insert into cart(obj_id, owner_id) values('"+obj.getId()+"' ,'"+this.userId+"' )");
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	public boolean removeObject(SaleObject obj) {
		try {	 
			query("delete from  cart where   obj_id = '"+obj.getId()+"' AND owner_id = '"+this.userId+"' )");
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		//return false;
	}
	public User getUser() throws SQLException{
		return User.findByPk(this.userId);
	}
	public SaleObject[] getSaleObjects() {
		return null;
	}
	
	
	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean create() {
		// TODO Auto-generated method stub
		return false;
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
    
	
}
