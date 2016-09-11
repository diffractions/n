package view;

import java.awt.Container;

import javax.swing.JComponent;

import entity.Table;

public abstract class AbstractViewCreator implements ViewCreator {
	protected JComponent pageToView;

	public Container getView(Table table) {
		pageToView.removeAll();
		fillComponent(table);
//		p.revalidate();
		return pageToView;
	}

	public void fillComponent(Table table){
		
	};
}
