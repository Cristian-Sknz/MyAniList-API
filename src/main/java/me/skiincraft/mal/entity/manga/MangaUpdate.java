package me.skiincraft.mal.entity.manga;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.skiincraft.mal.MyAnimeList;
import me.skiincraft.mal.api.Request;
import me.skiincraft.mal.util.By;

public class MangaUpdate {
	
	private List<UpdatedManga> updatedMangas;
	
	public MangaUpdate(UpdatedManga... animes) {
		updatedMangas = new ArrayList<>(Arrays.asList(animes));
	}
	
	public List<UpdatedManga> getUpdatedMangas() {
		return updatedMangas;
	}
	
	public int size() {
		return updatedMangas.size();
	}
	
	

	public String toString() {
		return "MangaUpdate [" + updatedMangas + "]";
	}



	public static class UpdatedManga {
		
		private String name;
		private long id;
		private int chapter;

		private String image;
		
		private Request<Manga> manga;
		
		public UpdatedManga(String name, long id, int chapter, String image, MyAnimeList mal) {
			this.name = name;
			this.id = id;
			this.chapter = chapter;
			this.image = image;
			this.manga = mal.getManga(By.id(id));
		}

		public String getName() {
			return name;
		}

		public long getId() {
			return id;
		}

		public String getImage() {
			return image;
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
