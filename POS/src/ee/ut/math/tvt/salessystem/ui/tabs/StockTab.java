package ee.ut.math.tvt.salessystem.ui.tabs;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.panels.WarehousePanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

public class StockTab {

	private static final Logger log = Logger.getLogger(PurchaseTab.class);

	private WarehousePanel warehousePane;

	private JButton addItem;

	private JButton cancelButton;

	// private JButton confirmButton;

	private SalesSystemModel model;

	private final SalesDomainController domainController;

	public StockTab(SalesDomainController controller, SalesSystemModel model) {
		this.model = model;
		this.domainController = controller;
	}

	// warehouse stock tab - consists of a menu and a table
	public Component draw() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gc = new GridBagConstraints();
		panel.setLayout(gb);

		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.NORTH;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.weightx = 1.0d;
		gc.weighty = 0d;

		// menu
		panel.add(getStockMenuPane(), getConstraintsForWarehouseMenu());

		// table
		panel.add(getStockTablePane(), getConstraintsForWarehousePanel());

		gc.weighty = 1.0;
		gc.fill = GridBagConstraints.BOTH;
		return panel;
	}

	// warehouse menu
	private Component getStockMenuPane() {
		JPanel panel = new JPanel();

		panel.setLayout(new GridBagLayout());

		addItem = createAddItemButton();
		// confirmButton = createConfirmButton();
		cancelButton = createCancelButton();

		// Add the menu
		panel.add(addItem, getConstraintsForMenuButtons());
		// panel.add(confirmButton, getConstraintsForMenuButtons());
		panel.add(cancelButton, getConstraintsForMenuButtons());

		// confirmButton.setEnabled(false);
		cancelButton.setEnabled(false);

		return panel;
	}

	// warehouse table
	private Component getStockTablePane() {
		JPanel panel = new JPanel();

		panel.setLayout(new GridBagLayout());

		// Add the panel
		warehousePane = new WarehousePanel(model);
		panel.add(warehousePane, getConstraintsForWarehousePanel());
		return panel;
	}

	// create the add item button
	private JButton createAddItemButton() {
		JButton b = new JButton("Add item");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newAddItemButtonClicked();
			}
		});
		return b;
	}

	// // create the confirm button
	// private JButton createConfirmButton() {
	// JButton b = new JButton("Confirm");
	// b.addActionListener(new ActionListener() {
	// public void actionPerformed(ActionEvent e) {
	// confirmButtonClicked();
	// }
	// });
	// return b;
	// }

	// create the cancel button
	private JButton createCancelButton() {
		JButton b = new JButton("Cancel");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelButtonClicked();
			}
		});
		return b;
	}

	/** Event handler for the <code>add item</code> event. */
	protected void newAddItemButtonClicked() {
		log.info("Started adding new item");
		try {
			domainController.startNewAdd();
			startAdd();
		} catch (VerificationFailedException e1) {
			log.error(e1.getMessage());
		}
	}

	/** Event handler for the <code>cancel adding</code> event. */
	protected void cancelButtonClicked() {
		log.info("Item adding cancelled");
		try {
			domainController.cancelItemAdd();
			cancelAdd();
			model.getCurrentPurchaseTableModel().clear();
		} catch (VerificationFailedException e1) {
			log.error(e1.getMessage());
		}
	}

	// /** Event handler for the <code>confirm adding</code> event. */
	// protected void confirmButtonClicked() {
	// log.info("Added item");
	// try {
	// domainController.confirmItemAdd();
	// confirmAdd();
	// model.getCurrentPurchaseTableModel().clear();
	// } catch (VerificationFailedException e1) {
	// log.error(e1.getMessage());
	// }
	// }

	// switch UI to the state that allows to initiate new purchase
	private void cancelAdd() {
		warehousePane.reset();

		cancelButton.setEnabled(false);
		// confirmButton.setEnabled(false);
		addItem.setEnabled(true);
		warehousePane.setEnabled(false);
	}

	// // switch UI to the state that allows to initiate new purchase
	// private void confirmAdd() {
	// model.getWarehouseTableModel().addItem(new StockItem());
	//
	// warehousePane.reset();
	//
	// cancelButton.setEnabled(false);
	// confirmButton.setEnabled(false);
	// addItem.setEnabled(true);
	// warehousePane.setEnabled(false);
	// }

	// go to item adding mode
	private void startAdd() {
		warehousePane.reset();

		warehousePane.setEnabled(true);
		addItem.setEnabled(false);
		// confirmButton.setEnabled(true);
		cancelButton.setEnabled(true);
	}

	// constraint for the menu
	private GridBagConstraints getConstraintsForWarehouseMenu() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.NORTH;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.weightx = 1.0d;
		gc.weighty = 0d;

		return gc;
	}

	// constraints for the table
	private GridBagConstraints getConstraintsForWarehousePanel() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.NORTH;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.weightx = 1.0d;
		gc.weighty = 1.0;

		return gc;
	}

	// The constraints that control the layout of the buttons in the purchase
	// menu
	private GridBagConstraints getConstraintsForMenuButtons() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.weightx = 0;
		gc.anchor = GridBagConstraints.CENTER;
		gc.gridwidth = GridBagConstraints.RELATIVE;

		return gc;
	}

}