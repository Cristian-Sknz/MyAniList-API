package me.skiincraft.mal.entity.experimental.anime;

import me.skiincraft.mal.entity.objects.MediaType;

public class AnimeShort {

    private final String name;
    private final long id;
    private final MediaType type;

    private final int episodes;
    private final String[] dates;
    private final long members;
    private final float score;

    private final String imageURL;

    public AnimeShort(String name, long id, MediaType type, int episodes, String[] dates, long members, float score, String imageURL) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.episodes = episodes;
        this.dates = dates;
        this.members = members;
        this.score = score;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public MediaType getType() {
        return type;
    }

    public String[] getDates() {
        return dates;
    }

    public long getMembers() {
        return members;
    }

    public float getScore() {
        return score;
    }

    public String getImageURL() {
        return imageURL;
    }

    public int getEpisodes() {
        return episodes;
    }
}
