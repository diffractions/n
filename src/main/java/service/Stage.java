package service;

import javax.swing.JButton;

import view.page.elements.AbstractMenuPanelViewCreator;
import view.page.elements.menu.ExtrMenuPanelViewCreator;
import view.page.elements.menu.ResultMenuPanelViewCreator;
import view.page.elements.menu.StartMenuPanelViewCreator;

public enum Stage {
	OPEN(new StartMenuPanelViewCreator()), EXTR(new ExtrMenuPanelViewCreator()), RESULT(new ResultMenuPanelViewCreator());

	private final AbstractMenuPanelViewCreator id;

	Stage(AbstractMenuPanelViewCreator id) {
		this.id = id;
	}

	public AbstractMenuPanelViewCreator getMenuViewCreator(JButton b_back, JButton b_next) {
		id.setB_back(b_back);
		id.setB_next(b_next);
		return id;
	}
}