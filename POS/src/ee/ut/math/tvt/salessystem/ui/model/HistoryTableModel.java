package ee.ut.math.tvt.salessystem.ui.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;

public class HistoryTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(PurchaseInfoTableModel.class);
    protected List<List<SoldItem>> orders;
    protected List<String[]> rows;
    protected final String[] headers;
	
	public HistoryTableModel() {
		headers = new String[] { "Date", "Time", "Total price" };
		rows = new ArrayList<String[]>();
		orders = new ArrayList<List<SoldItem>>();
	}

    public void addItem(final List<SoldItem> item) {
    	String date = (new SimpleDateFormat("dd.MM.yyyy")).format(new Date());
    	String time = (new SimpleDateFormat("HH:mm:ss")).format(new Date());
        rows.add(new String[] { date, time, "1" });
        orders.add(item);
        fireTableDataChanged();
    }

	public List<SoldItem> getOrders(int selectedRow) {
		return orders.get(selectedRow);
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

}
