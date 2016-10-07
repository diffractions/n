package view.page.elements.graph;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;


import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;


import entity.ModifyTable;

public class GraphTopPnaelMouseListener extends MouseAdapter {

	private int point;
	private ModifyTable table;
	private ModifyGraphPanel panel;
	private LinkedList<Component> hed;
	private JPopupMenu pm;

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


	public void addPopupMeny(JPopupMenu menu){
		this.pm = menu;
        JMenuItem mi = new JMenuItem(table.getHeaders()[1]);
        this.pm.add(mi);
 
		for(int i=0; i<this.pm.getComponentCount(); i++)
		if(this.pm.getComponent(i).getName().equals("add")){
			((JMenuItem)this.pm.getComponent(i)).addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent e){
						System.out.println(e);
					}
				}
			);
		} else if(this.pm.getComponent(i).getName().equals("del")){System.out.println(2);}


/*
for (int i = 0; i < popupMenu.getComponentCount(); i++) {
    if (popupMenu.getComponent(i).isVisible()) {
      allItemsInvisible = false;
      break;
    }
  } */

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
		if(point != -1 && this.pm!= null);
		if (e.getButton() == MouseEvent.BUTTON3) {
			this.pm.show(e.getComponent(), e.getX(), e.getY());
		}
		point = -1; 
	}




}
