package me.skiincraft.mal.entity.people;

import me.skiincraft.mal.api.Request;
import me.skiincraft.mal.entity.anime.AnimeStatus;
import me.skiincraft.mal.entity.anime.AnimeUpdate;
import me.skiincraft.mal.entity.list.AnimeList;
import me.skiincraft.mal.entity.list.ListStatus;
import me.skiincraft.mal.entity.manga.MangaStatus;
import me.skiincraft.mal.entity.manga.MangaUpdate;

public interface Profile {
	
	String getName();
	String getDefaultAvatar();
	
	AnimeUpdate getAnimeUpdates();
	MangaUpdate getMangaUpdates();
	
	Request<AnimeList> getAnimeList();
	Request<AnimeList> getAnimeList(ListStatus status);
	
	AnimeStatus getAnimeStatus();
	MangaStatus getMangaStatus();
	
	long getForumPostsSize();
	long getReviewsSize();
	long getRecommendationsSize();
	long getBlogPostsSize();
	long getClubsSize();

	default String getUrl() {
		return "https://myanimelist.net/profile/" + getName();
	}
}
