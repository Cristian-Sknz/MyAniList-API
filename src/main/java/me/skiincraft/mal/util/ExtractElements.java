package me.skiincraft.mal.util;

import java.util.concurrent.atomic.AtomicInteger;

public class ExtractElements {

    public static final int ANIME = 1;
    public static final int MANGA = 2;
    public static final int CHARACTER = 3;

    public static String extractImage(String datasrc, final int type){
        String str = datasrc.substring(datasrc.indexOf(typeToString(type))).substring(typeToString(type).length());
        String ids = str.substring(0, str.lastIndexOf("."));
        if (countChars('/', ids) > 2) {
            String[] split = ids.split("/");
            if (split.length < 2){
                return null;
            }
            ids = split[split.length - 2] + "/"+ split[split.length - 1];
        }

        return String.format("https://cdn.myanimelist.net/images/%s/%s.jpg", typeToString(type), (ids.startsWith("/")) ? ids.substring(1) : ids);
    }

    public static int countChars(char c, String string){
        AtomicInteger integer = new AtomicInteger(0);
        for (char ch : string.toCharArray()){
            if (ch == c) integer.getAndIncrement();
        }
        return integer.get();
    }

    private static String typeToString(int type){
        return (type == 1) ? "anime" : (type == 2) ? "manga" : (type == 3) ? "characters" : "anime";
    }

}
