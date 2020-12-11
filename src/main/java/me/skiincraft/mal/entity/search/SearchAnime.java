package me.skiincraft.mal.entity.search;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import me.skiincraft.mal.util.ExtractElements;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import me.skiincraft.mal.entity.exception.SearchException;

public class SearchAnime extends Search {
	
	private static final String ANIME_URL = "https://myanimelist.net/anime.php?";	
	
	public SearchAnime(String queue) {
		super(t -> {
			try {
				Document document = Jsoup.connect(ANIME_URL + "q=" + queue + "&cat=anime").get();
				Element ele = document.selectFirst("#content > div.js-categories-seasonal.js-block-list.list > table");
				List<Element> elementos = ele.getElementsByTag("tr").stream().filter(e -> e.toString().contains("https://myanimelist.net/anime/")).collect(Collectors.toList());
				for (Element e: elementos) {
					Element element = e.getElementsByClass("hoverinfo_trigger fw-b fl-l").get(0);
					t.add(new SearchResult(element.text(), getId("/anime/",element.attr("href")), Objects.requireNonNull(ExtractElements.extractImage(e.selectFirst("img").attr("data-src"), ExtractElements.ANIME))));
				}
			} catch (HttpStatusException e) {
				throw new SearchException("This anime you searched for doesn't exist, or it wasn't spelled correctly.");
			} catch (IOException io) {
				io.printStackTrace();
			}
		}, queue);
	}

}
