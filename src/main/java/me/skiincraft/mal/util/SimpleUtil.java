package me.skiincraft.mal.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class SimpleUtil {

	public static Month convertMonth(String threeLetters){
		return Arrays.stream(Month.values()).filter(month -> month.name().substring(0, 3).equalsIgnoreCase(threeLetters.substring(0, 3))).findAny().orElse(null);
	}

	public static String convertAired(String airedString){
		if (airedString.contains("Not available")){
			return "Not available";
		}
		String[] value = airedString.split("to");

		String[] datesplit = value[0].split(" ");
		StringBuilder builder = new StringBuilder();
		if (!(datesplit.length == 1)) {
			builder.append(SimpleUtil.convertMonth(datesplit[0]).getValue()).append("/");
			builder.append((datesplit.length == 3) ? Integer.parseInt(datesplit[1].replaceAll("\\D+", "")) : 1).append("/");
			builder.append(Integer.parseInt(datesplit[datesplit.length-1])).append(" to ");

			if (datesplit.length != 3) {
				builder.append("?");
			} else {
				String[] datesplit2 = value[1].split(" ");
				builder.append(SimpleUtil.convertMonth(datesplit2[1]).getValue()).append("/");
				builder.append((datesplit2.length == 3) ? Integer.parseInt(datesplit2[2].replaceAll("\\D+", "")) : 1).append("/");
				builder.append(Integer.parseInt(datesplit2[datesplit2.length-1]));
			}
		} else {
			builder.append(Integer.parseInt(datesplit[0])).append(" to ?");
		}
		return builder.toString();
	}

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
