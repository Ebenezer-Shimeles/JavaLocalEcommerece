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
    
    public boolean addCommment(User user, String msg) {
    	return false;
    }
    public boolean isBought() {
    	refresh();
    	if(quantity >0 ) return false;
    	else return true;
    }

    String getId() { return id;}
    String getOwnerId() { return ownerId;}
    String getName() { return name;}
    String getBrand() { return brand;}
    String getDescr() { return descr;}


    SaleObject(String i, String o, String n, String b, int q, String d, double m){
        id= i;
        ownerId= o;
        name = n;
        brand = b;
        quantity= q;
        descr = d;
        money = m;
    }
    static SaleObject findById(String id) throws SQLException {
        var result = query("select top 1 * from objects where id = " + id);
        if(!result.next()) return null;
        else return new SaleObject(
                result.getString("id"),
                result.getString("owner"),
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


    public SaleObject[] search(String kw) {
        return new SaleObject[10];
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
            query("INSERT into objects(owner_id,name,quantity, descr, price )"
                    + "values('"+ownerId+"', '"+name+"', '"+quantity+"', '"+descr+"', "+money+" )");
            return true;
        }catch(SQLException e) {
            System.out.print("Unable to register object");
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
            System.out.print("Unable to delete object");
            return false;
        }

    }

    @Override
    public boolean update() {
        try {
            query("update objects set owner_id = '"+ownerId+"' name = '"+name+"'  quantity = '"+quantity+"' descr = '"+descr+"' price = "+money+" ");
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
