package ee.ut.math.tvt.salessystem.domain.data;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class StockItemTest {

	StockItem stockItem;
	
	@Before
	public void setUp() {
		stockItem = new StockItem((long) 1, "Lauaviin", "", 3.5, 100);
	}
	
	@Test
	public void testClone() {
		StockItem clone = (StockItem) stockItem.clone();
		
		assertNotSame(stockItem, clone);
		assertTrue(stockItem.getName().equals(clone.getName()) &&
				stockItem.getPrice() == clone.getPrice() &&
				stockItem.getQuantity() == clone.getQuantity());
	}
	
	@Test
	public void testGetColumn() {
		assertTrue(stockItem.getColumn(1).equals("Lauaviin") &&
				stockItem.getColumn(2).equals(3.5) &&
				stockItem.getColumn(3).equals(100));
	}
	
	@Test(expected=RuntimeException.class)
	public void testGetColumnException() {
		stockItem.getColumn(4);
	}
	
}
