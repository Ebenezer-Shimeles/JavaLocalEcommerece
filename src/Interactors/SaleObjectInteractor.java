package Interactors;

import Models.SaleObject;

import java.sql.SQLException;
import Models.Transaction;
import Models.User;
import Main.Globals;

public class SaleObjectInteractor {
	public static void buyObject(String objId, String sellerId, String buyerId) throws SQLException {
		var obj = SaleObject.findById(objId);
		if(obj.getQuantity() <=0 ) {
			Main.Main.mainWindow.showMessage("Object is sold out!");
			return;
		}
		obj.setQuantity(obj.getQuantity() - 1);
		obj.update();
	    var t = new Transaction();
	    t.setAmmount(obj.getMoney());
	    t.setBuyer(User.findByPk(buyerId));
	    t.setSellerId(sellerId);
	    
	    t.create();
	}
    public static void addSaleObject(
    		String ownerId,
    		String name,
    		String brand,
    		int quantity,
    		String descr,
    		double money
    
    		) {
    	
          var so = new SaleObject();
          so.setBrand(brand);
          so.setDescr(descr);
          so.setMoney(money);
          so.setName(name);
          so.setOwnerId(ownerId);
          so.setQuantity(quantity);
          so.create();
    }
}
