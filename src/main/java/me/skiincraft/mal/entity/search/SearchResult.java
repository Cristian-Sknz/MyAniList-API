package me.skiincraft.mal.entity.search;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SearchResult {
	
	private final String result;
	private final long resultId;
	private String image = null;
	
	public SearchResult(String result, long resultId) {
		this.result = result;
		this.resultId = resultId;
	}

	public SearchResult(String result, long resultId, @Nonnull String image) {
		this.result = result;
		this.resultId = resultId;
		this.image = image;
	}

	public String getResult() {
		return result;
	}

	@Nullable
	public String getResultImage() {
		return image;
	}
	
	public long getResultId() {
		return resultId;
	}
}
