package me.skiincraft.mal.entity.objects;

import java.util.Arrays;

public enum MediaType {

	OVA("Ova"), TV("TV"), Special("Special"), ONA("ONA"), Movie("Movie"), Manga("Manga"), Novel("Light Novel"), Visual("Visual Novel");

	private final String name;

	MediaType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static MediaType getMediaType(String string) {
		return Arrays.stream(values()).filter(o -> o.name().equalsIgnoreCase(string)).findAny().orElse(null);
	}
	
}
