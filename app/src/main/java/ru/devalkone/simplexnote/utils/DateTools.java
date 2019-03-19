package ru.devalkone.simplexnote.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTools {

    public static String getFormattedDate(String rawFormat, String endFormat, String rawDate) {
        SimpleDateFormat utcFormat = new SimpleDateFormat(rawFormat, Locale.ROOT);
        SimpleDateFormat displayedFormat = new SimpleDateFormat(endFormat, Locale.getDefault());
        try {
            Date date = utcFormat.parse(rawDate);
            return displayedFormat.format(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
