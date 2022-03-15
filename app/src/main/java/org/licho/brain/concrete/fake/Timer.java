package org.licho.brain.concrete.fake;


import java.util.TimerTask;

/**
 * improve: -> ScheduledExecutorService -> TimeWheel
 */
public class Timer {
    private java.util.Timer timer;
    public int Interval;
    public TimerTask timerTask;

    public Timer() {
        this.timer = new java.util.Timer();
    }

    public void Enabled(boolean run) {
        if (run) {
            this.timer.schedule(this.timerTask, this.Interval);
        }else{
            this.timer.cancel();
        }
    }

    public void Dispose() {
        this.timer.purge();
    }
}
