package view.page.elements.graph;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import entity.ModifyTable;

import javax.swing.AbstractAction;
import javax.swing.JMenu;

public class GraphPanelManagerPopMenu extends JPopupMenu {

	private static final long serialVersionUID = 1L;

	private Map<ModifyTable, GraphTopPnaelMouseListener> list;

	final private JMenuItem mi_del;
	final private JMenu mi_add;

	public GraphPanelManagerPopMenu() {

		list = new HashMap<ModifyTable, GraphTopPnaelMouseListener>();

		mi_del = new JMenuItem(new AbstractAction("del") {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				for (ModifyTable g : list.keySet()) {
					int pos = -1;
					if ((pos = list.get(g).getPoint()) != -1) {
						g.delRow(pos);
						list.get(g).repaint();
					}
				}

			}
		});

		mi_add = new JMenu("add");

		mi_del.setEnabled(false);
		mi_add.setEnabled(false);

		add(mi_del);
		add(mi_add);

	}

	public void addTbale(ModifyTable table, GraphTopPnaelMouseListener modif) {
		list.put(table, modif);
		// JMenuItem mi = new JMenuItem(table.getHeaders()[1]);
		// mi_add.add(mi);

	}

	@Override
	public void show(Component invoker, final int x, final int y) {

		int pos = -1;
		for (ModifyTable g : list.keySet()) {
			mi_add.setEnabled(false);
			mi_del.setEnabled(false);
			if ((pos = list.get(g).getPoint()) != -1) {
				mi_del.setEnabled(true);
				break;
			}
		}
		if (pos == -1) {

			mi_add.removeAll();
			for (ModifyTable g : list.keySet()) {
				mi_add.setEnabled(true);
				final ModifyTable g1 = g;
				mi_add.add(new AbstractAction(g.getHeaders()[1]) {

					private static final long serialVersionUID = 1L;

					@Override
					public void actionPerformed(ActionEvent e) {
						g1.addRow(list.get(g1).grphToTable(x, y));
						list.get(g1).repaint();
					}
				});
			}
		}

		super.show(invoker, x, y);
	}
}