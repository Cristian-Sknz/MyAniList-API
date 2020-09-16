package me.skiincraft.mal.entity.manga;

import java.util.List;

import me.skiincraft.mal.entity.objects.Genre;
import me.skiincraft.mal.entity.objects.Status;
import me.skiincraft.mal.entity.objects.MediaType;

public class MangaInformation {
	
	private MediaType type;
	
	private String synopsis;
	private int volumes;
	private int chapters;
	
	private Status status;
	
	private String published;
	
	private List<Genre> genres;
	private List<String> authors;
	
	public MangaInformation(MediaType type, String synopsis, int volumes, int chapters, Status status, String published, List<Genre> genres,
			List<String> authors) {
		this.type = type;
		this.synopsis = synopsis;
		this.volumes = volumes;
		this.chapters = chapters;
		this.status = status;
		this.published = published;
		this.genres = genres;
		this.authors = authors;
	}
	
	public String getSynopsis() {
		return synopsis;
	}
	
	public MediaType getType() {
		return type;
	}
	public int getVolumes() {
		return volumes;
	}
	public int getChapters() {
		return chapters;
	}
	public Status getStatus() {
		return status;
	}
	public String getPublished() {
		return published;
	}
	public List<Genre> getGenres() {
		return genres;
	}
	public List<String> getAuthors() {
		return authors;
	}

	public String toString() {
		return "MangaInformation [type=" + type + ", synopsis=" + synopsis + ", volumes=" + volumes + ", chapters="
				+ chapters + ", status=" + status + ", published=" + published + ", genres=" + genres + ", authors="
				+ authors + "]";
	}
}
