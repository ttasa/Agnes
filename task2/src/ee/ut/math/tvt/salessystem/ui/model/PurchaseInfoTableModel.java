package ee.ut.math.tvt.salessystem.ui.model;

import ee.ut.math.tvt.salessystem.domain.data.Sale;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.controller.SaleController;
import java.util.List;

/**
 * Purchase history details model.
 */
public class PurchaseInfoTableModel extends SalesSystemTableModel<SoldItem> {
	private static final long serialVersionUID = 1L;

	private SaleController saleController;

	public PurchaseInfoTableModel() {
		super(new String[] { "Id", "Name", "Price", "Quantity", "Sum" });
		saleController = new SaleController();
	}

	public PurchaseInfoTableModel(SaleController saleController) {
		super(new String[] { "Id", "Name", "Price", "Quantity", "Sum" });
		this.saleController = saleController;
	}

	public static PurchaseInfoTableModel getEmptyTable() {
		return new PurchaseInfoTableModel();
	}

	@Override
	protected Object getColumnValue(SoldItem item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getId();
		case 1:
			return item.getName();
		case 2:
			return item.getPrice();
		case 3:
			return item.getQuantity();
		case 4:
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

		for (final SoldItem item : getTableRows()) {
			buffer.append(item.getId() + "\t");
			buffer.append(item.getName() + "\t");
			buffer.append(item.getPrice() + "\t");
			buffer.append(item.getQuantity() + "\t");
			buffer.append(item.getSum() + "\t");
			buffer.append("\n");
		}

		return buffer.toString();
	}

	@Override
	public List<SoldItem> getTableRows() {
		return saleController.getSale().getSoldItems();
	}

	public void showSale(Sale sale) {
		saleController.setSale(sale);
		fireTableDataChanged();
	}

	public void clearSale() {
		saleController.endSale();
		fireTableDataChanged();
	}

}
