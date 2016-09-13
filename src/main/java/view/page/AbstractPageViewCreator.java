package view.page;

import javax.swing.JComponent;

import entity.Table;

public abstract class AbstractPageViewCreator implements ViewCreator {
	protected JComponent pageToView;

	public JComponent getView(Table table) {
		pageToView.removeAll();
		fillComponent(table);
		// p.revalidate();
		return pageToView;
	}

	abstract public void fillComponent(Table table);
}
