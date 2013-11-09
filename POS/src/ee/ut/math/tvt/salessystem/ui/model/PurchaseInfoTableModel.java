package ee.ut.math.tvt.salessystem.ui.model;

import java.util.List;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.SalesSystemUI;

/**
 * Purchase history details model.
 */
public class PurchaseInfoTableModel extends SalesSystemTableModel<SoldItem> {
	private static final long serialVersionUID = 1L;
	private StockItem stockitem;
	private static final Logger log = Logger.getLogger(PurchaseInfoTableModel.class);
	
	public PurchaseInfoTableModel() {
		//Removed ID from that list
		super(new String[] { "Name", "Price", "Quantity","Sum"});
	}

	@Override
	protected Object getColumnValue(SoldItem item, int columnIndex) {
		switch (columnIndex) {
		//case 0:
		//	return item.getId();
		case 0:
			return item.getName();
		case 1:
			return item.getPrice();
		case 2:
			return item.getQuantity();
		case 3:
			return item.getSum();
		}
	
		throw new IllegalArgumentException("Column index out of range");
	}

	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < headers.length; i++)
			buffer.append(headers[i] + "\t");
		buffer.append("\n");

		for (final SoldItem item : rows) {
			buffer.append(item.getId() + "\t");
			buffer.append(item.getName() + "\t");
			buffer.append(item.getPrice() + "\t");
			buffer.append(item.getQuantity() + "\t");
			buffer.append(item.getSum() + "\t");
			buffer.append("\n");
		}

		return buffer.toString();
	}
	
    /**
     * Add new StockItem to table.
     */
    public void addItem(final SoldItem item) {
        
    	if (doesItemExist(item.getName())) {
    		getItemByName(item.getName()).setQuantity(
    				getItemByName(item.getName()).getQuantity() + item.getQuantity());
    	}
    	else {
	        rows.add(item);
    	}
        log.debug("Added " + item.getName() + " quantity of " + item.getQuantity());
        fireTableDataChanged();
    }
    
}
