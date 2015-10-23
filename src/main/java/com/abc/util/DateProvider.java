package com.abc.util;

import java.util.Calendar;
import java.util.Date;

public class DateProvider {
    private static DateProvider instance = null;

    public static DateProvider getInstance() {
        if (instance == null)
            instance = new DateProvider();
        return instance;
    }

    public Date now() {
        return Calendar.getInstance().getTime();
    }

//	public Date oneYearFromNow() {
//		// TODO Auto-generated method stub
//		Calendar nextYearToday = Calendar.getInstance();
//		nextYearToday.add(Calendar.YEAR, 1);
//        return nextYearToday.getTime();
//	}
}
