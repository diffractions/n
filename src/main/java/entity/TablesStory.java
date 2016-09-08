package entity;

import service.Updating;

public interface TablesStory extends TableStory {
	  public TableStory getStory(String name);
	  public void setStory(String name, Table table);
	  public Table getTable();
	  public Updating getUpdating();
}
