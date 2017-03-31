package com.etoilecarte.Utils;

import android.view.Gravity;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mghrissi on 01/03/2017.
 */

public class Validator {

    public static boolean validateFormatHour(String hour) {

        if (hour.equalsIgnoreCase("") || hour == null) {
            return true;
        } else {
            return (!(hour.matches(".*[A-Za-z].*") || hour.length() != 4 || getSpecialCharacterCount(hour)));
        }
    }

    public static boolean validateFormatHourWithSplit(EditText hour, int i) {
        if (hour == null || hour.getText().toString().equalsIgnoreCase("") || hour.getText().toString().equalsIgnoreCase("-")) {
            hour.setText(" - ");
            hour.setGravity(Gravity.CENTER);
            return true;
        } else if (!hour.getText().toString().contains("-") && !hour.getText().toString().equalsIgnoreCase("")) {
            return false;
        } else { 
            String sHour = hour.getText().toString().split("-")[i];
            return (!(sHour.matches(".*[A-Za-z].*") || sHour.length() > 4 || getSpecialCharacterCount(sHour)));
        }
    }
    public static boolean getSpecialCharacterCount(String s) {
        if (s == null || s.trim().isEmpty()) {
            return false;
        }
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(s);
        boolean b = m.find();
        return b;

    }

    public static boolean areAllTrue(boolean[] array) {
        for (boolean b : array) if (!b) return false;
        return true;
    }

    /**
     * Parsing double
     * @param strNumber string to parse
     * @return double. 0 by default
     */
    public static double parseDouble(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Double.parseDouble(strNumber);
            } catch (Exception e) {
                return -1;   // or some value to mark this field is wrong. or make a function validates field first ...
            }
        } else {
            return 0;
        }
    }

    /**
     * Pattern to validate if date match dd/MM/yyyy
     *
     * @return true if date valide
     */
    public static boolean isDateValid(String date) {
        if (date == null || date.trim().isEmpty()) {
            return false;
        }
        return date.matches(
                "^(((0[1-9]|[12]\\d|3[01])\\/(0[13578]|1[02])\\/((1[6-9]|[2-9]\\d)\\d{2}))|((0[1-9]|[12]\\d|30)\\/(0[13456789]|1[012])\\/((1[6-9]|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])\\/02\\/((1[6-9]|[2-9]\\d)\\d{2}))|(29\\/02\\/((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$");
    }

    /**
     * Pattern to validate if date match dd/MM/yyyy
     *
     * @return true if date valide
     */
    public static boolean isEmailValide(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return email.matches(
                "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    }

    /**
     * Format date in yyyy-MM-dd for SalesForce
     */
    public static String formatDate(String dateString) throws ParseException {
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        Date date = dt.parse(dateString);
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
        return dt1.format(date);
    }

}
