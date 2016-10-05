package view.page.elements;

import javax.swing.JPanel;

import view.page.ViewCreator; 
import view.page.elements.graph.GraphPanelManager; 
import entity.Table;
import entity.ModifyTable; 

public class GraphViewCreator implements ViewCreator {
 
//private GraphPanelManager graph = null;
	@Override
	public JPanel getView(Table table, ModifyTable ... tables) {
		if (table != null) { 
			//if(graph==null)
			//graph = new GraphPanelManager(); 

			return new GraphPanelManager(table, tables);
		}
		return null;
	}

}
