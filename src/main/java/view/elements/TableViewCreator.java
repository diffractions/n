package view.elements;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
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

	@Override
	public JComponent getView(Table table) {

		if (tables == null)
			tables = new JTable();
		if (table == null)
			return new JPanel();

		JTable tables = getJTable(table);
		JComponent view = new JScrollPane(tables);
		view.setSize(x, y);
		view.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		return view;
	}

	JTable tables = null;

	private JTable getJTable(Table table) {

	//	JTable tables = new JTable();
	//	if (table == null)
	//		return tables;



		DefaultTableModel models = new DefaultTableModel();

		for (String collName : table.getHeaders())
			models.addColumn(collName);

		for (double[] s : table.getTable()) {
			Double d[] = new Double[s.length];
			for (int i = 0; i < s.length; i++)
				d[i] = s[i];
			models.addRow(d);
		}

		tables.setModel(models);
		return tables;
	}
}
