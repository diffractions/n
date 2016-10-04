package view.page.elements.menu;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import entity.ModifyTable;
import entity.Table;
import view.page.PageManager;
import view.page.ViewCreator;

public class GraphStyleViewCreator implements ViewCreator {

	@Override
	public JComponent getView(final Table table, ModifyTable... tables) {
		JPanel p = new JPanel();
		p.setBackground(Color.BLACK);
		
		PageManager.tableLineView.get(table);
		JButton b = new JButton("INC");
		b.setAction(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int i  = (int) PageManager.tableLineView.get(table).getDiameter(2);
				PageManager.tableLineView.get(table).setDiameter(2, i+2);
				
			}
		});
		p.add(b);

		return p;
	}

}
