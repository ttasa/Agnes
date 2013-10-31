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

    JTextField paymentField;
    JTextField sumField;
    JTextField changeField;
    JButton acceptButton;
    JButton cancelButton;
    SalesSystemModel model;
	private static final Logger log = Logger.getLogger(PurchaseTab.class);

	
    public ConfirmationPane(SalesSystemModel model) {
        paymentField = new JTextField();
        sumField = new JTextField();
        changeField = new JTextField();
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
        this.add(sumField);
        sumField.setEnabled(false);
        
        this.add(new JLabel("Amount:"));
        this.add(paymentField);
        paymentField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("test");
			}
        });
        
        this.add(new JLabel("Change:"));
        this.add(changeField);
        changeField.setEnabled(false);
        
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
		this.setVisible(false);
        try {
            double payment = Double.parseDouble(paymentField.getText());
            double sum = Double.parseDouble(sumField.getText());
        } catch (NumberFormatException ex) {
        }
	}
	
	protected void cancelButtonClicked() {
		this.setVisible(false);
	}
    
    public void processPurchase(List<SoldItem> items) {
    	sumField.setText("" + getItemsTotalPrice(items));
    	model.getHistoryTableModel().addItem(
				model.getCurrentPurchaseTableModel().getTableRows());
    }
    
    private double getItemsTotalPrice(List<SoldItem> items) {
    	double sum = 0;
    	for (int i=0; i<items.size(); i++) {
    		sum += items.get(i).getSum();
    	}
    	return sum;
    }
	
}
