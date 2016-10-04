package view.page;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.HashMap;
import java.util.Map;

import view.page.elements.graph.GraphLineView;
import view.page.panels.GraphPanel;
import view.page.panels.MenuPanel;
import view.page.panels.RedactorPanel;
import view.page.panels.TablePanel;
import entity.ModifyTable; 
import entity.Table;

public class PageManager {

	private Container cont;
	private ViewCreator redactorPage;
	private ViewCreator graphPage;
	private ViewCreator tablePage;
	private ViewCreator menuElement;
	
	public static Map<Table, GraphLineView> tableLineView = new HashMap<Table, GraphLineView>() {

		private static final long serialVersionUID = 1L;

		public GraphLineView put(Table key, GraphLineView value) {
			if (get(key) != null) {
				// System.out.println("++++++++++");
				get(key).update(key);
				return get(key);
			} else
				// System.out.println("-----------");
				return super.put(key, value);
		}

	};

	public PageManager(Container cont) {

		this.cont = cont;
		redactorPage = new RedactorPanel();
		graphPage = new GraphPanel();
		tablePage = new TablePanel();
		menuElement = new MenuPanel();

	}

	public void paintTablePage(Table table, ModifyTable... tables) {
		paint(tablePage, table,tables );

	}

	public void paintRedactorPage(Table table, ModifyTable... tables) {
		paint(redactorPage, table,tables );
	}
	

	public void paintGraphPage(Table table, ModifyTable... tables) {
		paint(graphPage, table,tables );
	}

	private void paint(ViewCreator viev, Table table, ModifyTable... tables) {
		cont.removeAll();
		cont.add(viev.getView(table,tables));
		cont.add(menuElement.getView(table,tables), BorderLayout.SOUTH);
		cont.revalidate();
	}
	
	

}