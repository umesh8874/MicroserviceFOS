package com.AN1D.an1d.Utils;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    // public static Timestamp = "";

    public static Timestamp getReferralExpiryDate(){
        Date now = new Date();
        Date dateAfter30Days = addDays(now, 30);
        Timestamp timestamp = new Timestamp(dateAfter30Days.getTime());
        return timestamp;
    }

    private static Date addDays(Date d, int days){
        d.setTime(d.getTime() + days * 1000 * 60 * 60 * 24);
        return d;
    }

    public static String getFormattedDateInYYYYMMDD(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static Date getTodaysDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

    public static Timestamp getCurretDateAsTimeStamp(){
        Date now = new Date();
        Timestamp timestamp = new Timestamp(now.getTime());
        return timestamp;
    }
}