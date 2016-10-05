package view.page.elements.menu;


import javax.swing.JComboBox;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Color; 
import javax.swing.JComponent;
import javax.swing.JPanel;

import entity.ModifyTable;
import entity.Table;
import view.page.PageManager;
import view.page.ViewCreator;
import view.page.elements.graph.GraphRootPnaelUpdater; 

public class GraphStyleViewCreator implements ViewCreator {

  final JComboBox<?> combobox1 = new JComboBox<>(new String[]{"0","1","2","3","4","5","6","7","8","9","10"});  
  final JComboBox<?> combobox2 = new JComboBox<>(new String[]{"0","1","2","3","4","5","6","7","8","9","10"});  
 // final GraphRootPnaelUpdater ml1 = null;

	@Override
	public JComponent getView(final Table table, ModifyTable... tables) {
		JPanel p = new JPanel();
		p.setBackground(Color.BLACK);
		
		//ml1 = new GraphRootPnaelUpdater (table);
		int i1  = (int) PageManager.tableLineView.get(table).getDiameter(1);
		int i2  = (int) PageManager.tableLineView.get(table).getDropPoint();
		
		
		combobox1.setSelectedIndex(i1); 
		combobox1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				PageManager.tableLineView.get(table).setDiameter(1, combobox1.getSelectedIndex());
				new GraphRootPnaelUpdater (table).repaint();
			}
		});

		combobox2.setSelectedIndex(i2); 
		combobox2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				PageManager.tableLineView.get(table).setDropPoint( combobox2.getSelectedIndex());
				new GraphRootPnaelUpdater (table).repaint();
			}
		});



		p.add(combobox1);
		p.add(combobox2);
		return p;
	}

}
