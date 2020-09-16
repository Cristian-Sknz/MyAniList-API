package me.skiincraft.mal.entity.search;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class Search {
	
	private List<SearchResult> results = new ArrayList<>();
	private String searchQueue;
	
	public Search(Consumer<List<SearchResult>> task, String queue) {
		task.accept(results);
	}
	
	public SearchResult getFirst() {
		return getResults().get(0);
	}
	
	public List<SearchResult> getResults() {
		return results;
	}
	
	public String getSearchQueue() {
		return searchQueue;
	}
	
	protected static long getId(String substring, String url) {
		String str = url.substring(url.indexOf(substring)).substring(substring.length());
		int index = (str.indexOf("/") == -1) ? 0 : str.indexOf("/");
		if (index != 0) {
			str = str.substring(0, index);
		}
		return Long.parseLong(str.replaceAll("\\D+", ""));
	}
	
}
