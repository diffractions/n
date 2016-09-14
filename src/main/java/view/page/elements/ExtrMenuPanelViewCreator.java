package view.page.elements;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.page.ViewCreator;
import entity.Table;
import ga.Program;
import controller.PaintTableController;

public class ExtrMenuPanelViewCreator implements ViewCreator {

	@Override
	public JPanel getView(final Table table) {
		JPanel panel = null;
		if (table != null) {
 			panel = new JPanel();

			JButton b_calcExtr = new JButton("FIND EXTR");
 			JButton b_recalculate = new JButton("APPLY CHANGES");


			b_calcExtr.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					PaintTableController.getInstance().createResultTable();
				}
			});
			b_recalculate.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					PaintTableController.getInstance().createResultTable();
				}
			});
			
			panel.add(b_calcExtr);
			panel.add(b_recalculate);
		}
		return panel;
	}

	private void paintAxis(Table table) {

	}

}


