package me.skiincraft.mal.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import me.skiincraft.mal.MyAnimeList;
import me.skiincraft.mal.entity.anime.Animeography;
import me.skiincraft.mal.entity.anime.Animeography.Animeshort;
import me.skiincraft.mal.entity.characters.Character;
import me.skiincraft.mal.entity.manga.Mangaography;
import me.skiincraft.mal.entity.manga.Mangaography.Mangashort;

public class CharacterImpl implements Character {
	
	private Document doc;
	private Animeography animeography;
	private Mangaography mangaography;
	private MyAnimeList mal;
	
	public CharacterImpl(Document document, MyAnimeList mal) {
		this.doc = document;
		this.mal = mal;
	}

	public String getName() {
		return doc.getElementsByClass("normal_header").get(2).ownText();
	}

	public String getTitle() {
		return doc.getElementsByClass("title-name").get(0).text();
	}

	public String getDefaultAvatar() {
		return doc.selectFirst("#content > table > tbody > tr > td.borderClass > div:nth-child(1) > a > img").attr("data-src");
	}

	public String getDescription() {
		Element element = doc.selectFirst("#content > table > tbody > tr > td:nth-child(2)").clone();
		element.getElementsByTag("div").remove();
		element.getElementsByClass("normal_header").remove();
		element.getElementsByTag("table").remove();
		
		return element.text();
	}

	public Animeography getAnimeography() {
		if (animeography != null) {
			return animeography;
		}
		Element element = doc.selectFirst("#content > table > tbody > tr > td.borderClass");
		Elements tables = element.getElementsByTag("table");
		if (tables.size() != 2) {
			return new Animeography(Collections.emptyList());
		}
		Elements tr = tables.get(0).getElementsByTag("tr");
		List<Animeshort> animeshorts = new ArrayList<>();
		for (Element anime : tr) {
			String link = anime.getElementsByClass("borderClass").get(1).selectFirst("a").attr("href");
			link = link.substring(link.indexOf("/anime/")).replace("/anime/", "");
			
			long id = Long.parseLong(link.substring(0, link.indexOf("/")));
			animeshorts.add(new Animeshort(anime.getElementsByClass("borderClass").get(1).selectFirst("a").text(),
					id, this, anime.getElementsByTag("small").text(), mal));
		}
		
		return animeography = new Animeography(animeshorts);
	}

	public Mangaography getMangaography() {
		if (mangaography != null) {
			return mangaography;
		}
		Element element = doc.selectFirst("#content > table > tbody > tr > td.borderClass");
		Elements tables = element.getElementsByTag("table");
		Elements tr = tables.get(1).getElementsByTag("tr");
		if (tables.size() != 2) {
			tr = tables.get(0).getElementsByTag("tr");
		}

		List<Mangashort> mangahorts = new ArrayList<>();
		for (Element manga : tr) {
			String link = manga.getElementsByClass("borderClass").get(1).selectFirst("a").attr("href");
			link = link.substring(link.indexOf("/manga/")).replace("/manga/", "");
			
			long id = Long.parseLong(link.substring(0, link.indexOf("/")));
			mangahorts.add(new Mangashort(manga.getElementsByClass("borderClass").get(1).selectFirst("a").text(),
					id, this, manga.getElementsByTag("small").text(), mal));
		}
		
		return mangaography = new Mangaography(mangahorts);
	}

	public long getId() {
		String str = doc.location().substring(doc.location().indexOf("/character/")).substring("/character/".length());
		int index = (str.indexOf("/") == -1) ? 0 : str.indexOf("/");
		if (index != 0) {
			str = str.substring(0, index);
		}
		return Long.parseLong(str.replaceAll("\\D+", ""));
	}

	public long getFavorites() {
		Scanner scanner = new Scanner(doc.selectFirst("#content > table > tbody > tr > td.borderClass").toString());
		while (scanner.hasNextLine()) {
			String next = scanner.nextLine();
			if (next.contains("Member Favorites:")) {
				return Long.parseLong(next.replaceAll("\\D+", ""));
			} 
		}
		return 0;
	}

}
