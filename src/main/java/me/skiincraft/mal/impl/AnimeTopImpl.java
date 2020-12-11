package me.skiincraft.mal.impl;

import me.skiincraft.mal.entity.experimental.anime.AnimeShort;
import me.skiincraft.mal.entity.objects.MediaType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.util.Arrays;
import java.util.List;

public class AnimeTopImpl extends AnimeShort {

    private AnimeTopImpl(String name, long id, MediaType type, int episodes, String[] dates, long members, float score, String imageURL) {
        super(name, id, type, episodes, dates, members, score, imageURL);
    }

    public static AnimeShort AnimeShort(Element rankingListElement) {
        Element detail = rankingListElement.getElementsByClass("detail").get(0);
        Element link = detail.select("a").get(0);
        List<TextNode> information = detail.getElementsByClass("information di-ib mt4").get(0).textNodes();
        String line1 = information.get(0).text().replaceAll("\n", "");

        return new AnimeTopImpl(detail.select("a").get(0).text(),
                extractId(link.attr("href")),
                extractMediaType(line1),
                extractEpisodes(line1),
                extractDates(information.get(1).text()),
                Long.parseLong(information.get(2).text().replaceAll("\\D+", "")),
                extractScore(rankingListElement.getElementsByClass("score ac fs14")),
                extractImage(rankingListElement.select("img").get(0).attr("data-src")));
    }

    private static float extractScore(Elements scoreAcFs14) {
        String str = scoreAcFs14.get(0).select("span").get(0).text();
        return (str.length() <= 2 || str.contains("N/A")) ? -1F : Float.parseFloat(str);
    }

    private static String[] extractDates(String string){
        if (string.length() <= 3) {
            return new String[] { null, null };
        }
        String[] dates = string.split("-");
        return new String[] {dates[0].substring(1), (dates[1].length() <= 2) ? null : dates[1].substring(1)};
    }

    private static int extractEpisodes(String string){
        String episodes = string.replaceAll("\\D+", "");
        if (episodes.length() != 0){
            return Integer.parseInt(episodes);
        }
        return -1;
    }

    private static MediaType extractMediaType(String nodeString) {
        return Arrays.stream(MediaType.values()).filter(type -> nodeString.toLowerCase().contains(type.name().toLowerCase())).findFirst().orElse(MediaType.TV);
    }

    public static String extractImage(String datasrc){
        String str = datasrc.substring(datasrc.indexOf("/anime/")).substring("/anime/".length());
        String ids = str.substring(0, str.lastIndexOf("."));
        return String.format("https://cdn.myanimelist.net/images/anime/%s.jpg", ids);
    }

    private static long extractId(String linkAnime){
        String str = linkAnime.substring(linkAnime.indexOf("/anime/")).substring("/anime/".length());
        int index = (!str.contains("/")) ? 0 : str.indexOf("/");
        if (index != 0) {
            str = str.substring(0, index);
        }
        return Long.parseLong(str.replaceAll("\\D+", ""));
    }
}
