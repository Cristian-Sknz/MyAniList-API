package me.skiincraft.mal;

import me.skiincraft.mal.api.Request;
import me.skiincraft.mal.entity.experimental.anime.AnimeShort;
import me.skiincraft.mal.entity.experimental.anime.TopAnime;
import me.skiincraft.mal.entity.experimental.top.TopType;
import me.skiincraft.mal.entity.search.SearchAnime;
import me.skiincraft.mal.impl.AnimeTopImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


public class ExperimentalMethods {

    public static void main(String[] args) {
        System.out.println(new SearchAnime("Monogatari").getFirst().getResultImage());
    }

    public Request<TopAnime> getTopAnime(TopType topType){
        return () -> {
            try {
                Document document = Jsoup.connect(topType.getURL()).get();
                Elements elements = document.getElementsByClass("ranking-list");
                List<AnimeShort> animes = new ArrayList<>();
                for (Element rankingListElement : elements){
                    animes.add(AnimeTopImpl.AnimeShort(rankingListElement));
                }
                return new TopAnime(animes, topType);
            } catch (Exception e){
                e.printStackTrace();
            }
            return null;
        };
    }
}
