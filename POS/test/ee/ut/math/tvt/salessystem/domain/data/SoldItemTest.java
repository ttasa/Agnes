package ee.ut.math.tvt.salessystem.domain.data;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SoldItemTest {

	StockItem stockItem;
	
	@Before
	public void setUp() {
		stockItem = new StockItem((long) 1, "Lauaviin", "", 3.5, 100);
	}
	
	@Test
	public void testGetSum() {
		SoldItem soldItem = new SoldItem(stockItem, 2);
		
		assertEquals(3.5*2, soldItem.getSum(), 0.0001);
	}
	
	@Test
	public void testGetSumWithZeroQuantity() {
		SoldItem soldItem = new SoldItem(stockItem, 0);
		
		assertEquals(0.0, soldItem.getSum(), 0.0001);
	}
	
}
