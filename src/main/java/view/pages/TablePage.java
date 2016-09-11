package view.pages;

import java.awt.GridLayout;

import javax.swing.JPanel;

import view.AbstractViewCreator;
import view.ViewCreator;
import view.elements.TableViewCreator;
import entity.Table;

public class TablePage extends AbstractViewCreator implements ViewCreator {

	private TableViewCreator table;

	public TablePage() {
		this.table = new TableViewCreator();
		this.pageToView = new JPanel();
	}

	@Override
	public void fillComponent(Table table) {
		pageToView.setLayout(new GridLayout(1, 1));
		pageToView.add(this.table.getView(table));
	}
}
