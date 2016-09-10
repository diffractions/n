package view.elements;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import view.AbstractViewCreator;
import view.ViewCreator;
import entity.Table;

public class TableViewCreator extends AbstractViewCreator implements
		ViewCreator {

	public TableViewCreator(int x, int y) {
		super(x, y);
	};

	private JTable tables = new JTable();
	private JScrollPane view = new JScrollPane(tables);

	@Override
	public JComponent getView(Table table) {

		if (table != null) {
			tables.setModel(getJTable(table));
//			view.setBorder(BorderFactory.createLineBorder(Color.GREEN));
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
