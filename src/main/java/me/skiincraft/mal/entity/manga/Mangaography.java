package me.skiincraft.mal.entity.manga;

import java.util.List;

import me.skiincraft.mal.MyAnimeList;
import me.skiincraft.mal.api.Request;
import me.skiincraft.mal.entity.characters.Character;
import me.skiincraft.mal.util.By;

public class Mangaography {
	
	private List<Mangashort> mangas;
	private int size;
	
	public Mangaography(List<Mangashort> mangas) {
		this.mangas = mangas;
		this.size = mangas.size();
	}
	
	public List<Mangashort> getMangas() {
		return mangas;
	}
	
	public int getSize() {
		return size;
	}
	
	public String toString() {
		return "Mangaography [mangas=" + mangas + ", size=" + size + "]";
	}



	public static class Mangashort {
		
		private String name;
		private Character character;
		private long id;
		
		private Request<Manga> manga;
		
		String role;
		
		public Mangashort(String name, long id, Character character, String role, MyAnimeList mal) {
			this.name = name;
			this.id = id;
			this.character = character;
			this.role = role;
			this.manga = mal.getManga(By.id(id));
		}
		
		public long getId() {
			return id;
		}
		
		public Request<Manga> getManga() {
			return manga;
		}
		
		public String getName() {
			return name;
		}
		public Character getCharacter() {
			return character;
		}
		public String getRole() {
			return role;
		}

		public String toString() {
			return "Mangashort [name=" + name + ", character=" + character + ", id=" + id + ", role=" + role + "]";
		}
		
		
	}
	

}
