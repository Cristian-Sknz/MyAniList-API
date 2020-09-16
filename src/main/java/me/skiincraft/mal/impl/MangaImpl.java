package me.skiincraft.mal.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import me.skiincraft.mal.MyAnimeList;
import me.skiincraft.mal.api.Request;
import me.skiincraft.mal.entity.characters.CharacterShort;
import me.skiincraft.mal.entity.manga.Manga;
import me.skiincraft.mal.entity.manga.MangaInformation;
import me.skiincraft.mal.entity.objects.Genre;
import me.skiincraft.mal.entity.objects.Statistics;
import me.skiincraft.mal.entity.objects.Status;
import me.skiincraft.mal.entity.objects.Titles;
import me.skiincraft.mal.entity.objects.MediaType;
import me.skiincraft.mal.entity.requests.CharactersAnime;

public class MangaImpl implements Manga {
	
	private Document doc;
	private Request<List<CharacterShort>> request;
	private MyAnimeList mal;
	
	public MangaImpl(Document document, MyAnimeList mal) {
		this.doc = document;
		this.mal = mal;
	}

	public String getName() {
		Elements ele = doc.select("#contentWrapper > div:nth-child(1) > h1 > span > span > span");
		if (ele.size() != 0) {
			ele.remove();
		}
		return doc.selectFirst("#contentWrapper > div:nth-child(1) > h1 > span > span").text();
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

	public MangaInformation getInformation() {
		Elements eles = doc.select("#content > table > tbody > tr > td.borderClass > div");
		String synopsis = doc.select("#content > table > tbody > tr > td:nth-child(2) > div.js-scrollfix-bottom-rel > table > tbody > tr:nth-child(1) > td > p").text();
		StringBuffer buffer = new StringBuffer();
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
 	 				buffer.append(e.select("span").text()+ "\n");
 	 				continue;
 				}
 				if (e.select("a").toString().contains("/people/")) {
 					int o = 1;
 					buffer.append("Authors: ");
 					for (Element authors : e.select("a")) {
 						buffer.append(authors.text() + ";");
 						if (o == e.select("a").size()) {
 							buffer.append("\n");
 						}
 						o++;
 					}
 					continue;
 					
 				}
 				
 				buffer.append(e.text() + "\n");
 			} else {
 			i++;
 			}
		}
		boolean guard = false;
		Scanner scanner = new Scanner(buffer.toString());
		List<String> tables = new ArrayList<>();
		while (scanner.hasNextLine()) {
			String next = scanner.nextLine();
			if (next.contains("Type:")) {
				guard = true;
			}
			if (guard == false) {
				continue;
			}
			System.out.println(next);
			tables.add(next.split(":")[1].substring(1));
		}
		return new MangaInformation(MediaType.valueOf(tables.get(0)),
				synopsis,
				parseAll(tables.get(1)),
				parseAll(tables.get(2)),
				Status.getStatus(tables.get(3)),
				tables.get(4), Genre.getGenres(tables.get(5)), Arrays.asList(tables.get(6).split(";")));
	}
	
	private String rAll(String string) {
		return string.replaceAll("\\D+", "");
	}
	private int parseAll(String string) {
		String replaced = string.replaceAll("\\D+", "");
		return (replaced.length() == 0) ? 0 : Integer.parseInt(replaced);
	}

	public Statistics getStatistics() {
		return new Statistics(getScore(), getRanked(), getPopularity(), getMembers());
	}

	public String getDefaultAvatar() {
		Element ele = doc.selectFirst("#content > table > tbody > tr >"
				+ " td.borderClass > div > div:nth-child(1) > a > img");
		return ele.attr("data-src");
	}

	public long getId() {
		String str = doc.location().substring(doc.location().indexOf("/manga/")).substring("/manga/".length());
		int index = (str.indexOf("/") == -1) ? 0 : str.indexOf("/");
		if (index != 0) {
			str = str.substring(0, index);
		}
		return Long.parseLong(str.replaceAll("\\D+", ""));
	}

	public float getScore() {
		Element ele = doc.selectFirst("#content > table > tbody > tr > td:nth-child(2) > div.js-scrollfix-bottom-rel > table > tbody > tr:nth-child(1) > td > div.pb24 > div.di-t.w100.mt12 > div > div.stats-block.po-r.clearfix > div.fl-l.score > div");
		
		String number = ele.text().replaceAll("N/A", "0");
		return Float.parseFloat(number);
	}

	public long getTotalVotes() {
		Element ele = doc.selectFirst("#content > table > tbody > tr > td:nth-child(2) > div.js-scrollfix-bottom-rel > table > tbody > tr:nth-child(1) > td > div.pb24 > div.di-t.w100.mt12 > div > div.stats-block.po-r.clearfix > div.fl-l.score");
		String number = ele.attr("data-user").replaceAll("\\D+", "");
		return Long.parseLong((number.length() == 0) ? "0" : number);
	}

	public long getRanked() {
		Element ele = doc.selectFirst(
				"#content > table > tbody > tr > td:nth-child(2) > div.js-scrollfix-bottom-rel > table > tbody > tr:nth-child(1) > td > div.pb24 > div.di-t.w100.mt12 > div > div.stats-block.po-r.clearfix > div.po-a.di-ib.ml12.pl20.pt8 > span.numbers.ranked > strong");
		
		return (ele.text().contains("N/A") || ele.text().replaceAll("\\D+", "").length() == 0) ? 0
				: Long.parseLong(rAll(ele.text()));
	}

	public long getPopularity() {
		Element ele = doc.selectFirst("#content > table > tbody > tr > td:nth-child(2) > div.js-scrollfix-bottom-rel > table > tbody > tr:nth-child(1) > td > div.pb24 > div.di-t.w100.mt12 > div > div.stats-block.po-r.clearfix > div.po-a.di-ib.ml12.pl20.pt8 > span.numbers.popularity > strong");
		
		return Long.parseLong(ele.text().replaceAll("\\D+", ""));
	}

	public long getMembers() {
		Element ele = doc.selectFirst("#content > table > tbody > tr > td:nth-child(2) > div.js-scrollfix-bottom-rel > table > tbody > tr:nth-child(1) > td > div.pb24 > div.di-t.w100.mt12 > div > div.stats-block.po-r.clearfix > div.po-a.di-ib.ml12.pl20.pt8 > span.numbers.members > strong");
		
		return Long.parseLong(ele.text().replaceAll("\\D+", ""));
	}

	public Request<List<CharacterShort>> getCharacters() {
		if (request != null) {
			return request;
		}
		Element ele = doc.selectFirst("#content > table > tbody > tr > td:nth-child(2) > div.js-scrollfix-bottom-rel > table > tbody > tr:nth-child(2) > td > h2:nth-child(5) > div");
		try {
			return request = new CharactersAnime(Jsoup.connect("https://myanimelist.net/" + ele.selectFirst("a").attr("href")).get(), mal);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String toString() {
		return "Manga [name=" + getName() +  ", id=" + getId() + ", malpage=" + getMALPage() + "]";
	}

}
