package me.skiincraft.mal.entity.anime;

import java.util.List;

import me.skiincraft.mal.MyAnimeList;
import me.skiincraft.mal.api.Request;
import me.skiincraft.mal.entity.characters.Character;
import me.skiincraft.mal.util.By;

public class Animeography {
	
	private List<Animeshort> animes;
	private int size;
	
	public Animeography(List<Animeshort> animes) {
		this.animes = animes;
		this.size = animes.size();
	}
	
	public List<Animeshort> getAnimes() {
		return animes;
	}
	
	public int getSize() {
		return size;
	}
	
	
	public String toString() {
		return "Animeography [animes=" + animes + ", size=" + size + "]";
	}


	public static class Animeshort {
		
		private String name;
		private Character character;
		private long id;
		
		private Request<Anime> anime;
		
		String role;
		
		public Animeshort(String name, long id, Character character, String role, MyAnimeList mal) {
			this.name = name;
			this.id = id;
			this.character = character;
			this.role = role;
			this.anime = mal.getAnime(By.id(id));
		}
		
		public long getId() {
			return id;
		}
		
		public Request<Anime> getAnime() {
			return anime;
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
			return "Animeshort [name=" + name + ", character=" + character + ", id=" + id + ", anime=" + anime
					+ ", role=" + role + "]";
		}
		
		
	}

}
