package ee.ut.math.tvt.salessystem.ui.panels.panes;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.tabs.PurchaseTab;

public class ConfirmationPane extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField paymentField;
    JTextField sumField;
    JTextField changeField;
    JButton acceptButton;
    JButton cancelButton;
    SalesSystemModel model;
    PurchaseTab purchaseTab;
	private static final Logger log = Logger.getLogger(ConfirmationPane.class);

	
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
        paymentField.getDocument().addDocumentListener(new DocumentListener() {

			public void insertUpdate(DocumentEvent e) {
				changeField.setText(getChange());
			}

			public void removeUpdate(DocumentEvent e) {
				changeField.setText(getChange());
			}

			public void changedUpdate(DocumentEvent e) {
				changeField.setText(getChange());
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
		try {
			double payment = Double.parseDouble(paymentField.getText());
			double sum = Double.parseDouble(sumField.getText());
			if (payment >= sum) {
				reset();
				purchaseTab.submitPurchaseButtonClicked();
			}
			else {
				JOptionPane.showMessageDialog(null, "Payment not big enough!");
			}
		} 
		catch (NumberFormatException ex) {
			log.error(ex);
			JOptionPane.showMessageDialog(null, "Bad amount!");
		}
	}
	
	protected void cancelButtonClicked() {
		purchaseTab.setOrderButtonsEnabledTo(true);
		this.setVisible(false);
	}
    
    public void processPurchase(PurchaseTab purchaseTab, List<SoldItem> items) {
    	this.purchaseTab = purchaseTab;
    	sumField.setText(getItemsSum(items));
    }
    
    private String getItemsSum(List<SoldItem> items) {
    	double sum = 0;
    	
    	for (int i=0; i<items.size(); i++) {
    		sum += items.get(i).getSum();
    	}
    	
    	return "" + sum;
    }
    
    private String getChange() {
		try {
			double payment = Double.parseDouble(paymentField.getText());
			double sum = Double.parseDouble(sumField.getText());
			if (payment >= sum) {
				return "" + (payment-sum);
			}
		} 
		catch (NumberFormatException ex) {
			//Ei tundu olevat logimist v‰‰rt
		}
		return "";
    }
    
    private void reset() {
    	paymentField.setText("");
    }
	
}
