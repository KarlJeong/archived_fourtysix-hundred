package com.karljeong.fourtysix.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
}
