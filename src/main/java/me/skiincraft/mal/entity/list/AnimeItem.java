package me.skiincraft.mal.entity.list;

import java.time.LocalDate;

import me.skiincraft.mal.MyAnimeList;
import me.skiincraft.mal.api.Request;
import me.skiincraft.mal.entity.anime.Anime;
import me.skiincraft.mal.entity.objects.MediaType;
import me.skiincraft.mal.entity.objects.Status;
import me.skiincraft.mal.util.By;

public class AnimeItem {
	
	private ListStatus status;
	private String animetitle;
	private int score;
	private String tags;
	private boolean rewatching;
	private int watchedEpisodes;
	private int maxEpisodes;
	private Status animeStatus;
	private long id;
	private String imagePreview;
	private MediaType mediaType;
	private String rating;
	private LocalDate startDate;
	private LocalDate finishDate;
	
	private Request<Anime> anime;
	
	public AnimeItem(String title, ListStatus status, int score, String tags, boolean rewatching, int watchedEpisodes,
			int maxEpisodes, Status animeStatus, long id, String imagePreview, MediaType mediaType, String rating,
			LocalDate startDate, LocalDate finishDate, MyAnimeList mal) {
		this.status = status;
		this.score = score;
		this.tags = tags;
		this.rewatching = rewatching;
		this.watchedEpisodes = watchedEpisodes;
		this.maxEpisodes = maxEpisodes;
		this.animeStatus = animeStatus;
		this.id = id;
		this.imagePreview = imagePreview;
		this.mediaType = mediaType;
		this.rating = rating;
		this.startDate = startDate;
		this.finishDate = finishDate;
		this.animetitle = title;
		this.anime = mal.getAnime(By.id(id));
	}

	public ListStatus getStatus() {
		return status;
	}

	public int getScore() {
		return score;
	}

	public String getTags() {
		return tags;
	}
	
	public Request<Anime> getAnime() {
		return anime;
	}

	public boolean isRewatching() {
		return rewatching;
	}

	public int getWatchedEpisodes() {
		return watchedEpisodes;
	}

	public int getMaxEpisodes() {
		return maxEpisodes;
	}

	public Status getAnimeStatus() {
		return animeStatus;
	}

	public long getId() {
		return id;
	}

	public String getImagePreview() {
		return imagePreview;
	}

	public MediaType getMediaType() {
		return mediaType;
	}

	public String getRating() {
		return rating;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getFinishDate() {
		return finishDate;
	}
	
	public String getTitle() {
		return animetitle;
	}

	public String toString() {
		return "AnimeItem [id=" + id +", anime=" + animetitle + ", maxEpisodes=" + maxEpisodes + ", watchedEpisodes=" + watchedEpisodes
				+ ", animeStatus=" + animeStatus + ", mediaType=" + mediaType + "]";
	}
	
	
}
