package ee.ut.math.tvt.salessystem.ui.model;

import java.util.List;

import ee.ut.math.tvt.salessystem.domain.data.Client;

/**
 * Client model.
 */
public class ClientTableModel extends SalesSystemTableModel<Client> {
	private static final long serialVersionUID = 1L;

	private List<Client> clients;

	public ClientTableModel() {
		super(new String[] { "Id", "First name", "Discount" });
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
		fireTableDataChanged();
	}

	@Override
	protected Object getColumnValue(Client client, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return client.getId();
		case 1:
			return client.getFirstName();
		case 2:
			return client.getDiscountPercentage();
		}
		throw new IllegalArgumentException("Column index out of range");
	}

	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < headers.length; i++)
			buffer.append(headers[i] + "\t");
		buffer.append("\n");

		for (final Client client : getTableRows()) {
			buffer.append(client.getId() + "\t");
			buffer.append(client.getFirstName() + "\t");
			buffer.append(client.getDiscountPercentage() + "\t");
			buffer.append("\n");
		}

		return buffer.toString();
	}

	@Override
	public List<Client> getTableRows() {
		return clients;
	}
}
