package view.page.elements.menu;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import controller.PaintTableController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.page.ViewCreator;
import view.page.elements.AbstractMenuPanelViewCreator;
import entity.Table;
import entity.ModifyTable; 
import ga.Program;

public class StartMenuPanelViewCreator extends AbstractMenuPanelViewCreator
		implements ViewCreator {

	@Override
	public JPanel getView(final Table table, ModifyTable... tables) {
		JPanel panel = new JPanel();
		final JRadioButton transmittance = new JRadioButton("Transmittance",
				false);
		final JRadioButton reflection = new JRadioButton("Reflection", false);
		ButtonGroup startGroup = new ButtonGroup();

		startGroup.add(transmittance);
		startGroup.add(reflection);

		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_next.setEnabled(true);
				b_next.revalidate();

				if (e.getSource() == transmittance)
					PaintTableController.getInstance().setProgram(
							Program.Transmittance);
				else if (e.getSource() == reflection)
					PaintTableController.getInstance().setProgram(
							Program.Reflection);
			}
		};

		transmittance.addActionListener(listener);
		reflection.addActionListener(listener);

		panel.add(transmittance);
		panel.add(reflection);

		return panel;
	}

	public void setB_back(JButton b_back) {
		this.b_back = b_back;
		b_back.setEnabled(false);
	}

	public void setB_next(JButton b_next) {
		this.b_next = b_next;
		b_next.setEnabled(false);
	}

}
