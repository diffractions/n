package view.page;

import javax.swing.JComponent;

import entity.Table;

public interface ViewCreator {

	public JComponent getView(Table table);

}
