package me.skiincraft.mal.entity.objects;

import java.util.Arrays;

public enum MediaType {

	OVA, TV, Special, ONA, Movie, Manga, Novel, Visual;
	
	public static MediaType getMediaType(String string) {
		return Arrays.stream(values()).filter(o -> o.name().equalsIgnoreCase(string)).findAny().orElse(null);
	}
	
}
