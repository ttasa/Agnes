package ee.ut.math.tvt.salessystem.ui.model;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.controller.SaleController;

/**
 * Main model. Holds all the other models.
 */
public class SalesSystemModel {

	private StockTableModel warehouseTableModel;
	private PurchaseInfoTableModel currentPurchaseTableModel;
	private PurchaseHistoryTableModel purchaseHistoryTableModel;
	private ClientTableModel clientTableModel;

	private SalesDomainController domainController;
	private SaleController saleController;

	/**
	 * Construct application model.
	 * 
	 * @param domainController
	 *            Sales domain controller.
	 */
	public SalesSystemModel(SalesDomainController domainController) {
		this.domainController = domainController;
		saleController = new SaleController(domainController);

		warehouseTableModel = new StockTableModel();
		currentPurchaseTableModel = new PurchaseInfoTableModel(saleController);
		purchaseHistoryTableModel = new PurchaseHistoryTableModel();
		clientTableModel = new ClientTableModel();

		// Load data from the database
		warehouseTableModel.setStockItems(domainController.getAllStockItems());
		purchaseHistoryTableModel.setSales(domainController.getAllSales());
		clientTableModel.setClients(domainController.getAllClients());
	}

	public SaleController getSaleController() {
		return saleController;
	}

	public SalesDomainController getDomainController() {
		return domainController;
	}

	public StockTableModel getWarehouseTableModel() {
		return warehouseTableModel;
	}

	public PurchaseInfoTableModel getCurrentPurchaseTableModel() {
		return currentPurchaseTableModel;
	}

	public PurchaseHistoryTableModel getPurchaseHistoryTableModel() {
		return purchaseHistoryTableModel;
	}

	public ClientTableModel getClientTableModel() {
		return clientTableModel;
	}

	public void setPurchaseHistoryTableModel(
			PurchaseHistoryTableModel purchaseHistoryTableModel) {
		this.purchaseHistoryTableModel = purchaseHistoryTableModel;
	}

}
