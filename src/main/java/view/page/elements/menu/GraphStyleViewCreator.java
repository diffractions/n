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
import java.util.Arrays;

import javax.swing.border.TitledBorder;


public class GraphStyleViewCreator implements ViewCreator {


 // final JComboBox<Integer> combobox1 = new JComboBox<>(new Integer[]{0,1,2,3,4,5,6,7,8,9,10});  
  final Integer[] dropPoint =  new Integer[]{1,2,3,4,5,6,7,8,9,10};
   final JComboBox<Integer> combobox2 = new JComboBox<>(dropPoint);    

	@Override
	public JComponent getView(final Table table, ModifyTable... tables) {
		JPanel p = new JPanel();
		p.setBackground(Color.GREEN);
		
		
		
		//ml1 = new GraphRootPnaelUpdater (table);
		int i1  = (int) PageManager.tableLineView.get(table).getDiameter(1);
		int i2  = Arrays.binarySearch(dropPoint,(int) PageManager.tableLineView.get(table).getDropPoint());
		

		for(int i = 1; i< table.getCollCount(); i++){
  			final JComboBox<Integer> combobox1 = new JComboBox<>(new Integer[]{0,1,2,3,4,5,6,7,8,9,10}); 
			combobox1.setBorder(new TitledBorder(table.getHeaders()[i]));
			final int size = i;
			combobox1.setSelectedIndex(i1); 

			combobox1.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					PageManager.tableLineView.get(table).setDiameter(size, combobox1.getSelectedIndex());
					new GraphRootPnaelUpdater (table).repaint();
				}
			});

			p.add(combobox1);
		}

		combobox2.setSelectedIndex(i2); 
		combobox2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				PageManager.tableLineView.get(table).setDropPoint( dropPoint[combobox2.getSelectedIndex()]);
				new GraphRootPnaelUpdater (table).repaint();
			}
		});



		//p.add(combobox1);
		p.add(combobox2);
		return p;
	}

}
