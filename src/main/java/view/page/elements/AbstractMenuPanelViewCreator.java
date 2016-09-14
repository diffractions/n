package view.page.elements;

import javax.swing.JButton;

import view.page.ViewCreator;

public abstract class AbstractMenuPanelViewCreator implements ViewCreator {

	protected JButton b_back;
	protected JButton b_next;

	public abstract void setB_back(JButton b_back);

	public abstract void setB_next(JButton b_next);

}
