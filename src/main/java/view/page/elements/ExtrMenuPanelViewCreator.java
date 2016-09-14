package view.page.elements;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.page.ViewCreator;
import entity.Table;
import controller.PaintTableController;

public class ExtrMenuPanelViewCreator extends AbstractMenuPanelViewCreator
		implements ViewCreator {

	@Override
	public JPanel getView(final Table table) {
		JPanel panel = null;
		if (table != null) {
			panel = new JPanel();

			JButton b_calcExtr = new JButton("FIND EXTR");
			JButton b_recalculate = new JButton("APPLY CHANGES");

			ActionListener listener = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					b_next.setEnabled(true);
					PaintTableController.getInstance().createResultTable();
				}
			};

			b_calcExtr.addActionListener(listener);
			b_recalculate.addActionListener(listener);

			panel.add(b_calcExtr);
			panel.add(b_recalculate);
		}
		return panel;
	}

	public void setB_back(JButton b_back) {
		this.b_back = b_back;
		b_back.setEnabled(true);
	}

	public void setB_next(JButton b_next) {
		this.b_next = b_next;
		b_next.setEnabled(false);
	}

}
