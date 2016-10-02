package view.page.elements;

import javax.swing.JPanel;

import view.page.ViewCreator; 
import view.page.elements.graph.GraphPanelManager; 
import entity.Table;
import entity.ModifyTable; 

public class GraphViewCreator implements ViewCreator {
 
	@Override
	public JPanel getView(Table table, ModifyTable ... tables) {
		if (table != null) { 
			JPanel graph = new GraphPanelManager(table, tables); 
			return graph;
		}
		return null;
	}

}
