package me.skiincraft.mal.entity.experimental.anime;

import me.skiincraft.mal.entity.experimental.top.Top;
import me.skiincraft.mal.entity.experimental.top.TopType;

import java.util.ArrayList;
import java.util.List;

public class TopAnime implements Top<AnimeShort> {

    private List<AnimeShort> animeShorts;
    private TopType type;

    public TopAnime(List<AnimeShort> animes, TopType type){
        this.animeShorts = animes;
        this.type = type;
    }

    @Override
    public AnimeShort get(int index) {
        return animeShorts.get(index);
    }

    @Override
    public List<AnimeShort> toList() {
        return new ArrayList<>(animeShorts);
    }

    @Override
    public String getURL() {
        return getType().getURL();
    }

    @Override
    public TopType getType() {
        return type;
    }

    @Override
    public int size() {
        return animeShorts.size();
    }

    @Override
    public String toString() {
        return "TopAnime{" +
                "animeShorts=" + animeShorts.size() +
                ", type=" + type +
                '}';
    }
}
