package ee.ut.math.tvt.salessystem.domain.controller.impl;

import java.util.ArrayList;
import java.util.List;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.service.HibernateDataService;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;



/**
 * Implementation of the sales domain controller.
 */
public class SalesDomainControllerImpl implements SalesDomainController {
	
	private static HibernateDataService service = new HibernateDataService() ;
	
	public void submitCurrentPurchase(List<SoldItem> goods) throws VerificationFailedException {
		// Let's assume we have checked and found out that the buyer is underaged and
		// cannot buy chupa-chups
		//throw new VerificationFailedException("Underaged!");
		// XXX - Save purchase
	}

	public void cancelCurrentPurchase() throws VerificationFailedException {
		// XXX - Cancel current purchase
	}
	
	public void startNewAdd() throws VerificationFailedException{
		// XXX - Start adding items to warehouse
	}
	
	public void confirmItemAdd() throws VerificationFailedException{
		// XXX - Add item to the warehouse
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
