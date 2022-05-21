package edu.fjnu501.securities.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RandomUsername {

    public static String getRandomUserName(int maxId) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String srcDate = "2002-03-28";
        Date late = simpleDateFormat.parse(srcDate);
        Date now = new Date();
        long timestamp = ((now.getTime() - late.getTime()) / 100);
        return String.valueOf(timestamp + maxId);
    }

}