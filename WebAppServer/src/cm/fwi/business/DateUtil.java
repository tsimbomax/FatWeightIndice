package cm.fwi.business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DateUtil {

    private static final String DEFAULT_FORMAT_PATTERN_1 = "EEE MMM dd hh:mm:ss 'WAT' yyyy";
    private static final String DEFAULT_FORMAT_PATTERN_2 = "yyyy-MM-dd hh:mm:ss";

    /**
     * Conversion of a string in the pattern Mon Jan 12:09:59 GMT 2019 to a date
     * @param sdate a string representation of a date
     * @return the associate date
     */
    public static Date convertStringToDate(String sdate) throws ParseException {

        return convertStringToDate(sdate, DEFAULT_FORMAT_PATTERN_1);
    }
    
    /**
     * Conversion of a string in a given pattern to a date
     * @param sdate a string representation of a date
     * @param pattern of the string
     * @return the associate date
     */
    public static Date convertStringToDate(String sdate, String pattern) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Date date = formatter.parse(sdate);

        return date;
    }

    /**
     *Conversion of a date to string in the format yyyy-MM-jj hh:mm:ss
     * @param date to convert to string
     * @return a string
     */
    public static String convertDateToString(Date date){

        SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_FORMAT_PATTERN_2);
        String sdate = formatter.format(date);
        return sdate;
    }
}
