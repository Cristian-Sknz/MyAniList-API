package me.skiincraft.mal.entity.list;

import java.util.Arrays;

public enum ListStatus {
	
	ALL(7, "All"), Completed(2, "Completed"), Watching(1, "Currently Watching"), On_Hold(3, "On Hold"), Dropped(4, "Dropped"), Plan_To_Watch(6, "Plan to Watch");

	private int id;
	private String name;
	
	ListStatus(int i, String string) {
		this.id = i;
		this.name = string;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public static ListStatus getListStatus(int id) {
		return Arrays.stream(values()).filter(i -> i.getId() == id).findAny().orElse(null);
	}

}
