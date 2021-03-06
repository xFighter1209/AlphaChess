package com.chess;

public class TimeManager {

    private long startTime, stopTime;
    private boolean stopped;


    public void initialize(TimeLimit limit, Board board) {
        startTime = System.currentTimeMillis();
        Side us = board.sideToMove();
        limit.mtg = limit.mtg == 0 ? 30 : limit.mtg;


        if (limit.moveTime == 0L && limit.time[us.ordinal()] > 0L) {
            long timeLeft = Math.max(1, limit.time[us.ordinal()] + limit.inc[us.ordinal()] * (limit.mtg - 1) - 10L * (2 + limit.mtg));
            limit.moveTime = timeLeft / limit.mtg;
            stopTime = startTime + limit.moveTime;
        } else if (limit.moveTime > 0L)
            stopTime = startTime + limit.moveTime;
    }


    public void resetTimeControl() {
        startTime = 0L;
        stopTime = 0L;
        stopped = false;
    }


    public void stop() {
        stopped = true;
    }
    public long elapsed() {
        return System.currentTimeMillis() - startTime;
    }
    public long stopTime() {
        return stopTime;
    }
    public boolean didStopped() {
        return stopped;
    }
    public boolean timeSet() {
        return stopTime != 0L;
    }


}
