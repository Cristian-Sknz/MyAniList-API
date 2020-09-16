package me.skiincraft.mal.entity.manga;

public class MangaStatus {
	
	private int completed;
	private int reading;
	private int onhold;
	private int dropped;
	private int planToRead;
	private int totalEntries;
	private long chapters;
	private long volumes;
	
	private float days;
	private float score;
	
	public MangaStatus(int completed, int reading, int onhold, int dropped, int planToRead, int totalEntries,
			long chapters, long volume, float days, float score) {
		this.completed = completed;
		this.reading = reading;
		this.onhold = onhold;
		this.dropped = dropped;
		this.planToRead = planToRead;
		this.totalEntries = totalEntries;
		this.chapters = chapters;
		this.volumes = volume;
		this.days = days;
		this.score = score;
	}
	
	
	public float getDays() {
		return days;
	}
	public float getScore() {
		return score;
	}
	public long getChapters() {
		return chapters;
	}
	public int getCompleted() {
		return completed;
	}
	public int getDropped() {
		return dropped;
	}
	public int getOnhold() {
		return onhold;
	}
	public int getPlanToRead() {
		return planToRead;
	}
	public int getReading() {
		return reading;
	}
	public int getTotalEntries() {
		return totalEntries;
	}
	public long getVolumes() {
		return volumes;
	}


	public String toString() {
		return "MangaStatus [completed=" + completed + ", reading=" + reading + ", onhold=" + onhold + ", dropped="
				+ dropped + ", totalEntries=" + totalEntries + ", chapters=" + chapters + ", volumes=" + volumes + "]";
	}
	
	
	
}
