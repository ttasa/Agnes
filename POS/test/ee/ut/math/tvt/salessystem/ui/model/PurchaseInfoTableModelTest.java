package ee.ut.math.tvt.salessystem.ui.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class PurchaseInfoTableModelTest {

	SoldItem soldItem1;
	SoldItem soldItem2;
	
	@Before
	public void setUp() {
		soldItem1 = new SoldItem(
				new StockItem((long) 1, "Lauaviin", "", 3.5, 100), 2);
		soldItem2 = new SoldItem(
				new StockItem((long) 2, "Hapukurk", "", 1.5, 10), 1);
	}
	
	@Test
	public void testItemExistence() {
		PurchaseInfoTableModel purchaseInfoTableModel = new PurchaseInfoTableModel();
		
		assertFalse(purchaseInfoTableModel.doesItemExist("Lauaviin"));
		
		purchaseInfoTableModel.addItem(soldItem1);
		assertTrue(purchaseInfoTableModel.doesItemExist("Lauaviin"));
	}
	
	@Test
	public void testModelItemNumbers() {
		PurchaseInfoTableModel purchaseInfoTableModel = new PurchaseInfoTableModel();
		purchaseInfoTableModel.addItem(soldItem1);
		purchaseInfoTableModel.addItem(soldItem2);
		
		assertEquals(2, purchaseInfoTableModel.getRowCount());
	}

	@Test
	public void testModelItemNumbersInCaseOfSameItems() {
		PurchaseInfoTableModel purchaseInfoTableModel = new PurchaseInfoTableModel();
		purchaseInfoTableModel.addItem(soldItem1);
		purchaseInfoTableModel.addItem(soldItem1);
		
		assertEquals(1, purchaseInfoTableModel.getRowCount());
	}
	
	@Test
	public void testModelClearedness() {
		PurchaseInfoTableModel purchaseInfoTableModel = new PurchaseInfoTableModel();
		purchaseInfoTableModel.addItem(soldItem1);
		purchaseInfoTableModel.addItem(soldItem2);
		purchaseInfoTableModel.clear();
		
		assertEquals(purchaseInfoTableModel.getRowCount(), 0);
	}
	
}
