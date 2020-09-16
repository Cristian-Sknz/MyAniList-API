package me.skiincraft.mal.util;

public class By {
	
	private long id;
	private String search;
	
	public By(String search) {
		this.search = search;
	}
	
	public By(long id) {
		this.id = id;
	}
	
	public boolean isById() {
		return search == null;
	}
	
	public Object get() {
		return (isById()) ? id : search;
	}
	
	public static By search(String search) {
		return new By(search);
	}
	
	public static By id(long id) {
		return new By(id);
	}

}
