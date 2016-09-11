package view;

import java.awt.Container;

import entity.Table;

public interface ViewCreator {


	public Container getView(Table table);
//	public Container getComponent(Table table);
//	public Container getView(int width, int height, Table table);

}
