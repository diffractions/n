package view.page.panels;

import java.awt.GridLayout;

import javax.swing.JPanel;

import view.page.AbstractPageViewCreator;
import view.page.ViewCreator;
import view.page.elements.TableViewCreator;
import entity.Table;

public class TablePanel extends AbstractPageViewCreator implements ViewCreator {

	private TableViewCreator table;

	public TablePanel() {
		this.table = new TableViewCreator();
		this.pageToView = new JPanel();
	}

	@Override
	public void fillComponent(Table table) {
		pageToView.setLayout(new GridLayout(1, 1));
		pageToView.add(this.table.getView(table));
	}
}
