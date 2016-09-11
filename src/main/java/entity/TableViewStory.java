//package entity;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import service.TableUpdating;
//import service.Updating;
//
//public class TableViewStory implements TablesStory {
//
//	private static TableViewStory st = new TableViewStory();
//	private static Map<String, TableStory> story = new HashMap<>();
//
//	private Table table;
//	private Updating updating;
//
//	public static TablesStory getInstance() {
//		return st;
//	}
//
//	private TableViewStory() {
//	}
//
//	private TableViewStory(Table table) {
//		this.table = table;
//		updating = new TableUpdating();
//	}
//
//	@Override
//	public TableStory getStory(String name) {
//		return story.get(name);
//	}
//
//	@Override
//	public void setStory(String name, Table table) {
//		story.put(name, new TableViewStory(table));
//	}
//
//	@Override
//	public Table getTable() {
//		return table;
//	}
//
//	@Override
//	public Updating getUpdating() {
//		return updating;
//	}
//
//}
