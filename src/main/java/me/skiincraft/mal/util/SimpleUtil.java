package me.skiincraft.mal.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class SimpleUtil {

	public static JsonElement getJsonConnection(String url) throws IOException {
		URL link = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) link.openConnection();
		connection.setRequestProperty("accept", "application/json");
		InputStream responseStream = connection.getInputStream();
		return new JsonParser().parse(new InputStreamReader(responseStream));
	}
	
	public static LocalDate parseLocalDate(String string) {
		DateFormat informat= new SimpleDateFormat("MM-dd-yy");
		try {
			return LocalDate.ofEpochDay(TimeUnit.MILLISECONDS.toDays(informat.parse(string).toInstant().toEpochMilli()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
