package me.skiincraft.mal.entity.anime;

import java.util.List;

import me.skiincraft.mal.api.Request;
import me.skiincraft.mal.entity.characters.CharacterShort;
import me.skiincraft.mal.entity.objects.Statistics;
import me.skiincraft.mal.entity.objects.Titles;

public interface Anime {
	
	String getName();
	String getEnglishName();
	
	Titles getAlternativeTitles();
	String[] getOpeningTheme();
	String[] getEndingTheme();
	
	AnimeInformation getInformation();
	Statistics getStatistics();
	
	String getDefaultAvatar();
	long getId();
	
	float getScore();
	long getTotalVotes();
	
	long getRanked();
	long getPopularity();
	long getMembers();
	
	default String getMALPage() {
		return "https://myanimelist.net/anime/" + getId();
	}
	
	Request<List<CharacterShort>> getCharacters();
 
}
