package me.skiincraft.mal.entity.manga;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.skiincraft.mal.MyAnimeList;
import me.skiincraft.mal.api.Request;
import me.skiincraft.mal.util.By;

public class MangaUpdate {
	
	private List<UpdatedManga> updatedAnimes;
	
	public MangaUpdate(UpdatedManga... animes) {
		updatedAnimes = new ArrayList<>(Arrays.asList(animes));
	}
	
	public List<UpdatedManga> getUpdatedAnimes() {
		return updatedAnimes;
	}
	
	public int size() {
		return updatedAnimes.size();
	}
	
	

	public String toString() {
		return "MangaUpdate [" + updatedAnimes + "]";
	}



	public static class UpdatedManga {
		
		private String name;
		private long id;
		private int chapter;
		
		private Request<Manga> manga;
		
		public UpdatedManga(String name, long id, int chapter, MyAnimeList mal) {
			this.name = name;
			this.id = id;
			this.chapter = chapter;
			manga = mal.getManga(By.id(id));
		}

		public String getName() {
			return name;
		}

		public long getId() {
			return id;
		}

		public int getEpisodes() {
			return chapter;
		}

		public Request<Manga> getManga() {
			return manga;
		}

		public String toString() {
			return "UpdatedManga [name=" + name + ", id=" + id + ", chapter=" + chapter + "]";
		}
	}
	
	
}
