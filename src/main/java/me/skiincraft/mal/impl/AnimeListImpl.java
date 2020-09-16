package me.skiincraft.mal.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import me.skiincraft.mal.MyAnimeList;
import me.skiincraft.mal.api.Request;
import me.skiincraft.mal.entity.list.AnimeItem;
import me.skiincraft.mal.entity.list.AnimeList;
import me.skiincraft.mal.entity.list.ListStatus;
import me.skiincraft.mal.entity.objects.MediaType;
import me.skiincraft.mal.entity.objects.Status;
import me.skiincraft.mal.entity.people.Profile;
import me.skiincraft.mal.util.SimpleUtil;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class AnimeListImpl implements AnimeList {
	 
	private List<AnimeItem> animeListeds;
	private ListStatus listStatus;
	private String user;
	private MyAnimeList mal;
	
	public AnimeListImpl(List<JsonArray> arrays, ListStatus status, String user, MyAnimeList mal) {
		animeListeds = new ArrayList<>();
		this.mal = mal;
		for (JsonArray array : arrays) {
			for (JsonElement ele : array) {
				JsonObject object = ele.getAsJsonObject();
				LocalDate start = null;
				LocalDate end = null;
				if (!object.get("anime_start_date_string").isJsonNull()) {
					start = SimpleUtil.parseLocalDate(object.get("anime_start_date_string").getAsString());
				}
				if (!object.get("anime_end_date_string").isJsonNull()) {
					end = SimpleUtil.parseLocalDate(object.get("anime_end_date_string").getAsString());
				}
				
				animeListeds.add(new AnimeItem(object.get("anime_title").getAsString(),ListStatus.getListStatus(object.get("status").getAsInt()),
						object.get("score").getAsInt(), object.get("tags").getAsString(),
						object.get("is_rewatching").getAsString().contains("1"), // boolean
						object.get("num_watched_episodes").getAsInt(), object.get("anime_num_episodes").getAsInt(),
						Status.getStatus("anime_airing_status"), object.get("anime_id").getAsLong(),
						object.get("anime_image_path").getAsString(),
						MediaType.getMediaType(object.get("anime_media_type_string").getAsString()),
						(object.get("anime_mpaa_rating_string").isJsonNull()) ? null : object.get("anime_mpaa_rating_string").getAsString(),
						start,
						end, mal));
			}
		}
		this.listStatus = status;
		this.user = user;
	}
	
	public Request<Profile> getUser() {
		return mal.getProfile(user);
	}

	public ListStatus getStatus() {
		return listStatus;
	}

	public List<AnimeItem> getAnimelist() {
		return animeListeds;
	}

}
