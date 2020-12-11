package me.skiincraft.mal.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.skiincraft.mal.entity.people.WatchingStatus;
import me.skiincraft.mal.util.ExtractElements;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import me.skiincraft.mal.MyAnimeList;
import me.skiincraft.mal.api.Request;
import me.skiincraft.mal.entity.anime.AnimeStatus;
import me.skiincraft.mal.entity.anime.AnimeUpdate;
import me.skiincraft.mal.entity.anime.AnimeUpdate.UpdatedAnime;
import me.skiincraft.mal.entity.list.AnimeList;
import me.skiincraft.mal.entity.list.ListStatus;
import me.skiincraft.mal.entity.manga.MangaStatus;
import me.skiincraft.mal.entity.manga.MangaUpdate;
import me.skiincraft.mal.entity.manga.MangaUpdate.UpdatedManga;
import me.skiincraft.mal.entity.people.Profile;

public class ProfileImpl implements Profile {
	
	private Document document;
	private MyAnimeList mal;
	
	public ProfileImpl(Document document, MyAnimeList mal) {
		this.document = document;
		this.mal = mal;
		userstats();
	}

	public String getName() {
		return document.getElementsByClass("h1").get(0).selectFirst("span").text().replace("'s Profile", "");
	}

	public String getDefaultAvatar() {
		return document.getElementsByClass("user-profile").get(0).selectFirst("img").attr("data-src");
	}

	public WatchingStatus containsStatus(String string){
		return Arrays.stream(WatchingStatus.values())
				.filter(watchingStatus -> string.toLowerCase().contains(watchingStatus.getName().toLowerCase()))
				.findFirst().orElse(WatchingStatus.Watching);
	}

	public AnimeUpdate getAnimeUpdates() {
		Elements allelements = document.getElementsByClass("updates anime").get(0).getElementsByClass("statistics-updates di-b w100 mb8");
		UpdatedAnime[] animes = new UpdatedAnime[allelements.size()];
		int i = 0;
		for (Element element: allelements) {
			Element data = element.getElementsByClass("data").get(0);
			Element e = data.selectFirst("a");

			String image = element.selectFirst("img").attr("data-src");

			UpdatedAnime anime = new UpdatedAnime(e.text(),
					getId("/anime/", e.attr("href")),
					Integer.parseInt((data.select("span").size() <= 2) ? "0" : data.select("span").get(2).text()),
					ExtractElements.extractImage(image, ExtractElements.ANIME),
					containsStatus(data.text()),
					mal);

			animes[i] = anime;
			i++;
		}
		return new AnimeUpdate(animes);
	}
	
	private static long getId(String substring, String url) {
		String str = url.substring(url.indexOf(substring)).substring(substring.length());
		int index = (!str.contains("/")) ? 0 : str.indexOf("/");
		if (index != 0) {
			str = str.substring(0, index);
		}
		return Long.parseLong(str.replaceAll("\\D+", ""));
	}

	public MangaUpdate getMangaUpdates() {
		/*Elements elements = document.getElementsByClass("updates manga").get(0).getElementsByClass("data");
		UpdatedManga[] animes = new UpdatedManga[elements.size()];
		int i = 0;
		for (Element element: elements) {
			Element e = element.selectFirst("a");
			UpdatedManga anime = new UpdatedManga(e.text(),
					getId("/manga/", e.attr("href")),
					Integer.parseInt((element.select("span").size() <= 2) ? "0" : element.select("span").get(2).text()),
					mal);
			animes[i] = anime;
			i++;
		}
		return new MangaUpdate(animes);
*/
		Elements allelements = document.getElementsByClass("updates manga").get(0).getElementsByClass("statistics-updates di-b w100 mb8");
		UpdatedManga[] mangas = new UpdatedManga[allelements.size()];
		int i = 0;
		for (Element element: allelements) {
			Element data = element.getElementsByClass("data").get(0);
			Element e = data.selectFirst("a");

			String image = element.selectFirst("img").attr("data-src");

			UpdatedManga manga = new UpdatedManga(e.text(),
					getId("/manga/", e.attr("href")),
					Integer.parseInt((element.select("span").size() <= 2) ? "0" : element.select("span").get(2).text()),
					ExtractElements.extractImage(image, ExtractElements.MANGA),
					mal);

			mangas[i] = manga;
			i++;
		}
		return new MangaUpdate(mangas);
	}


	
	public AnimeStatus getAnimeStatus() {
		Element stats = document.getElementsByClass("stats anime").get(0);
		Elements s = stats.select("li > span");
		
		Element day = stats.selectFirst("div > div > div");
		float days = Float.parseFloat(day.text().replace("Days: ", ""));
		
		Element sco = stats.select("div > div > div > span").get(2);
		float score = Float.parseFloat(sco.text());
		Number[] statsarray = status(s); 
		

		return new AnimeStatus(statsarray[1].intValue(), statsarray[0].intValue(), statsarray[2].intValue(),
				statsarray[3].intValue(), statsarray[4].intValue(), statsarray[5].intValue(), statsarray[7].intValue(),
				days, score);
	}
	
	private Number[] status(Elements status) {
		List<Integer> stats = new ArrayList<>();
		for (Element element : status) {
			if (element.className().contains("fl-r")) {
				stats.add(Integer.parseInt(element.text().replaceAll("\\D+", "")));
			}
		}
		
		return stats.toArray(new Number[0]);
	}

	public MangaStatus getMangaStatus() {
		Element stats = document.getElementsByClass("stats manga").get(0);
		Elements s = stats.select("li > span");
		
		Element day = stats.selectFirst("div > div > div");
		float days = Float.parseFloat(day.text().replace("Days: ", ""));
		
		Element sco = stats.select("div > div > div > span").get(2);
		float score = Float.parseFloat(sco.text());
		Number[] statsarray = status(s); 
		

		return new MangaStatus(statsarray[1].intValue(), statsarray[0].intValue(), statsarray[2].intValue(), statsarray[3].intValue(), statsarray[4].intValue(), statsarray[5].intValue(), statsarray[3].longValue(), statsarray[3].longValue(), days, score);
	}
	
	private Number[] uservalues;
	
	private Number[] userstats() {
		if (uservalues != null) {
			return uservalues;
		}
		List<Integer> stats = new ArrayList<>();
		for (Element element : document.getElementsByClass("user-profile").get(0).getElementsByClass("link").select("span")) {
			if (element.className().contains("fl-r")) {
				stats.add(Integer.parseInt(element.text().replaceAll("\\D+", "")));
			}
		}
		
		return uservalues = stats.toArray(new Number[0]);
	}

	public long getForumPostsSize() {
		return uservalues[0].intValue();
	}

	public long getReviewsSize() {
		return uservalues[1].intValue();
	}

	public long getRecommendationsSize() {
		return uservalues[2].intValue();
	}

	public long getBlogPostsSize() {
		return uservalues[3].intValue();
	}

	public long getClubsSize() {
		return uservalues[4].intValue();
	}
	public Request<AnimeList> getAnimeList(ListStatus status) {
		return mal.getAnimeList(getName(), status);
	}
	public Request<AnimeList> getAnimeList() {
		return mal.getAnimeList(getName(), ListStatus.ALL);
	}

}
