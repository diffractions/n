package view.page.elements;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import view.page.ViewCreator;
import view.page.elements.graph.GraphTopPnaelMouseListener;
import entity.Table;
import entity.ModifyTable;

public class TableViewCreator implements ViewCreator {

	private Table tableCopy = null;
	private ModifyTable[] tablesCopy = null;

	private JTable tablest;
	private JScrollPane view;
	private DefaultTableCellRenderer[][] colortext = null;

	private DefaultTableCellRenderer bl = new DefaultTableCellRenderer();
	private DefaultTableCellRenderer rd = new DefaultTableCellRenderer();

	private GraphTopPnaelMouseListener ml;

	public TableViewCreator() {
		this.bl.setForeground(Color.BLUE);
		this.rd.setForeground(Color.RED);
		this.tablest = new JTable() {

			private static final long serialVersionUID = 1L;

			@Override
			public TableCellRenderer getCellRenderer(int arg0, int arg1) {
				if (colortext[arg0][arg1] == null) {

					return bl;
				} else
					return colortext[arg0][arg1];
			}

		};

		this.view = new JScrollPane(tablest);

	}

	private DefaultTableModel getJTableModel(Table table, ModifyTable... tables) {
		DefaultTableModel models = new DefaultTableModel();

		this.tableCopy = table;
		this.tablesCopy = tables;

		int rows = table.getRowCount();

		for (String collName : table.getHeaders()) {
			models.addColumn(collName);
		}

		for (ModifyTable tab : tables) {

			rows += tab.getRowCount();

			for (String collName : tab.getHeaders()) {
				if (!hasColl(models, collName))
					models.addColumn(collName);
			}
		}

		this.colortext = new DefaultTableCellRenderer[rows][models
				.getColumnCount()];

		for (double[] s : table.getTable()) {
			Double d[] = new Double[models.getColumnCount()];
			for (int i = 0; i < s.length; i++)
				d[i] = s[i];
			models.addRow(d);
		}

		for (ModifyTable tab : tables)
			for (double[] s : tab.getTable()) {
				Double d[] = new Double[models.getColumnCount()];
				d[0] = s[0];
				d[models.findColumn(tab.getHeaders()[1])] = s[1];
				models.addRow(d);
				for (int i = 0; i < models.getColumnCount(); i++) {
					colortext[models.getRowCount() - 1][i] = rd;
				}
			}

		return models;
	}

	@Override
	public JScrollPane getView(Table table, ModifyTable... tables) {

		this.tablest.setModel(getJTableModel(table, tables));

		JComponent tableModifyMockComponent = new JComponent() {
			private static final long serialVersionUID = 1L;

			public void repaint() {
				if (tableCopy != null)
					tablest.setModel(getJTableModel(tableCopy, tablesCopy));
			};
		};

		for (ModifyTable tab : tables) {
			ml = new GraphTopPnaelMouseListener(tab);
			ml.addComponent(tableModifyMockComponent);
		}

		return view;
	}

	private boolean hasColl(DefaultTableModel models, String collName) {

		for (int i = 0; i < models.getColumnCount(); i++) {

			if (models.getColumnName(i).equals(collName)) {
				return true;
			}
		}
		return false;
	}

}
