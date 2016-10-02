package view.page;

import java.awt.BorderLayout;
import java.awt.Container;

import view.page.panels.GraphPanel;
import view.page.panels.MenuPanel;
import view.page.panels.RedactorPanel;
import view.page.panels.TablePanel;
import entity.SimpleTable;

public class PageManager {

	private Container cont;
	private ViewCreator redactorPage;
	private ViewCreator graphPage;
	private ViewCreator tablePage;
	private ViewCreator menuElement;

	public PageManager(Container cont) {

		this.cont = cont;
		redactorPage = new RedactorPanel();
		graphPage = new GraphPanel();
		tablePage = new TablePanel();
		menuElement = new MenuPanel();

	}

	public void paintTablePage(SimpleTable table) {
		paint(table, tablePage);

	}

	public void paintRedactorPage(SimpleTable table) {
		paint(table, redactorPage);
	}
	

	public void paintGraphPage(SimpleTable table) {
		paint(table, graphPage);
	}

	private void paint(SimpleTable table, ViewCreator viev) {
		cont.removeAll();
		cont.add(viev.getView(table));
		cont.add(menuElement.getView(table), BorderLayout.SOUTH);
		cont.revalidate();
	}

}