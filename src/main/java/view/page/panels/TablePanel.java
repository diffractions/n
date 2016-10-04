package view.page.panels;

import java.awt.GridLayout;

import javax.swing.JPanel;

import view.page.ViewCreator;
import view.page.elements.TableViewCreator;
import view.page.elements.menu.AbstractPageViewCreator;
import entity.Table;
import entity.ModifyTable; 

public class TablePanel extends AbstractPageViewCreator implements ViewCreator {

	private TableViewCreator table;

	public TablePanel() {
		this.table = new TableViewCreator();
		this.pageToView = new JPanel();
	}

	@Override
	public void fillComponent(Table table, ModifyTable... tables) {
		pageToView.setLayout(new GridLayout(1, 1));
		pageToView.add(this.table.getView( table,  tables));
	}
}
