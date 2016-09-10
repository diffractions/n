package view;

import java.awt.Container;

import entity.Table;

public abstract class AbstractViewCreator implements ViewCreator {

	@Override
	public void getView(Table table) {
		getSizedView(cont.getWidth(), cont.getHeight(), table);
	}

	public Container cont;

	public AbstractViewCreator(Container cont) {
		this.cont = cont;
	}

}
