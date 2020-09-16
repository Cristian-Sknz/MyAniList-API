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

public class SearchManga extends Search {

	private static final String MANGA_URL = "https://myanimelist.net/manga.php?";
	
	public SearchManga(String queue) {
		super(new Consumer<List<SearchResult>>() {

			public void accept(List<SearchResult> t) {
				try {
					Document document = Jsoup.connect(MANGA_URL + "q=" + queue + "&cat=manga").get();
					Element ele = document.selectFirst("#content > div.js-categories-seasonal.js-block-list.list > table");
					
					List<Element> elementos = ele.getElementsByTag("tr").stream().filter(e -> e.toString().contains("https://myanimelist.net/manga")).collect(Collectors.toList());
					for (Element e: elementos) {
						Element element = e.getElementsByClass("hoverinfo_trigger fw-b").get(0);
						t.add(new SearchResult(element.text(), getId("/manga/",element.attr("href"))));
					}
				} catch (HttpStatusException e) {
					throw new SearchException("This manga you searched for doesn't exist, or it wasn't spelled correctly.");
				} catch (IOException io) {
					io.printStackTrace();
				}
			}
		}, queue);
	}
}
