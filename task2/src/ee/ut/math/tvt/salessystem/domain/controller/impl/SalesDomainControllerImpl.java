package ee.ut.math.tvt.salessystem.domain.controller.impl;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.Client;
import ee.ut.math.tvt.salessystem.domain.data.Sale;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Implementation of the sales domain controller.
 */
public class SalesDomainControllerImpl implements SalesDomainController {

	private static final Logger log = Logger
			.getLogger(SalesDomainControllerImpl.class);

	private Session session = HibernateUtil.currentSession();

	@SuppressWarnings("unchecked")
	public List<StockItem> getAllStockItems() {
		List<StockItem> result = session.createQuery("from StockItem").list();

		log.info(result.size() + " items loaded from disk");

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Sale> getAllSales() {
		List<Sale> result = session.createQuery("from Sale").list();
		log.info(result.size() + " Sales loaded from disk");

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Client> getAllClients() {
		List<Client> clients = session.createQuery("from Client").list();

		log.info(clients.size() + " clients loaded from disk");

		return clients;
	}

	public Client getClient(long id) {
		return (Client) session.get(Client.class, id);
	}

	public StockItem getStockItem(long id) {
		return (StockItem) session.get(StockItem.class, id);
	}

	public int getNrOfStockItemsByName(String name) {
		return session
				.createQuery("from StockItem SI WHERE SI.name = '" + name + "'")
				.list().size();
	}

	public void createStockItem(StockItem stockItem) {
		Transaction tx = session.beginTransaction();
		session.save(stockItem);
		tx.commit();
		log.info("Added new stockItem : " + stockItem);
	}

	public void registerSale(Sale sale) {
		sale.setSellingTime();
		Transaction tx = session.beginTransaction();

		for (SoldItem soldItem : sale.getSoldItems()) {
			StockItem stockItem = getStockItem(soldItem.getStockItem().getId());
			stockItem.setQuantity(stockItem.getQuantity()
					- soldItem.getQuantity());
			session.save(stockItem);
		}
		session.save(sale);

		tx.commit();
		log.info("Sale registered");
	}

	@Override
	public void endSession() {
		HibernateUtil.closeSession();
	}

}
