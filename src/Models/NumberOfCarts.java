package Models;


import java.sql.*;
public class NumberOfCarts extends Model {
	public static int ofObject(SaleObject obj) {
		try {
			ResultSet result = query("select * from objects_carts where id = " + obj.getId());
			
			if(!result.next()) return 0;
			else return result.getInt("add_no");
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		    return 0;
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
