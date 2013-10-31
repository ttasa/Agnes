package ee.ut.math.tvt.salessystem.ui.panels;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.tabs.PurchaseTab;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.NoSuchElementException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.apache.log4j.Logger;
/**
 * Warehouse pane + warehouse table UI.
 */
public class WarehousePanel extends JPanel {
	private static final Logger log = Logger.getLogger(PurchaseTab.class);
	private static final long serialVersionUID = 1L;
	// Text field on the dialogPane
	private JTextField barCodeField;
	private JTextField quantityField;
	private JTextField nameField;
	private JTextField priceField;
	private JButton confirmButton;
	// Warehouse model
	private SalesSystemModel model;
	/**
	 * Constructs new warehouse panel.
	 * 
	 * @param model
	 *            composite model of the warehouse and the warehouse table.
	 */
	public WarehousePanel(SalesSystemModel model) {
		this.model = model;
		setLayout(new GridBagLayout());
		add(drawDialogPane(), getDialogPaneConstraints());
		add(drawBasketPane(), getBasketPaneConstraints());
		setEnabled(false);
	}
	// warehouse pane
	private JComponent drawBasketPane() {
		// Create the warehouse table
		JPanel basketPane = new JPanel();
		basketPane.setLayout(new GridBagLayout());
		basketPane.setBorder(BorderFactory
				.createTitledBorder("Warehouse status"));
		// Create the table, put it inside a scollPane,
		// and add the scrollPane to the basketPanel.
		JTable table = new JTable(model.getWarehouseTableModel());
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					final JTable target = (JTable) e.getSource();
					final int row = target.getSelectedRow();
					// get info from clicking on table cells
					final Long barcode = (Long) target.getValueAt(row, 0);
					final String name = (String) target.getValueAt(row, 1);
					final double price = (double) target.getValueAt(row, 2);
					// final int quantity = (int)target.getValueAt(row, 3);
					nameField.setText(name);
					quantityField.setText("");
					priceField.setText(Double.toString(price));
					barCodeField.setText(Long.toString(barcode));
				}
			}
		});
		JScrollPane scrollPane = new JScrollPane(table);
		basketPane.add(scrollPane, getBacketScrollPaneConstraints());
		return basketPane;
	}
	// add item dialog
	private JComponent drawDialogPane() {
		// Create the panel
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2));
		panel.setBorder(BorderFactory.createTitledBorder("Product"));
		barCodeField = new JTextField();
		// listener to automatically get name by id
		barCodeField.getDocument().addDocumentListener(new DocumentListener() {
			public void getItem() {
				try {
					model.getWarehouseTableModel().getItemById(
							Long.parseLong(barCodeField.getText()));
					if (!barCodeField.getText().equals("")) {
						nameField.setEnabled(false);
						nameField.setText(model
								.getWarehouseTableModel()
								.getItemById(
										Long.parseLong(barCodeField.getText()))
								.getName());
					}
					else {
					}
				} catch (Exception n) {
					nameField.setEnabled(true);
				}
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				getItem();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				getItem();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				getItem();
			}
		});
		quantityField = new JTextField("");
		nameField = new JTextField("");
		priceField = new JTextField();
		barCodeField.setEditable(true);
		nameField.setEditable(true);
		priceField.setEditable(true);
		// == Add components to the panel
		// - bar code
		panel.add(new JLabel("Bar code:"));
		panel.add(barCodeField);
		// - amount
		panel.add(new JLabel("Amount:"));
		panel.add(quantityField);
		// - name
		panel.add(new JLabel("Name:"));
		panel.add(nameField);
		// - price
		panel.add(new JLabel("Price:"));
		panel.add(priceField);
		// Create and add the confirm and cancel button
		confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addItemEventHandler();
			}
		});
		nameField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillDialogFields();
			}
		});
		barCodeField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillDialogFields();
				quantityField.setText("");
			}
		});
		panel.add(confirmButton);
		return panel;
	}
	// Fill dialog with data from the "database".
	public void fillDialogFields() {
		StockItem stockItem = getStockItemByName();
		if (stockItem != null) {
			barCodeField.setText(String.valueOf(stockItem.getId()));
			String priceString = String.valueOf(stockItem.getPrice());
			priceField.setText(priceString);
		} else {
			reset();
		}
	}
	private StockItem getStockItemByName() {
		try {
			String name = nameField.getText();
			return model.getWarehouseTableModel().getItemByName(name);
		} catch (NumberFormatException ex) {
			return null;
		} catch (NoSuchElementException ex) {
			return null;
		}
	}
	/**
	 * Add new item to the warehouse.
	 */
	public void addItemEventHandler() {
		long barCode=0;
		double price=0;
		int quantity=0;
		try {
			barCode = Long.parseLong(barCodeField.getText());
			price = Double.parseDouble(priceField.getText());
			quantity = Integer.parseInt(quantityField.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getRootPane(), "Illegal move", "STOP", JOptionPane.WARNING_MESSAGE, null);
			log.warn("Illegal move attempted");
			return;
		}
		String name = nameField.getText();
		String desc = "";
		
		StockItem stockItem = new StockItem(barCode, name, desc, price,
				quantity);
		fillDialogFields();
		if (stockItem != null) {
			// if(Arrays.asList(model.getWarehouseTableModel().getProductNames()).contains(name)){
			// model.getWarehouseTableModel().addItem(model.getWarehouseTableModel().getItemByName(name));
			// }
			model.getWarehouseTableModel().addItem(stockItem);
		}
		log.info("Added new item");
	}
	/**
	 * Sets whether or not this component is enabled.
	 */
	@Override
	public void setEnabled(boolean enabled) {
		this.confirmButton.setEnabled(enabled);
		this.quantityField.setEnabled(enabled);
		this.barCodeField.setEnabled(enabled);
		this.nameField.setEnabled(enabled);
		this.priceField.setEnabled(enabled);
	}
	/**
	 * Reset dialog fields.
	 */
	public void reset() {
		barCodeField.setText("");
		quantityField.setText("");
		nameField.setText("");
		priceField.setText("");
	}
	/*
	 * === Ideally, UI's layout and behaviour should be kept as separated as
	 * possible. If you work on the behaviour of the application, you don't want
	 * the layout details to get on your way all the time, and vice versa. This
	 * separation leads to cleaner, more readable and better maintainable code.
	 * 
	 * In a Swing application, the layout is also defined as Java code and this
	 * separation is more difficult to make. One thing that can still be done is
	 * moving the layout-defining code out into separate methods, leaving the
	 * more important methods unburdened of the messy layout code. This is done
	 * in the following methods.
	 */
	// Formatting constraints for the dialogPane
	private GridBagConstraints getDialogPaneConstraints() {
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.WEST;
		gc.weightx = 0.2;
		gc.weighty = 0d;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.fill = GridBagConstraints.NONE;
		return gc;
	}
	// Formatting constraints for the basketPane
	private GridBagConstraints getBasketPaneConstraints() {
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.WEST;
		gc.weightx = 0.2;
		gc.weighty = 1.0;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.fill = GridBagConstraints.BOTH;
		return gc;
	}
	private GridBagConstraints getBacketScrollPaneConstraints() {
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;
		gc.weightx = 1.0;
		gc.weighty = 1.0;
		return gc;
	}
}