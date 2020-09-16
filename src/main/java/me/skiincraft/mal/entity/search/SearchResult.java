package me.skiincraft.mal.entity.search;

public class SearchResult {
	
	private String result;
	private long resultId;
	
	public SearchResult(String result, long resultId) {
		this.result = result;
		this.resultId = resultId;
	}
	
	public String getResult() {
		return result;
	}
	
	public long getResultId() {
		return resultId;
	}
}
