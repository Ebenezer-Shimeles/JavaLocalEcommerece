package Models;

import java.sql.*;

public class SaleObject extends Model {
    private String id = null;
    private String ownerId = null;
    private String name = null;
    private String brand = null;
    private int quantity;
    private String descr;
    private double money;
   
    public static SaleObject[] of(String userId) {
    	try {
			var rs = query("select * from objects where owner_id = " + userId);
			int len=0;
			while(rs.next()) len++;
			SaleObject[] objects = new SaleObject[len];
			rs = query("select * from objects where owner_id = " + userId);
		    for(int i=0;rs.next();i++) {
		    	objects[i] = new SaleObject();
		    	objects[i].setBrand(rs.getString("brand"));
		    	objects[i].setDescr(rs.getString("descr"));
		    	objects[i].setId(String.valueOf(rs.getInt("id")));
		    	objects[i].setMoney(rs.getDouble("price"));
		    	objects[i].setName(rs.getString("name"));
		    	objects[i].setOwnerId(String.valueOf(rs.getInt("owner_id")));
		    	objects[i].setQuantity(rs.getInt("quantity"));
		    }
		    return objects;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
    public static SaleObject[] all() {
    	try {
			var rs = query("select * from objects where quantity > 0");
			int len=0;
			while(rs.next()) len++;
			SaleObject[] objects = new SaleObject[len];
			rs = query("select * from objects where quantity > 0");
		    for(int i=0;rs.next();i++) {
		    	objects[i] = new SaleObject();
		    	objects[i].setBrand(rs.getString("brand"));
		    	objects[i].setDescr(rs.getString("descr"));
		    	objects[i].setId(String.valueOf(rs.getInt("id")));
		    	objects[i].setMoney(rs.getDouble("price"));
		    	objects[i].setName(rs.getString("name"));
		    	objects[i].setOwnerId(String.valueOf(rs.getInt("owner_id")));
		    	objects[i].setQuantity(rs.getInt("quantity"));
		    }
		    return objects;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    	
    }
    public String toString() {
    	return this.brand+ "=> " + this.name + " @" + this.money;
    }
    public boolean addCommment(User user, String msg) {
    	return false;
    }
    public boolean isBought() {
    	refresh();
    	if(quantity >0 ) return false;
    	else return true;
    }

    public String getId() { return id;}
    public String getOwnerId() { return ownerId;}
    public String getName() { return name;}
    public String getBrand() { return brand;}
    public String getDescr() { return descr;}
    public double getMoney() { return money;}
    public int getQuantity() {return quantity;}
    
    public void setId(String i) { id=i;}
    public void setOwnerId(String oId) { ownerId=oId;}
    public void setName(String n) { name=n;}
    public void setBrand(String b) { brand=b;}
    public void setDescr(String d) {  descr=d;}
    public void setMoney(double d) { money=d;}
    public void setQuantity(int q) { quantity=q;}
    
    public SaleObject() {}

    public SaleObject(String i, String o, String n, String b, int q, String d, double m){
        id= i;
        ownerId= o;
        name = n;
        brand = b;
        quantity= q;
        descr = d;
        money = m;
    }
    public static SaleObject findById(String id) throws SQLException {
        var result = query("select top 1 * from objects where id = " + id);
        if(!result.next()) return null;
        else return new SaleObject(
                String.valueOf(result.getInt("id")),
                String.valueOf(result.getInt("owner_id")),
                result.getString("name"),
                result.getString("brand"),
                result.getInt("quantity"),
                result.getString("descr"),
                result.getDouble("price")
        );
    }

    /**
     * create table objects(id int primary key identity, owner_id int foreign key references users(id) not null,
     name varchar(20) not null, brand varchar(20), quantity int not null default 1, descr varchar(MAX) ,
     price money not null
     )
     * @throws Exception

     */


    public static SaleObject[] search(String kw) {
    	try {
			var rs = query("select * from objects where quantity > 0 and  "
					+ "(name LIKE '%"+kw+"%' or brand like '%"+kw+"%' or descr LIKE '%"+kw+"%' )");
			int len=0;
			while(rs.next()) len++;
			SaleObject[] objects = new SaleObject[len];
			rs = query("select * from objects where quantity > 0 and  "
					+ "(name LIKE '%"+kw+"%' or brand like '%"+kw+"%' or descr LIKE '%"+kw+"%' )");
		    for(int i=0;rs.next();i++) {
		    	objects[i] = new SaleObject();
		    	objects[i].setBrand(rs.getString("brand"));
		    	objects[i].setDescr(rs.getString("descr"));
		    	objects[i].setId(String.valueOf(rs.getInt("id")));
		    	objects[i].setMoney(rs.getDouble("price"));
		    	objects[i].setName(rs.getString("name"));
		    	objects[i].setOwnerId(String.valueOf(rs.getInt("owner_id")));
		    	objects[i].setQuantity(rs.getInt("quantity"));
		    }
		    return objects;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    @Override
    public boolean exists(){
        // TODO Auto-generated method stub1
        //if(id == null) throw new Exception("Id is not set");

        return false;
    }

    @Override
    public boolean create() {
        // TODO Auto-generated method stub
        try {
            query("INSERT into objects(owner_id,name,quantity, descr, price, brand )"
                    + "values("+ownerId+", '"+name+"', "+quantity+", '"+descr+"', "+money+" ,'"+brand+"' )");
            return true;
        }catch(SQLException e) {
            System.out.print("Unable to register object");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete() {
        // TODO Auto-generated method stub
        try {
            query("delete from objects where id = " + id);
            return true;
        }catch(SQLException e) {
        	e.printStackTrace();
            System.out.print("Unable to delete object");
            return false;
        }

    }

    @Override
    public boolean update() {
        try {
            query("update objects set owner_id = "+ownerId+" ,name = '"+name+"' , quantity = "+quantity+" ,descr = '"+descr+"' ,price = "+money+" "
            		+ " where id = " + id);
            return true;
        }catch(SQLException e) {
            System.out.print("Unable to update object");
            return false;
        }
    }
    
    

    @Override
    public boolean refresh() {
        try {
            query("select top 1 * from objects where id = " + id);
            var object = findById(id);

            return true;
        }catch(SQLException e) {
        	e.printStackTrace();
            System.out.print("Unable to update object");
            return false;
        }
    }

}
