package view.elements;

import java.awt.Container;

import javax.swing.JComponent;
import javax.swing.JPanel;

//import view.AbstractViewCreator;
import view.ViewCreator;
import entity.Table;

public class GraphViewCreator /*extends AbstractViewCreator*/ implements
		ViewCreator {

//	public GraphViewCreator(Container cont) {
//		super(cont);
//	}
	
	public GraphViewCreator(   ) { 
	}

//	@Override
//	public JComponent getView(int width, int height, Table table) {
////		if (table != null) {
////			JPanel graph = new GraphPanel(table);
////			graph.setSize(width, height);
////			paintAxis(table);
////			return graph;
////		}
//		return null;
//	}

	@Override
	public JComponent getView( Table table) {
		if (table != null) {
			JPanel graph = new GraphPanel(table); 
			paintAxis(table);
			return graph;
		}
		return null;
	}
	
	private void paintAxis(Table table) {

	}

}
