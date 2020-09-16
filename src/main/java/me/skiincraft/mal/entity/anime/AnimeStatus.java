package me.skiincraft.mal.entity.anime;

public class AnimeStatus {
	
	private int completed;
	private int watching;
	private int onhold;
	private int dropped;
	private int planToWatch;
	private int totalEntries;
	private long episodes;
	
	private float days;
	private float score;
	
	
	public AnimeStatus(int completed, int watching, int onhold, int dropped, int planToWatch, int totalEntries,
			long episodes, float days, float score) {
		this.completed = completed;
		this.watching = watching;
		this.onhold = onhold;
		this.dropped = dropped;
		this.planToWatch = planToWatch;
		this.totalEntries = totalEntries;
		this.episodes = episodes;
		this.days = days;
		this.score = score;
	}

	public float getDays() {
		return days;
	}
	
	public float getScore() {
		return score;
	}
	
	public int getCompleted() {
		return completed;
	}
	public int getWatching() {
		return watching;
	}
	public int getOnhold() {
		return onhold;
	}
	public int getDropped() {
		return dropped;
	}
	public int getPlanToWatch() {
		return planToWatch;
	}
	public int getTotalEntries() {
		return totalEntries;
	}
	public long getEpisodes() {
		return episodes;
	}

	public String toString() {
		return "AnimeStatus [completed=" + completed + ", watching=" + watching + ", onhold=" + onhold + ", dropped="
				+ dropped + ", totalEntries=" + totalEntries + "]";
	}
	
	
	
}
