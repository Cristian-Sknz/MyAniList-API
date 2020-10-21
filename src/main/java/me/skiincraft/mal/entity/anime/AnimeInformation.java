package me.skiincraft.mal.entity.anime;

import java.util.List;

import me.skiincraft.mal.entity.objects.Genre;
import me.skiincraft.mal.entity.objects.Source;
import me.skiincraft.mal.entity.objects.Status;
import me.skiincraft.mal.entity.objects.MediaType;
import me.skiincraft.mal.util.SimpleUtil;

public class AnimeInformation {
	
	private MediaType type;
	private int episodes;
	private Status status;
	
	private String synopsis;
	
	private String aired;
	private String premiered;
	
	private List<String> producers;
	private List<String> studios;
	private Source source;
	
	private List<Genre> genres;
	private String duration;
	private String rating;
	
	
	
	public AnimeInformation(MediaType type, int episodes, Status status, String synopsis,String aired, String premiered,
			List<String> producers, List<String> studios, Source source, List<Genre> genres, String duration,
			String rating) {
		this.type = type;
		this.episodes = episodes;
		this.status = status;
		this.synopsis = synopsis;
		this.aired = aired;
		this.premiered = premiered;
		this.producers = producers;
		this.studios = studios;
		this.source = source;
		this.genres = genres;
		this.duration = duration;
		this.rating = rating;
	}
	
	public MediaType getType() {
		return type;
	}
	public int getEpisodes() {
		return episodes;
	}
	public Status getStatus() {
		return status;
	}
	public String getAired() {
		return SimpleUtil.convertAired(aired);
	}
	public String getPremiered() {
		return premiered;
	}
	public List<String> getProducers() {
		return producers;
	}
	public List<String> getStudios() {
		return studios;
	}
	public Source getSource() {
		return source;
	}
	public List<Genre> getGenres() {
		return genres;
	}
	public String getDuration() {
		return duration;
	}
	public String getRating() {
		return rating;
	}
	
	public String getSynopsis() {
		return synopsis;
	}

	public String toString() {
		return "AnimeInformation [type=" + type + ", episodes=" + episodes + ", status=" + status + ", synopsis="
				+ synopsis + ", aired=" + aired + ", premiered=" + premiered + ", producers=" + producers + ", studios="
				+ studios + ", source=" + source + ", genres=" + genres + ", duration=" + duration + ", rating="
				+ rating + "]";
	}
	
	
}
