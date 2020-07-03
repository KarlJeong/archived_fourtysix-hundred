package com.karljeong.fourtysix.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

public class ThymeleafDialectUtil {
	private static String[] suffix = new String[] { "", "k", "m", "b", "t" };
	private static int MAX_LENGTH = 4;

	public String convertNumberFormat(double number) {
		String r = new DecimalFormat("##0E0").format(number);
		r = r.replaceAll("E[0-9]", suffix[Character.getNumericValue(r.charAt(r.length() - 1)) / 3]);
		while (r.length() > MAX_LENGTH || r.matches("[0-9]+\\.[a-z]")) {
			r = r.substring(0, r.length() - 2) + r.substring(r.length() - 1);
		}
		return r;
	}

    public String dateConverterToDateOrDateTime(Timestamp givenDateTime) {
        if (givenDateTime == null) {
            return "";
        }

        long timestamp = givenDateTime.getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp);

        long currentTimestamp = System.currentTimeMillis();
        Calendar todayCal = Calendar.getInstance();
        todayCal.setTimeInMillis(currentTimestamp);


        DateFormat df = null;
        if (cal.get(Calendar.YEAR) == todayCal.get(Calendar.YEAR) && cal.get(Calendar.MONTH) == todayCal.get(Calendar.MONTH) && cal.get(Calendar.DATE) == todayCal.get(Calendar.DATE)) {
            df = new SimpleDateFormat("HH:mm:ss");
        } else {
            df = new SimpleDateFormat("yyyy-MM-dd");
        }
        return df.format(cal.getTime());
    }
    
    public String dateConverToTimeOrDate(Timestamp givenDateTime) {
    	if (givenDateTime == null) {
    		return "";
    	}
        
        long timestamp = givenDateTime.getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp);

        long currentTimestamp = System.currentTimeMillis();
        Calendar todayCal = Calendar.getInstance();
        todayCal.setTimeInMillis(currentTimestamp);

        LocalDateTime givenLocalDate = givenDateTime.toLocalDateTime();
        LocalDateTime currentLocalDate = new Timestamp(System.currentTimeMillis()).toLocalDateTime();

        long years = ChronoUnit.YEARS.between(givenLocalDate, currentLocalDate);
        long months = ChronoUnit.MONTHS.between(givenLocalDate, currentLocalDate);
        long weeks = ChronoUnit.WEEKS.between(givenLocalDate, currentLocalDate);
        long days = ChronoUnit.DAYS.between(givenLocalDate, currentLocalDate);
        long hours = ChronoUnit.HOURS.between(givenLocalDate, currentLocalDate);
        long minutes = ChronoUnit.MINUTES.between(givenLocalDate, currentLocalDate);
        long seconds = ChronoUnit.SECONDS.between(givenLocalDate, currentLocalDate);
        
        if (seconds < 60) {
        	return seconds + (seconds > 1 ? " secs ago" : " sec ago");
        }
        else if (minutes < 60) {
        	return minutes + (minutes > 1 ? " mins ago" : " min ago");
        }
        else if (hours < 24) {
        	return hours + (hours > 1 ? " hours ago" : " hour ago");
        }
        else if (days >= 1) {
        	long d = (hours - (days * 24));
        	if (d > currentLocalDate.getHour()) {
        		days = days + 1;
        	}
        	return days + (days > 1 ? " days ago" : " days ago");
        }
        else if (months >= 1) {
        	return months + (months > 1 ? " months ago" : " month ago");
        }
        else if (years >= 1) {
        	return years + (years > 1 ? " years ago" : " year ago");
        }
        else {
        	return null;
        }
    }
    
}
