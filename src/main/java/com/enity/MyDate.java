package com.enity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MyDate extends Date {
    public MyDate(long date) {
        super(date);
    }

    @Override
    public String toString() {
        int year = 1900 + getYear();
        int month = getMonth() % 12 + 1;
        int day = getDate();
        int hour = getHours();
        int minute = getMinutes();

        LocalDateTime time = LocalDateTime.of(year,month,day,hour,minute);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String format = time.format(dateTimeFormatter);
        return format;
    }
}
