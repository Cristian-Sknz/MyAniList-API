package me.skiincraft.mal.entity.objects;

import java.util.Arrays;

public enum Status {

	Airing(1), Finished(2), Not_aired(3);
	
	private int id;
	private Status(int i) {
		this.id = i;
	}
	
	public static Status getStatus(int i) {
		return Arrays.stream(values()).filter(o -> o.getId() == i).findAny().orElse(null);
	}
	
	public int getId() {
		return id;
	}
	
	public static Status getStatus(String string) {
		if (string.contains("Not yet aired")) {
			return Not_aired;
		}
		if (string.contains("Airing")) {
			return Airing;
		}
		
		return Finished; 
	}
	
}
