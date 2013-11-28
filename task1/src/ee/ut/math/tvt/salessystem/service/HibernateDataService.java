package ee.ut.math.tvt.salessystem.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ee.ut.math.tvt.Agnes.Intro;
import ee.ut.math.tvt.salessystem.domain.data.Sale;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

@SuppressWarnings("unchecked")
public class HibernateDataService {

	private Session session = HibernateUtil.currentSession();
	private static final Logger log = Logger.getLogger(Intro.class);

	public List<Sale> getSales() {
		List<Sale> result = session.createQuery("from Sale").list();
		return result;
	}

	public List<StockItem> getStockItems() {
		List<StockItem> result = session.createQuery("from StockItem").list();
		return result;
	}

	public List<SoldItem> getSoldItems() {
		List<SoldItem> result = session.createQuery("from SoldItem").list();
		return result;
	}
	
	public void insert(List<Object> objects) {
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			for (Object object : objects)
				session.save(object);
			transaction.commit();
		}
		catch (Exception e) {
			if (transaction != null) 
				transaction.rollback();
			log.error(e);
		}
		session.flush();
	}

}
