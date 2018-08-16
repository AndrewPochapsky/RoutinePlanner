package com.company.loaf.routinescheduler;

import com.company.loaf.routinescheduler.utils.DateUtils;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class DateUtilsTests {

    @Test
    public void dateToString_TodayDate_ReturnsValidFormat(){
        String regex = "\\d{4}\\|\\d{1,2}\\|\\d{1,2}";
        Assert.assertTrue(DateUtils.dateToString(LocalDate.now()).matches(regex));
    }

    @Test
    public void stringToDate_TodayDate_ReturnsValidDate(){
        LocalDate date = LocalDate.now();
        String stringDate= DateUtils.dateToString(date);
        LocalDate convertedDate = DateUtils.stringToDate(stringDate);

        Assert.assertTrue(date.equals(convertedDate));
    }
}
