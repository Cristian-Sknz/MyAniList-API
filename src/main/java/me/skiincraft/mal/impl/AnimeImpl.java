package me.skiincraft.mal.impl;

import me.skiincraft.mal.MyAnimeList;
import me.skiincraft.mal.api.Request;
import me.skiincraft.mal.entity.anime.Anime;
import me.skiincraft.mal.entity.anime.AnimeInformation;
import me.skiincraft.mal.entity.characters.CharacterShort;
import me.skiincraft.mal.entity.objects.*;
import me.skiincraft.mal.entity.requests.CharactersAnime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AnimeImpl implements Anime {
	
	private final Document doc;
	private final MyAnimeList mal;
	private Request<List<CharacterShort>> request;
	
	public AnimeImpl(Document document, MyAnimeList mal) {
		this.doc = document;
		this.mal = mal;
	}

	public String getName() {
		return doc.selectFirst("#contentWrapper > div:nth-child(1) >"
				+ " div > div.h1-title > div > h1").text();
	}

	public String getEnglishName() {
		return (getAlternativeTitles().getEnglish() != null)
				? getAlternativeTitles().getEnglish() : null;
	}

	public Titles getAlternativeTitles() {
		Element ele = doc.selectFirst("#content > table > tbody >"
				+ " tr > td.borderClass > div");
		
		Elements eles = ele.getElementsByClass("spaceit_pad");
		
		String english = null;
		String[] synonyms = null;
		String japonese = null;
		
		for (Element e: eles) {
			if (e.getElementsContainingText("English").size() != 0) {
				english = e.getElementsContainingText("English").get(0).ownText();
			}
			if (e.getElementsContainingText("Synonyms").size() != 0) {
				String s = e.getElementsContainingText("Synonyms").get(0).ownText();
				synonyms = s.split(",");
			}
			if (e.getElementsContainingText("Japanese").size() != 0) {
				japonese = e.getElementsContainingText("Japanese").get(0).ownText();
			}
		}
		return new Titles(english, japonese, synonyms);
	}

	public String[] getOpeningTheme() {
		Element ele = doc.selectFirst(
				"#content > table > tbody > tr > td:nth-child(2) >"
				+ " div.js-scrollfix-bottom-rel > table > tbody >"
				+ " tr:nth-child(2) > td > div.di-t > div:nth-child(1) >"
				+ " div.theme-songs.js-theme-songs.opnening");
		
		Elements clss = ele.getElementsByClass("theme-song");
		String[] themes = new String[clss.size()];
		int i = 0;
		for (Element e: clss) {
			themes[i] = e.ownText().substring(e.ownText().indexOf("\""));
			i++;
		}
		return themes;
	}

	public String[] getEndingTheme() {
		Element ele = doc.selectFirst(
				"#content > table > tbody > tr > td:nth-child(2) >"
				+ " div.js-scrollfix-bottom-rel > table > tbody >"
				+ " tr:nth-child(2) > td > div.di-t > div:nth-child(3) >"
				+ " div.theme-songs.js-theme-songs.ending");
		
		Elements clss = ele.getElementsByClass("theme-song");
		String[] themes = new String[clss.size()];
		int i = 0;
		for (Element e: clss) {
			themes[i] = e.ownText().substring(e.ownText().indexOf("\""));
			i++;
		}
		return themes;
	}

	public AnimeInformation getInformation() {
		Elements eles = doc.select("#content > table > tbody > tr > td.borderClass > div");
		String synopsis = doc.select("#content > table > tbody > tr > td:nth-child(2) > div.js-scrollfix-bottom-rel > table > tbody > tr:nth-child(1) > td > p").text();
		StringBuilder builder = new StringBuilder();
		int i = 1;
		for (Element e : eles.get(0).getElementsByTag("div")) {
 			if (e.text().length() == 0) {
 				continue;
 			}
 			if (i > 4) {
 				if (!e.text().contains(":")) {
 					continue;
 				}
 				if (e.select("span").toString().contains("itemprop")) {
 	 				builder.append(e.select("span").text()).append("\n");
 	 				continue;
 				}
 				builder.append(e.text()).append("\n");
 			} else {
 			i++;
 			}
		}
		boolean guard = false;
		Scanner scanner = new Scanner(builder.toString());
		List<String> tables = new ArrayList<>();
		while (scanner.hasNextLine()) {
			String next = scanner.nextLine();
			if (next.contains("Type:")) {
				guard = true;
			}
			if (!guard) {
				continue;
			}
			tables.add(next.split(":")[1].substring(1));
		}
		return new AnimeInformation(MediaType.valueOf(tables.get(0)), (rAll(tables.get(1)).length() == 0) ? 0 :
			Integer.parseInt(rAll(tables.get(1))),
			Status.getStatus(tables.get(2)),
			synopsis,
			tables.get(3),
			tables.get(4),
			Arrays.asList(tables.get(6).split(",")),
			Arrays.asList(tables.get(8).split(",")),
			Source.getSource(tables.get(9)),
			Genre.getGenres(tables.get(10)),
			tables.get(11),
			tables.get(12));
	}
	
	private String rAll(String string) {
		return string.replaceAll("\\D+", "");
	}

	public Statistics getStatistics() {
		return new Statistics(getScore(), getRanked(), getPopularity(), getMembers());
	}

	public String getDefaultAvatar() {
		Element ele = doc.getElementsByClass("borderClass").select("img").get(0);
		return ele.attr("data-src");
	}

	public long getId() {
		String str = doc.location().substring(doc.location().indexOf("/anime/")).substring("/anime/".length());
		int index = (!str.contains("/")) ? 0 : str.indexOf("/");
		if (index != 0) {
			str = str.substring(0, index);
		}
		return Long.parseLong(str.replaceAll("\\D+", ""));
	}

	public float getScore() {
		for (int i = 1; i <= 10; i++) {
			Elements elements = doc.getElementsByClass("score-label score-" + i);
			if (elements.size() != 0) {
				return Float.parseFloat(elements.get(0).text());
			}
		}
		return 0;
	}

	public long getTotalVotes() {
		Element ele = doc.getElementsByClass("fl-l score").get(0);
		String number = ele.attr("data-user").replaceAll("\\D+", "");
		return Long.parseLong((number.length() == 0) ? "0" : number);
	}

	public long getRanked() {
		Element ele = doc.getElementsByClass("numbers ranked").get(0);
		return (ele.text().contains("N/A") || ele.text().replaceAll("\\D+", "").length() == 0) ? 0
				: Long.parseLong(ele.text().replaceAll("\\D+", ""));
	}

	public long getPopularity() {
		Element ele = doc.getElementsByClass("numbers popularity").get(0);
		return (ele.text().contains("N/A") || ele.text().replaceAll("\\D+", "").length() == 0) ? 0
				: Long.parseLong(ele.text().replaceAll("\\D+", ""));
	}

	public long getMembers() {
		Element ele = doc.getElementsByClass("numbers members").get(0);
		return (ele.text().contains("N/A") || ele.text().replaceAll("\\D+", "").length() == 0) ? 0
				: Long.parseLong(ele.text().replaceAll("\\D+", ""));
	}

	public Request<List<CharacterShort>> getCharacters() {
		if (request != null) {
			return request;
		}
		Element ele = doc.selectFirst("#content > table > tbody > tr > td:nth-child(2) > div.js-scrollfix-bottom-rel > table > tbody > tr:nth-child(2) > td > div:nth-child(5) > div");
		try {
			return request = new CharactersAnime(Jsoup.connect("https://myanimelist.net/" + ele.selectFirst("a").attr("href")).get(), mal);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String toString() {
		return "Anime [name=" + getName() +  ", id=" + getId() + ", malpage=" + getMALPage() + "]";
	}

}
