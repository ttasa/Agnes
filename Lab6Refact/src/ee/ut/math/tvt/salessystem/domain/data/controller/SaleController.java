package ee.ut.math.tvt.salessystem.domain.data.controller;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.Client;
import ee.ut.math.tvt.salessystem.domain.data.Sale;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.SalesSystemException;

public class SaleController {

	private static final Logger log = Logger.getLogger(SaleController.class);

	private SalesDomainController domainController;

	private Sale sale;

	public SaleController() {
		this.sale = new Sale();
	}

	public SaleController(SalesDomainController domainController) {
		this.domainController = domainController;
		this.sale = new Sale(); // Empty sale
	}

	public void addItemToSale(final SoldItem soldItem)
			throws SalesSystemException {

		StockItem stockItem = soldItem.getStockItem();
		long stockItemId = stockItem.getId();
		SoldItem existingItem = getExistingSoldItem(stockItemId);

		if (existingItem != null) {
			int totalQuantity = existingItem.getQuantity()
					+ soldItem.getQuantity();
			validateQuantityInStock(stockItem, totalQuantity);
			existingItem.setQuantity(totalQuantity);

			log.debug("Found existing item " + soldItem.getName()
					+ " increased quantity by " + soldItem.getQuantity());

		} else {
			validateQuantityInStock(soldItem.getStockItem(),
					soldItem.getQuantity());
			sale.addItem(soldItem);
			log.debug("Added " + soldItem.getName() + " quantity of "
					+ soldItem.getQuantity());
		}

	}

	private SoldItem getExistingSoldItem(long stockItemId) {
		for (SoldItem soldItem : sale.getSoldItems()) {
			if (soldItem.getStockItem().getId().equals(stockItemId))
				return soldItem;
		}
		return null;
	}

	private void validateQuantityInStock(StockItem stockItem, int quantity)
			throws SalesSystemException {

		if (!isEnoughInStock(stockItem, quantity)) {
			log.info(" -- not enough in stock!");
			throw new SalesSystemException();
		}

	}

	private boolean isEnoughInStock(StockItem stockItem, int quantity) {
		return (domainController.getStockItem(stockItem.getId()).getQuantity() >= quantity);
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public Sale getSale() {
		return sale;
	}

	public void startNewSale(Client client) {
		sale = new Sale(client);
		log.info("New purchase started");
	}

	public void endSale() {
		sale = new Sale();
	}

}
