package Models;

import java.sql.ResultSet;
import java.sql.SQLException;

/*create table comments(id int primary key identity, msg varchar(200) not null, from_user int foreign key references users(id), 
                      forobject int foreign key references objects(id)); */
public class Comments extends Model{
    private String msg;
    private String writerId;
    private String objectId;
    
    
    public void setMsg(String m) {
    	msg = m;
    }
    public void setWriterId(String w) {
    	writerId = w;
    }
    public void setObjectId(String o) {
    	objectId = o;
    }
    @Override public String toString() {
    	try {
			return "From: "+ User.findByPk(this.writerId).getEmail() + " " + this.msg;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
   public static Comments[] getByObjectId(String objectId) {
		try {
			ResultSet r = query( "select * from dbo.get_comments("+objectId+")");
			int len = 0;
			while(r.next()) len++;
			
			Comments t[] = new Comments[len];
			 r = query("select * from dbo.get_comments("+objectId+")");
			 for(int i = 0;r.next();i++) {
				 t[i] = new Comments();
				 
				 t[i].setMsg(r.getString("msg"));
				 t[i].setWriterId(String.valueOf(r.getInt("from_user")));
				 t[i].setObjectId(String.valueOf(r.getInt("forobject")));
				 
//				 t[i].setAmmount(r.getDouble("ammount"));
//				 t[i].setBuyerId(String.valueOf(r.getInt("buyer_id")));
//				 t[i].setSellerId(String.valueOf(r.getInt("seller_id")));
			 }
			
			return t;
			
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
		try {
			query("exec [write_comment]@msg='"+this.msg+"', @write="+this.writerId+", @object="+this.objectId+"");
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
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
