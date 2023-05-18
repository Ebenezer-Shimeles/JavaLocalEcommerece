package Interactors;

import Models.SaleObject;

import java.sql.SQLException;
import Models.Transaction;
import Models.User;
import Main.Globals;
import Models.Cart;
public class SaleObjectInteractor {
	public static void buyObject(String objId, String sellerId, String buyerId) throws SQLException {
		var user = User.findByPk(Globals.userId);
		var obj = SaleObject.findById(objId);
		if(!user.canBuy(obj.getMoney())) {
			Main.Main.mainWindow.showMessage("It seems you don't have enough balance please recharge your account :(");
			return;
		}
		
		if(obj.getQuantity() <=0 ) {
			Main.Main.mainWindow.showMessage("Object is sold out!");
			return;
		}
		var seller = User.findByPk(sellerId);
		user.deposit(-1 * obj.getMoney());
		seller.deposit(obj.getMoney());
		obj.setQuantity(obj.getQuantity() - 1);
		obj.update();
	    var t = new Transaction();
	    t.setAmmount(obj.getMoney());
	    t.setBuyer(User.findByPk(buyerId));
	    t.setSellerId(sellerId);
	    
	    t.create();
	    Main.Main.mainWindow.showMessage("You have bought it :)");
	}
	public static void addToCart(String objectId, String userId) throws SQLException{
		var obj = SaleObject.findById(objectId);
		if(obj == null) {
			Main.Main.mainWindow.showMessage("Something went wrong!");
			return;
		}
		if(obj.getQuantity() <=0 ) {
			Main.Main.mainWindow.showMessage("Object is sold out!");
			return;
		}
		var cart = new Cart(userId);
		cart.addObject(obj);
		Main.Main.mainWindow.showMessage("Added!");
		
	}
	public static void removeFromCart(String objectId, String userId) throws SQLException {
		var cart = new Cart(userId);
        cart.removeObject(SaleObject.findById(objectId));
	}
    public static void addSaleObject(
    		String ownerId,
    		String name,
    		String brand,
    		int quantity,
    		String descr,
    		double money,
    		int cata
    
    		) throws SQLException{
    	
          var so = new SaleObject();
          so.setCata(cata);
          so.setBrand(brand);
          so.setDescr(descr);
          so.setMoney(money);
          so.setName(name);
          so.setOwnerId(ownerId);
          so.setQuantity(quantity);
		  System.out.println("Adding sale object");
          so.create();

    }
}
