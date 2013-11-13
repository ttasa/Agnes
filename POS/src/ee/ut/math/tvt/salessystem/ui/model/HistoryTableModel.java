package ee.ut.math.tvt.salessystem.ui.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.Sale;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.service.HibernateDataService;

public class HistoryTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger
			.getLogger(PurchaseInfoTableModel.class);
	protected List<Sale> sales;
	protected List<String[]> rows;
	protected final String[] headers;
	private static HibernateDataService service = new HibernateDataService();

	public HistoryTableModel() {
		headers = new String[] { "Date", "Time", "Total price" };
		rows = new ArrayList<String[]>();
		sales = new ArrayList<Sale>();

	}

	public void addItem(final Sale sale) {
		String date = (new SimpleDateFormat("dd.MM.yyyy")).format(sale
				.getDate());
		String time = (new SimpleDateFormat("HH:mm:ss")).format(sale.getDate());
		rows.add(new String[] { date, time, getSoldItemsSum(sale) });
		sales.add(sale);
		fireTableDataChanged();
	}

	public List<SoldItem> getSaleItems(int selectedRow) {
		return sales.get(selectedRow).getSoldItems();

	}

	public void loadHistoryTableModelState() {
		List<Sale> dataset = service.getSales();
		sales.clear();
		rows.clear();
		for (Sale i : dataset) {
			addItem(i);
		}

	}

	@Override
	public int getRowCount() {
		return rows.size();
	}

	@Override
	public int getColumnCount() {
		return headers.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return (rows.get(rowIndex)[columnIndex]);
	}

	@Override
	public String getColumnName(final int columnIndex) {
		return headers[columnIndex];
	}

	private String getSoldItemsSum(Sale sale) {
		double sum = 0;

		for (SoldItem item : sale.getSoldItems()) {
			sum += item.getSum();
		}

		return "" + sum;
	}

}
