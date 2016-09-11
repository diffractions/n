package view.elements;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import view.ViewCreator;
import entity.Table;

public class TableViewCreator /* extends AbstractViewCreator */implements
		ViewCreator {

	// public TableViewCreator(Container cont) {
	// super(cont);
	// };

	// public TableViewCreator() {
	// };

	private JTable tables = new JTable();
	private JScrollPane view = new JScrollPane(tables);

	// @Override
	// public JComponent getView(int width, int height, Table table) {
	// // if (table != null) {
	// // tables.setModel(getJTable(table));
	// // view.setSize(width, height);
	// // }
	// return view;
	// }

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
