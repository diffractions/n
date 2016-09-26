package view.page.elements;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.page.ViewCreator;
import entity.Table;
import controller.PaintTableController;

import javax.swing.*;

public class ExtrMenuPanelViewCreator extends AbstractMenuPanelViewCreator
		implements ViewCreator {

	@Override
	public JPanel getView(final Table table) {
		JPanel panel = null;
		if (table != null) {
 
			panel = new JPanel();

			final JTextField textField = new JTextField(4);
			final JButton b_calcExtr = new JButton("FIND EXTR");
			final JButton b_smoth = new JButton("SMOTHING");

			b_calcExtr.setEnabled(false);
			b_smoth.setEnabled(false);

			textField.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					b_calcExtr.setEnabled(true);
					b_smoth.setEnabled(true);
				}
			});

			ActionListener listener1 = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					b_next.setEnabled(true);
					PaintTableController.getInstance().createExtrTable(
							new Integer(textField.getText()));
				}
			};

			ActionListener listener2 = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					b_next.setEnabled(true);
					PaintTableController.getInstance().createSmothTable(
							new Integer(textField.getText()));
				}
			};

			b_calcExtr.addActionListener(listener1);
			b_smoth.addActionListener(listener2);

			panel.add(b_calcExtr);
			panel.add(b_smoth);

			panel.add(textField);
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
