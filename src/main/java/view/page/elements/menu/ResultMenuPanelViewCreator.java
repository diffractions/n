package view.page.elements.menu;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.PaintTableController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import view.page.ViewCreator;
import view.page.elements.AbstractMenuPanelViewCreator;
import entity.Table;
import entity.ModifyTable;

public class ResultMenuPanelViewCreator extends AbstractMenuPanelViewCreator implements ViewCreator {

	public void setB_back(JButton b_back) {
		this.b_back = b_back;
		b_back.setEnabled(true);
	}

	public void setB_next(JButton b_next) {
		this.b_next = b_next;
		b_next.setEnabled(false);
	}

	@Override
	public JComponent getView(Table table, ModifyTable... tables) {
		JPanel panel = null;
		if (table != null) {

			panel = new JPanel();

			final JButton b_calc = new JButton("CLACULATE");

			b_calc.setEnabled(true);

			ActionListener listener1 = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					PaintTableController.getInstance().calculateN();
				}
			};

			b_calc.addActionListener(listener1);

			panel.add(b_calc);
		}
		return panel;
	}

}
