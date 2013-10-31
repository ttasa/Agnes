package ee.ut.math.tvt.salessystem.ui.panels.panes;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.tabs.PurchaseTab;

public class ConfirmationPane extends JPanel {

    JTextField paymentAmount;
    JTextField sumOfOrder;
    JTextField changeAmount;
    JButton acceptButton;
    JButton cancelButton;
    SalesSystemModel model;
	private static final Logger log = Logger.getLogger(PurchaseTab.class);

	
    public ConfirmationPane(SalesSystemModel model) {
        paymentAmount = new JTextField();
        sumOfOrder = new JTextField();
        changeAmount = new JTextField();
        acceptButton = createAcceptButton();
        cancelButton = createCancelButton();
        this.model = model;
        drawConfirmationPane();
    }
    
    public void drawConfirmationPane() {

        // Create the panel
        this.setLayout(new GridLayout(4, 2));
        this.setBorder(BorderFactory.createTitledBorder("Confirmation"));
        
        this.add(new JLabel("Total sum:"));
        this.add(sumOfOrder);
        
        this.add(new JLabel("Amount:"));
        this.add(paymentAmount);
        
        this.add(new JLabel("Change:"));
        this.add(changeAmount);
        
        this.add(acceptButton);
        this.add(cancelButton);
        
    }
    
	private JButton createAcceptButton() {
		JButton b = new JButton("Accept the purchase");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acceptButtonClicked();
			}
		});

		return b;
	}
	
	private JButton createCancelButton() {
		JButton b = new JButton("Cancel");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelButtonClicked();
			}
		});

		return b;
	}
	
	protected void acceptButtonClicked() {

	}
	
	protected void cancelButtonClicked() {
	}
    
    public void processPurchase(List<SoldItem> items) {
    	sumOfOrder.setText("1");
    	model.getHistoryTableModel().addItem(
				model.getCurrentPurchaseTableModel().getTableRows());
    }
	
}
