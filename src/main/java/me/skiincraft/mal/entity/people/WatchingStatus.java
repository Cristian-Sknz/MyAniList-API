package me.skiincraft.mal.entity.people;

public enum WatchingStatus {
	
	Watching("Watching"),
	Completed("Completed"),
	On_Hold("On-Hold"),
	Dropped("Dropped"),
	Plan_to_Watch("Plan to Watch");

	private String name;

	WatchingStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
