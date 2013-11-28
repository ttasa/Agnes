package ee.ut.math.tvt.salessystem.domain.controller;

import ee.ut.math.tvt.salessystem.domain.data.Client;
import ee.ut.math.tvt.salessystem.domain.data.Sale;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import java.util.List;

/**
 * Sales domain controller is responsible for the domain specific business
 * processes.
 */
public interface SalesDomainController {

	/**
	 * Load the current state of the warehouse.
	 * 
	 * @return List of ${link
	 *         ee.ut.math.tvt.salessystem.domain.data.StockItem}s.
	 */
	public List<StockItem> getAllStockItems();

	public List<Client> getAllClients();

	public List<Sale> getAllSales();

	public Client getClient(long id);

	public StockItem getStockItem(long id);

	public int getNrOfStockItemsByName(String name);

	public void createStockItem(StockItem stockItem);

	/**
	 * Commit business transaction - purchase of goods.
	 * 
	 * @param goods
	 *            Goods that the buyer has chosen to buy.
	 * @throws VerificationFailedException
	 */

	public void registerSale(Sale sale);

	/**
	 * Close all resources
	 */
	public void endSession();
}
