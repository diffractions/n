package view.elements;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import view.ViewCreator;
import entity.Table;

public class TableViewCreator implements ViewCreator {

	private JTable tables;
	private JScrollPane view;

	public TableViewCreator() {
		tables = new JTable();
		view = new JScrollPane(tables);

	}

	@Override
	public JComponent getView(Table table) {
		if (table != null) {
			tables.setModel(getJTable(table));
		}
		return view;
	}

	private DefaultTableModel getJTable(Table table) {
		DefaultTableModel models = new DefaultTableModel();
		for (String collName : table.getHeaders())
			models.addColumn(collName);
		for (double[] s : table.getTable()) {
			Double d[] = new Double[s.length];
			for (int i = 0; i < s.length; i++)
				d[i] = s[i];
			models.addRow(d);
		}
		return models;
	}

}
