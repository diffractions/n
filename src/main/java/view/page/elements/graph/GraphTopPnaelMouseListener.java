package view.page.elements.graph;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import entity.ModifyTable;

public class GraphTopPnaelMouseListener extends MouseAdapter {

	private int point;
	private ModifyTable table;
	private ModifyGraphPanel panel;
	private LinkedList<Component> hed;

	private static Map<ModifyTable, LinkedList<Component>> tables = new HashMap<ModifyTable, LinkedList<Component>>() {

		private static final long serialVersionUID = 1L;

		public LinkedList<Component> put(ModifyTable key,
				LinkedList<Component> value) {
			if (get(key) != null)
				return get(key);
			return super.put(key, value);
		};

	};

	public GraphTopPnaelMouseListener(ModifyTable table) {

		this.table = table;

		if (tables.get(table) == null) {
			this.hed = new LinkedList<Component>();
			tables.put(this.table, this.hed);
		} else {
			this.hed = tables.get(table);
		}

	}

	public void addModifyGraphPanel(ModifyGraphPanel panel) {
		this.panel = panel;
	}

	public void addComponent(Component comp) { 
		this.hed.add(comp);
	}

	public void mouseDragged(MouseEvent e) { 
		if (point != -1) {
			int x = e.getX();
			int y = e.getY();
			panel.movePoint(point, x, y);
			repaint();
		}
	}


	public void repaint(){
		panel.getLineView().setUpdate(true);
		for (Component com : tables.get(table))
		com.repaint();
	}

	public void mousePressed(MouseEvent e) {
		point = panel.searchPoint(e); 
	}

	public void mouseReleased(MouseEvent e) {
		point = -1; 
	}

}
