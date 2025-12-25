package com.onlinestore.corecart.util;

public class SlugUtil {


    public static String toSlug(String name) {

        String normalizedName = normalize(name).toLowerCase();

        StringBuilder result = new StringBuilder();

        for (char c : normalizedName.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            } else {
                result.append("-");
            }
        }

        return result.toString()
                .replaceAll("-+", "-")
                .replaceAll("^-|-$", "");




    }


    private static String normalize(String input) {
        return input
                .replace("ç", "c").replace("Ç", "c")
                .replace("ş", "s").replace("Ş", "s")
                .replace("ö", "o").replace("Ö", "o")
                .replace("ü", "u").replace("Ü", "u")
                .replace("ə", "e").replace("Ə", "e")
                .replace("ı", "i").replace("İ", "i")
                .replace("ğ", "g").replace("Ğ", "g");
    }


}
