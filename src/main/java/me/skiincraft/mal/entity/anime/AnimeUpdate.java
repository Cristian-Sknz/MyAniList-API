package me.skiincraft.mal.entity.anime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.skiincraft.mal.MyAnimeList;
import me.skiincraft.mal.api.Request;
import me.skiincraft.mal.util.By;

public class AnimeUpdate {
	
	private List<UpdatedAnime> updatedAnimes;
	
	public AnimeUpdate(UpdatedAnime... animes) {
		updatedAnimes = new ArrayList<>(Arrays.asList(animes));
	}
	
	public List<UpdatedAnime> getUpdatedAnimes() {
		return updatedAnimes;
	}
	
	public int size() {
		return updatedAnimes.size();
	}
	
	public String toString() {
		return "AnimeUpdate [" + updatedAnimes + "]";
	}



	public static class UpdatedAnime {
		
		private String name;
		private long id;
		private int episodes;
		
		private Request<Anime> anime;
		
		public UpdatedAnime(String name, long id, int episodes, MyAnimeList mal) {
			this.name = name;
			this.id = id;
			this.episodes = episodes; 
			this.anime = mal.getAnime(By.id(id));
		}

		public String getName() {
			return name;
		}

		public long getId() {
			return id;
		}

		public int getEpisodes() {
			return episodes;
		}

		public Request<Anime> getAnime() {
			return anime;
		}

		public String toString() {
			return "UpdatedAnime [name=" + name + ", id=" + id + ", episodes=" + episodes + "]";
		}
		
	}
	
	
}