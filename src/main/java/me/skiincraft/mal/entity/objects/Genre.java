package me.skiincraft.mal.entity.objects;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Genre {

	Action("Action"), Adventure("Adventure"), Cars("Cars"), Comedy("Comedy"), Dementia("Dementia"), Demons("Demons"),
	Drama("Drama"), Ecchi("Ecchi"), Fantasy("Fantasy"), Game("Game"), Harem("Harem"), Hentai("Hentai"),
	Historical("Historiacal"), Horror("Horror"), Josei("Josei"), Kids("Kids"), Magic("Magic"),
	Martial_Arts("Martial Arts"), Mecha("Mecha"), Military("Military"), Music("Music"), Mystery("Mistery"),
	Parody("Parody"), Police("Police"), Psychological("Psychological"), Romance("Romance"), Samurai("Samurai"),
	School("School"), Sci_Fi("Sci-fi"), Seinen("Seinen"), Shoujo("Shoujo"), Shoujo_Ai("Shoujo Ai"), Shounen("Shounen"),
	Shounen_Ai("Shounen Ai"), Slice_of_Life("Slife of Life"), Space("Space"), Sports("Sports"),
	Super_Power("Super Power"), Supernatural("Supernatural"), Thriller("Thriller"), Vampire("Vampire"), Yaoi("Yaoi"),
	Yuri("Yuri");

	private String name;
	
	private Genre(String genrename) {
		this.name = genrename;
	}
	
	public String getName() {
		return name;
	}
	
	public static List<Genre> getGenres(String string) {
		return Arrays.stream(values()).filter(f -> string.contains(f.getName())).collect(Collectors.toList());
	}

}
