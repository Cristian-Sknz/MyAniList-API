package me.skiincraft.mal.entity.requests;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import me.skiincraft.mal.MyAnimeList;
import me.skiincraft.mal.api.Request;
import me.skiincraft.mal.entity.characters.CharacterShort;

public class CharactersAnime implements Request<List<CharacterShort>> {

	private Document doc;
	private List<CharacterShort> chars;
	private MyAnimeList mal;
	public CharactersAnime(Document document, MyAnimeList mal) {
		this.doc = document;
		this.mal = mal;
	}

	public List<CharacterShort> get() {
		if (chars != null) {
			return chars;
		}
		Elements eles = doc.selectFirst("#content > table > tbody > tr > td:nth-child(2) > div.js-scrollfix-bottom-rel").select("table > tbody > tr");
		List<CharacterShort> characters = new ArrayList<>();
		eles.stream().filter(ele -> {
			Scanner scanner = new Scanner(ele.toString());
			while (scanner.hasNextLine()) {
				String next = scanner.nextLine();
				if (next.contains("https://myanimelist.net/character/")) {
					return true;
				}
			}
			return false;
		}).forEach(ele ->{
			Element cl = ele.getElementsByClass("picSurround").get(0);
			characters.add(new CharacterShort(cl.selectFirst("img").attr("href"),
					revertName(cl.selectFirst("img").attr("alt")),
					getId(cl.selectFirst("a").attr("href")),
					ele.selectFirst("small").text(), mal));
		});
		return this.chars = characters;
	}
	
	public long getId(String link) {
		String str = link.substring(link.indexOf("/character/")).substring("/character/".length());
		int index = (str.indexOf("/") == -1) ? 0 : str.indexOf("/");
		if (index != 0) {
			str = str.substring(0, index);
		}
		return Long.parseLong(str.replaceAll("\\D+", ""));
	}
	
	private String revertName(String string) {
		if (!string.contains(",")) {
			return string;
		}
		String[] splited = string.split(",");
		StringBuffer buffer = new StringBuffer();
		for (int i = splited.length-1; i >= 0; i--) {
			buffer.append(splited[i].replace(" ", "") + " ");
		}
		return buffer.toString().substring(0, buffer.length()-1);
	}

}
