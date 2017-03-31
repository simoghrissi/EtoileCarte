package com.etoilecarte.Utils;

import com.google.gson.JsonElement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by mghrissi on 08/02/2017.
 */

public class StringUtils {

    public static String formateDate(String value) {
        if (value == null || value.equalsIgnoreCase("")) {
            return "";
        } else {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd", Locale.FRENCH);
            try {
                Date date = fmt.parse(value);
                value = fmt.format(date);
                fmt = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
                value = fmt.format(date);
                return value;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    /**
     * Method used to syncDown.
     * Consist of the WHERE/IN condition in a sql request.
     * Get a list of id and format for the request.
     * ie: '0010E000002hd7DQAQ','0010E000002hd7LQAQ','0010E000002hd7HQAQ'
     *
     * @param poiIDsFromPrefs list of ids
     * @return a string formated for the WHERE clause in the sql request
     */
    public static String formatIDs(String[] poiIDsFromPrefs) {
        StringBuilder sb = new StringBuilder();
        for (String poiIDFromPrefs : poiIDsFromPrefs) {
            sb.append("'");
            sb.append(poiIDFromPrefs);
            sb.append("'");
            sb.append(",");
        }
        // Substract the last coma (",")
        String poiIDForRequest = "";
        if (sb.length() > 0) {
            poiIDForRequest = sb.substring(0, sb.length() - 1);
        }
        return poiIDForRequest;
    }

    /**
     * if element not present, return null;
     *
     * @return String
     */
    public static String getNullAsEmptyString(JsonElement jsonElement) {
        if (jsonElement != null && !jsonElement.isJsonNull()) {
            return jsonElement.getAsString();
        }
        return null;
    }

    /**
     * If element not present, return false to API
     *
     * @return boolean
     */
    public static boolean getNullAsEmptyBoolean(JsonElement jsonElement) {
        return !jsonElement.isJsonNull() && jsonElement.getAsBoolean();
    }

    /**
     * If element not present, return 0 to API
     *
     * @return double
     */
    public static double getNullAsEmptyDouble(JsonElement jsonElement) {
        return jsonElement.isJsonNull() ? 0 : jsonElement.getAsDouble();
    }
}
