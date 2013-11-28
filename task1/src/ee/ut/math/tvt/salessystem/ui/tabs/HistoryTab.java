package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "History" in the menu).
 */
public class HistoryTab {

	private static final Logger log = Logger.getLogger(PurchaseTab.class);
	private final SalesDomainController domainController;
	private SalesSystemModel model;

	public HistoryTab(SalesDomainController controller, SalesSystemModel model) {
		this.domainController = controller;
		this.model = model;
	}

	public Component draw() {
		JPanel panel = new JPanel();

		panel.setLayout(new GridBagLayout());
		panel.setBorder(BorderFactory.createTitledBorder("History"));

		final JTable infoTable = new JTable(model.getHistoryTableModel());
		final PurchaseInfoTableModel itemsTableModel = new PurchaseInfoTableModel();
		final JTable itemsTable = new JTable(itemsTableModel);

		infoTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				int selectedRow = infoTable.getSelectedRow();
				if (selectedRow >= 0) {
					itemsTableModel.populateWithData(model
							.getHistoryTableModel().getSaleItems(selectedRow));
				}
			}

		});

		panel.add(new JScrollPane(infoTable), getBacketScrollPaneConstraints());
		panel.add(new JScrollPane(itemsTable), getBacketScrollPaneConstraints());

		return panel;
	}
	
	private GridBagConstraints getBacketScrollPaneConstraints() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.fill = GridBagConstraints.BOTH;
		gc.weightx = 1.0;
		gc.weighty = 1.0;

		return gc;
	}

}