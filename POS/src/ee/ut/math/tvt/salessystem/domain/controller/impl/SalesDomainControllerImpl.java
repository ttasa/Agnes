package ee.ut.math.tvt.salessystem.domain.controller.impl;

import java.util.ArrayList;
import java.util.List;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.Sale;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.service.HibernateDataService;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;



/**
 * Implementation of the sales domain controller.
 */
public class SalesDomainControllerImpl implements SalesDomainController {
	
	private static HibernateDataService service = new HibernateDataService() ;
	
	public void submitCurrentPurchase(Sale sale) throws VerificationFailedException {
		List<Object> insertableObjects = new ArrayList<Object>();
		insertableObjects.add(sale);
		insertableObjects.addAll(sale.getSoldItems());
		
		service.insert(insertableObjects);
	}

	public void cancelCurrentPurchase() throws VerificationFailedException {
		// XXX - Cancel current purchase
	}
	
	public void startNewAdd() throws VerificationFailedException{
		// XXX - Start adding items to warehouse
	}
	
	public void confirmItemAdd(StockItem item) throws VerificationFailedException{
		List<Object> insertableObject = new ArrayList<Object>();
		insertableObject.add(item);
		
		service.insert(insertableObject);
	}

	public void cancelItemAdd() throws VerificationFailedException{
		// XXX - Cancel the item adding
	}
	
	public void startNewPurchase() throws VerificationFailedException {
		// XXX - Start new purchase
	}

	public List<StockItem> loadWarehouseState() {	
		List<StockItem> dataset = service.getStockItems();
		return dataset;
	}

		
	
	public void endSession() {
		HibernateUtil.closeSession();
	}
	
}
