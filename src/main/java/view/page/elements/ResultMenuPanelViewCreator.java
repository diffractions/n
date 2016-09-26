package view.page.elements;

import javax.swing.JPanel;
import javax.swing.JButton;

import view.page.ViewCreator;
import entity.Table;

public class ResultMenuPanelViewCreator extends AbstractMenuPanelViewCreator
		implements ViewCreator {

	@Override
	public JPanel getView(final Table table) {
//		JPanel panel = null;

		return new JPanel();
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
