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

			
			final JTextField iter = new JTextField(4);
			final JTextField thr = new JTextField(4);

			panel = new JPanel();

			final JButton b_calc = new JButton("CLACULATE");
			final JButton stop = new JButton("STOP");

			b_calc.setEnabled(true);

			ActionListener listener1 = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if(new Integer(iter.getText())!=null && new Integer(thr.getText())!=null)
						PaintTableController.getInstance().calculateN(new Integer(iter.getText()),new Integer(thr.getText()));
					else
						PaintTableController.getInstance().calculateN();
				}
			};

			b_calc.addActionListener(listener1);


			stop.setEnabled(true);

			ActionListener listener2 = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					PaintTableController.getInstance().stopCalculateN();
				}
			};

			stop.addActionListener(listener2);

			panel.add(stop);
			panel.add(b_calc);
			panel.add(iter);
			panel.add(thr);
		}
		return panel;
	}

}
