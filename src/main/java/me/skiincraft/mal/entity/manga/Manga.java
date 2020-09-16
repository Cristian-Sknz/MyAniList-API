package me.skiincraft.mal.entity.manga;

import java.util.List;

import me.skiincraft.mal.api.Request;
import me.skiincraft.mal.entity.characters.CharacterShort;
import me.skiincraft.mal.entity.objects.Statistics;
import me.skiincraft.mal.entity.objects.Titles;

public interface Manga {
	
	String getName();
	String getEnglishName();
	
	Titles getAlternativeTitles();
	
	MangaInformation getInformation();
	Statistics getStatistics();
	
	String getDefaultAvatar();
	long getId();
	
	float getScore();
	long getTotalVotes();
	
	long getRanked();
	long getPopularity();
	long getMembers();
	
	default String getMALPage() {
		return "https://myanimelist.net/manga/" + getId();
	}
	
	Request<List<CharacterShort>> getCharacters();
 
}
