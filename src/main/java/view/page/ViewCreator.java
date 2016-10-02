package view.page;

import javax.swing.JComponent;

import entity.Table;
import entity.ModifyTable;

public interface ViewCreator {

	public JComponent getView(Table table, ModifyTable... tables);

}
