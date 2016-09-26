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
			final JButton b_findExtr = new JButton("FIND EXTR");
			final JButton b_smoth = new JButton("SMOTHING");

			b_findExtr.setEnabled(false);
			b_smoth.setEnabled(false);

			textField.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					b_findExtr.setEnabled(true);
					b_smoth.setEnabled(true);
				}
			});

			ActionListener listener1 = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					PaintTableController.getInstance().createExtrTable(
							new Integer(textField.getText()));

					b_next.setEnabled(true);
				}
			};

			ActionListener listener2 = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					PaintTableController.getInstance().createSmothTable(
							new Integer(textField.getText()));

				}
			};

			b_findExtr.addActionListener(listener1);
			b_smoth.addActionListener(listener2);

			panel.add(b_findExtr);
			panel.add(b_smoth);

			panel.add(textField);
		}
		return panel;
	}

	public void setB_back(JButton b_back) {
		this.b_back = b_back;
		this.b_back.setEnabled(true);
	}

	public void setB_next(JButton b_next) {
		this.b_next = b_next;
		this.b_next.setEnabled(false);
	}

}
