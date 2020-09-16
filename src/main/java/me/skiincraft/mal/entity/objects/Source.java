package me.skiincraft.mal.entity.objects;

public enum Source {
	
	Manga, Light_novel, Visual_novel;
	
	public static Source getSource(String string) {
		if (string.contains("Light")) {
			return Light_novel;
		}
		if (string.contains("Visual")) {
			return Visual_novel;
		}
		return Manga;
	}

}
