package view.page.elements.graph;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import entity.Table;

public class GraphRootPnaelUpdater  {

	private LinkedList<Component> hed;

	private Table table;

	private static Map<Table, LinkedList<Component>> tables = new HashMap<Table, LinkedList<Component>>() {

		private static final long serialVersionUID = 1L;

		public LinkedList<Component> put(Table key,
				LinkedList<Component> value) {
			if (get(key) != null)
				return get(key);
			return super.put(key, value);
		};

	};

	public GraphRootPnaelUpdater(Table table) {

		this.table = table;

		if (tables.get(table) == null) {
			this.hed = new LinkedList<Component>();
			tables.put(this.table, this.hed);
		} else {
			this.hed = tables.get(table);
		}

	}


	public void addComponent(Component comp) {
		// System.out.println(table + " " + comp);
		this.hed.add(comp);
	}


	public void repaint(){
		for (Component com : tables.get(table))
		com.repaint();
	}


}
