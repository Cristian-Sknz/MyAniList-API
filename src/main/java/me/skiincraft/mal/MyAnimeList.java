package me.skiincraft.mal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;

import me.skiincraft.mal.impl.AnimeImpl;
import me.skiincraft.mal.impl.AnimeListImpl;
import me.skiincraft.mal.impl.CharacterImpl;
import me.skiincraft.mal.impl.MangaImpl;
import me.skiincraft.mal.impl.ProfileImpl;
import me.skiincraft.mal.util.By;
import me.skiincraft.mal.util.SimpleUtil;

import com.google.gson.JsonArray;

import me.skiincraft.mal.api.Request;
import me.skiincraft.mal.entity.anime.Anime;
import me.skiincraft.mal.entity.manga.Manga;
import me.skiincraft.mal.entity.people.Profile;
import me.skiincraft.mal.entity.search.SearchAnime;
import me.skiincraft.mal.entity.search.SearchCharacter;
import me.skiincraft.mal.entity.search.SearchManga;
import me.skiincraft.mal.entity.characters.Character;
import me.skiincraft.mal.entity.exception.SearchException;
import me.skiincraft.mal.entity.list.AnimeList;
import me.skiincraft.mal.entity.list.ListStatus;

public class MyAnimeList {
	
	private static final String SITE_URL = "https://myanimelist.net/";
	private final MyAnimeList mal;
	
	public MyAnimeList() {
		this.mal = this;
	}
	
	public Request<Anime> getAnime(By by){
		return new Request<Anime>() {
			private Anime anime; 
			public Anime get() {
				if (anime == null) {
					if (!by.isById()) {
						try {
							return anime = new AnimeImpl(Jsoup.connect(
									SITE_URL + "anime/" + new SearchAnime((String) by.get()).getFirst().getResultId())
									.get(), mal);
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						try {
							return anime = new AnimeImpl(Jsoup.connect(SITE_URL + "anime/" + ((long) by.get())).get(), mal);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				return anime;
			}
		};
	}
	
	public Request<Manga> getManga(By by){
		return new Request<Manga>() {
			private Manga manga; 
			public Manga get() {
				if (manga == null) {
					if (!by.isById()) {
						try {
							return manga = new MangaImpl(Jsoup.connect(
									SITE_URL + "manga/" + new SearchManga((String) by.get()).getFirst().getResultId())
									.get(), mal);
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						try {
							return manga = new MangaImpl(Jsoup.connect(SITE_URL + "manga/" + ((long) by.get())).get(), mal);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				return manga;
			}
		};
	}
	
	public Request<Character> getCharacter(By by){
		return new Request<Character>() {
			private Character character; 
			public Character get() {
				if (character == null) {
					if (!by.isById()) {
						try {
							return character = new CharacterImpl(Jsoup.connect(
									SITE_URL + "character/" + new SearchCharacter((String) by.get()).getFirst().getResultId())
									.get(), mal);
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						try {
							return character = new CharacterImpl(Jsoup.connect(SITE_URL + "character/" + ((long) by.get())).get(), mal);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				return character;
			}
		};
	}
	
	public Request<Profile> getProfile(String name){
		return new Request<Profile>() {
			private Profile character; 
			public Profile get() {
				if (character == null) {
					try {
						return character = new ProfileImpl(Jsoup.connect(SITE_URL + "profile/" + name).get(), mal);
					} catch (HttpStatusException e) {
						throw new SearchException("This profile you searched for doesn't exist, or it wasn't spelled correctly.");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				return character;
			}
		};
	}
	
	public Request<AnimeList> getAnimeList(String name, ListStatus status) {
		return new Request<AnimeList>() {
			private AnimeList animelist; 
			public AnimeList get() {
				try {
					if (animelist == null) {
						List<JsonArray> lista = new ArrayList<>();
						JsonArray array = SimpleUtil.getJsonConnection(AnimeList.RequestURL(name, 0, status))
								.getAsJsonArray();
						int arraysize = array.size();
						lista.add(array);
						int v = 300;
						while (arraysize == 300) {
							JsonArray array2 = SimpleUtil.getJsonConnection(AnimeList.RequestURL(name, v, status))
									.getAsJsonArray();
							arraysize = array2.size();
							lista.add(array2);
							v += 300;
						}

						return animelist = new AnimeListImpl(lista, status, name, mal);
					}
				} catch (IOException e) {
					throw new SearchException("This profile you searched for doesn't exist, or it wasn't spelled correctly.");
				}
				return animelist;

			}
		};
	}
}
