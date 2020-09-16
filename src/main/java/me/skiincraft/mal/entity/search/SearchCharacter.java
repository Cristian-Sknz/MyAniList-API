package me.skiincraft.mal.entity.search;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import me.skiincraft.mal.entity.exception.SearchException;

public class SearchCharacter extends Search {

	private static final String CHARACTER_URL = "https://myanimelist.net/character.php?";
	
	public SearchCharacter(String queue) {
		super(new Consumer<List<SearchResult>>() {

			public void accept(List<SearchResult> t) {
				try {
					Document document = Jsoup.connect(CHARACTER_URL + "q=" + queue + "&cat=character").get();
					Element ele = document.selectFirst("#content > table");
					List<Element> elementos = ele.getElementsByTag("tr").stream().filter(e -> e.toString().contains("https://myanimelist.net/character")).collect(Collectors.toList());
					for (Element e: elementos) {
						Element element = e.select("td").get(1).selectFirst("a");
						t.add(new SearchResult(element.text(), getId("/character/",element.attr("href"))));
					}
				} catch (HttpStatusException e) {
					throw new SearchException("This character you searched for doesn't exist, or it wasn't spelled correctly.");
				} catch (IOException io) {
					io.printStackTrace();
				}
			}
		}, queue);
	}
}
