package com.zdpx.cctpp.utils.simu.system;

import com.zdpx.cctpp.concrete.fake.TimeSpan;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 */
public class DateTime {
    public static DateTime MinValue;
    public static DateTime MaxValue;
    private LocalDateTime value = LocalDateTime.now();
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public DateTime() {

    }

    public DateTime(LocalDateTime value) {
        this.value = value;
    }

    public DateTime(int year, int month, int day, int hour, int minute, int second) {
        this.value = LocalDateTime.of(year, month, day, hour, minute, second);
    }

    public DateTime(int year, int month, int day) {
        this.value = LocalDateTime.of(LocalDate.of(year, month, day), LocalTime.of(0, 0));
    }


    public static DateTime TryParse(String value) {
        return DateTime.TryParse(value, null, null);
    }

    public static DateTime TryParse(String value, Object dateTimeFormat, DateTimeStyles none) {
        try {
            return new DateTime(LocalDateTime.parse(value, DateTime.formatter));

        } catch (Exception e) {
            return null;
        }
    }

    public static DateTime Now() {
        return new DateTime(LocalDateTime.now());
    }


    public DateTime AddDays(double value) {
        return new DateTime(this.value.plusDays((long) value));
    }

    public DateTime add(TimeSpan fromHours) {
        return new DateTime(this.value.plus(fromHours.duration));
    }

    public TimeSpan sub(DateTime startTime) {
        return new TimeSpan(Duration.between(this.value, startTime.value));
    }

    public int compare(DateTime nowTime) {
        return this.value.compareTo(nowTime.value);
    }

    public DateTime Date() {
        return null;
    }

}
