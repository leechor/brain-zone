package org.licho.brain.concrete.fake;

import java.time.Duration;

/**
 *
 */
public class TimeSpan implements Comparable<TimeSpan> {
    public static TimeSpan Zero = new TimeSpan(0, 0, 0);
    public Duration duration;

    public TimeSpan(Duration duration) {
        this.duration = duration;
    }

    public TimeSpan(int hours, int minutes, int seconds) {
        this.duration = Duration.ofHours(hours).plusMinutes(minutes).plusSeconds(seconds);
    }

    public static TimeSpan FromHours(double endTimeHours) {
        return new TimeSpan(Duration.ofHours((long) endTimeHours));
    }

    public static TimeSpan parse(String attribute) {
        return null;
    }

    public static TimeSpan FromDays(double v) {
        return null;
    }

    public int Hours() {
        return this.duration.toHoursPart();
    }

    public int TotalHours() {
        return this.duration.toHoursPart();
    }

    public int Days() {
        return 0;
    }


    public int Minutes() {
        return this.duration.toMinutesPart();
    }

    public int Seconds() {
        return this.duration.toSecondsPart();
    }


    @Override
    public int compareTo(TimeSpan o) {
        return this.duration.compareTo(o.duration);
    }

    public TimeSpan add(TimeSpan timeSpan) {
        return new TimeSpan(this.duration.plus(timeSpan.duration));
    }
}
