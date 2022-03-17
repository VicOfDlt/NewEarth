package gg.newearth.utility;

import org.bukkit.Bukkit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeUtility {

    public static Date getCurrentDate() {
        return new Date();
    }

    public static String getCurrentDateStringFormat() {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        return format.format(date);
    }

    public static String getDateStringFormat(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        return format.format(date);
    }

    public static Date parseDateString(String dateString) throws ParseException { // dd/MM/yyyy format
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        return format.parse(dateString);
    }

    public static long getDateDifference(Date date1, Date date2) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }
}
