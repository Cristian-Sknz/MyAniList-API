package me.skiincraft.mal.entity.list;

import java.util.List;

import me.skiincraft.mal.api.Request;
import me.skiincraft.mal.entity.people.Profile;

public interface AnimeList {
	
	Request<Profile> getUser();
	ListStatus getStatus();
	List<AnimeItem> getAnimelist();

	public static String RequestURL(String username, int offset, ListStatus status) {
		return "https://myanimelist.net/animelist/" + username + "/load.json?offset=" + offset + "&status=" + status.getId();
	}
}
