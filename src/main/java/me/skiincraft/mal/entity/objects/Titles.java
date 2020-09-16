package me.skiincraft.mal.entity.objects;

import java.util.Arrays;

public class Titles {
	
	String english;
	String[] synonyms;
	String japonese;
	
	public Titles(String english, String japonese, String... synonyms) {
		this.english = english;
		this.synonyms = synonyms;
		this.japonese = japonese;
	}

	public String getEnglish() {
		return english;
	}
	
	public String getJaponese() {
		return japonese;
	}
	
	public String[] getSynonyms() {
		return synonyms;
	}

	public String toString() {
		return "Titles [english=" + english + ", synonyms=" + Arrays.toString(synonyms) + ", japonese=" + japonese
				+ "]";
	}
	
	

}
