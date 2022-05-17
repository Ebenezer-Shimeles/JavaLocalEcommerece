package Interactors;

import Models.SaleObject;
import Main.Globals;

public class SaleObjectInteractor {
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
