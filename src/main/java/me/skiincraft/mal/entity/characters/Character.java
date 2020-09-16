package me.skiincraft.mal.entity.characters;

import me.skiincraft.mal.entity.anime.Animeography;
import me.skiincraft.mal.entity.manga.Mangaography;

public interface Character {
	
	String getName();
	String getTitle();
	
	String getDefaultAvatar();
	String getDescription();
	
	Animeography getAnimeography();
	Mangaography getMangaography();
	
	default boolean containsAnime() {
		return getAnimeography() != null;
	}
	
	default boolean containsManga() {
		return getMangaography() != null;
	}
	
	default String getMALPage() {
		return "https://myanimelist.net/character/" + getId();
	}
	
	long getId();
	long getFavorites();
	

}
