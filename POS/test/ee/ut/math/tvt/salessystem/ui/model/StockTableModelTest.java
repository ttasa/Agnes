package ee.ut.math.tvt.salessystem.ui.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class StockTableModelTest {

	StockTableModel stockTableModel;
	
	@Before
	public void setUp() {
		StockItem stockItem1 = new StockItem((long) 1, "Lauaviin", "", 3.5, 100);
		StockItem stockItem2 = new StockItem((long) 2, "Hapukurk", "", 1.5, 10);
		
		stockTableModel = new StockTableModel();
		stockTableModel.addItem(stockItem1);
		stockTableModel.addItem(stockItem1);
		stockTableModel.addItem(stockItem2);
	}
	
	@Test
	public void testValidateNameUniqueness() {
		String[] productNames = stockTableModel.getProductNames();
		boolean areNamesUnique = true;
		
		outerLoop:
		for (int i=0; i<productNames.length-1; i++) {
			for (int j=i+1; j<productNames.length; j++) {
				if (productNames[i].equals(productNames[j])) {
					areNamesUnique = false;
					break outerLoop;
				}
			}
		}
		
		assertTrue(areNamesUnique);
	}
	
	@Test
	public void testHasEnoughInStock() {
		List<StockItem> stockItems = stockTableModel.getTableRows();
		boolean hasEnoughInStock = true;
		
		for (StockItem stockItem: stockItems) {
			if (stockItem.getQuantity() <= 0) {
				hasEnoughInStock = false;
				break;
			}
		}

		assertTrue(hasEnoughInStock);
	}
	
	@Test
	public void testGetItemByIdWhenItemExists() {
		StockItem stockItem = stockTableModel.getItemById(1);
		
		assertNotNull(stockItem);
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testGetItemByIdWhenThrowsException() {
		StockItem stockItem = stockTableModel.getItemById(1000);
		
		assertNull(stockItem);
	}

}
