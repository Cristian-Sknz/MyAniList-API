package me.skiincraft.mal.entity.experimental.top;

import me.skiincraft.mal.MyAnimeList;

public enum TopType {

    Airing("Top Airing","?type=airing"),
    Upcoming("Top Upcoming","?type=upcoming"),
    TV_Series("Top TV Series","?type=tv"),
    Movies("Top Movies","?type=movie"),
    OVAs("Top OVAs","?type=ova"),
    ONAs("Top ONAs","?type=ona"),
    Special("Top Specials","?type=special"),
    Most_Popular("Most Popular","?type=bypopularity"),
    Most_Favorited("Most Favorited","?type=favorite");

    private static final String TOP_URL = MyAnimeList.SITE_URL + "topanime.php";
    private final String name;
    private final String endpoint;

    TopType(String name, String endpoint) {
        this.name = name;
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getURL() {
        return TOP_URL + endpoint;
    }

    public String getName() {
        return name;
    }
}
