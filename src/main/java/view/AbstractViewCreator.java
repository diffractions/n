package view;

import java.awt.Container;

import entity.Table;

public abstract class AbstractViewCreator implements ViewCreator {

	@Override
	public Container getView(Table table) {
		return getView(cont.getWidth(), cont.getHeight(), table);
	}

	public Container cont;

	public AbstractViewCreator(Container cont) {
		this.cont = cont;
	}

}
