package view.page.elements.menu;

import javax.swing.JComponent;

import view.page.ViewCreator;
import entity.Table;
import entity.ModifyTable; 

public abstract class AbstractPageViewCreator implements ViewCreator {
	protected JComponent pageToView;

	public JComponent getView(Table table, ModifyTable... tables) {
		pageToView.removeAll();
		fillComponent(table, tables);
		// p.revalidate();
		return pageToView;
	}

	abstract public void fillComponent(Table table, ModifyTable... tables);

}
