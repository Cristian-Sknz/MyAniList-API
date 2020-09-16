package me.skiincraft.mal.entity.objects;

public class Statistics {
	
	private float score;
	private long ranked;
	private long popularity;
	private long members;
	
	public Statistics(float score, long ranked, long popularity, long members) {
		this.score = score;
		this.ranked = ranked;
		this.popularity = popularity;
		this.members = members;
	}
	public float getScore() {
		return score;
	}
	public long getRanked() {
		return ranked;
	}
	public long getPopularity() {
		return popularity;
	}
	public long getMembers() {
		return members;
	}
	public String toString() {
		return "Statistics [score=" + score + ", ranked=" + ranked + ", popularity=" + popularity + ", members="
				+ members + "]";
	}
	
	

}
