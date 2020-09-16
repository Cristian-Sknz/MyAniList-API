package me.skiincraft.mal.entity.characters;

import me.skiincraft.mal.MyAnimeList;
import me.skiincraft.mal.api.Request;
import me.skiincraft.mal.util.By;

public class CharacterShort {
	
	private String imagepreview;
	private String name;
	private long id;
	private String role;
	private MyAnimeList mal;
	
	public CharacterShort(String imagepreview, String name, long id, String role, MyAnimeList mal) {
		this.imagepreview = imagepreview;
		this.name = name;
		this.id = id;
		this.role = role;
		this.mal = mal;
	}
	
	public String getImagepreview() {
		return imagepreview;
	}
	public String getName() {
		return name;
	}
	
	public Request<Character> getCharacter(){
		return mal.getCharacter(By.id(getId()));
	}
	
	public long getId() {
		return id;
	}
	public String getRole() {
		return role;
	}

	public String toString() {
		return "CharacterShort [name=" + name + ", id=" + id + ", role=" + role + "]";
	}
	
	
}
