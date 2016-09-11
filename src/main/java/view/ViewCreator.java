package view;

import java.awt.Container;

import entity.Table;

public interface ViewCreator {

	public Container getView(Table table);

}
