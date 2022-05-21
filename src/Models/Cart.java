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
			query("delete from  cart where   obj_id = "+obj.getId()+" AND owner_id = "+this.userId+" ");
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
		try {	 
			ResultSet x = query("select * from  cart where  owner_id = "+this.userId);
			int len=0;
			while(x.next()) len++;
			 SaleObject[] objects= new SaleObject[len];
			 x = query("select * from  cart where  owner_id = "+this.userId);

			for(int j=0; x.next(); j++) {
				objects[j] = SaleObject.findById(String.valueOf( x.getInt("obj_id")));
			}
			System.out.println("Len: " + len);
			return objects;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
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
