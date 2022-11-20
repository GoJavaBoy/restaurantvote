package com.lunchvote;

import java.time.*;

public class MockClock extends Clock {
    private Instant instant = Instant.now();

    @Override
    public ZoneId getZone() {
        return ZoneId.systemDefault();
    }

    @Override
    public Clock withZone(ZoneId zone) {
        return Clock.systemDefaultZone();
    }

    @Override
    public Instant instant() {
        return instant;
    }

    public void setBeforeEleven(){
        instant = LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)).toInstant(ZoneOffset.UTC);
    }

    public void setAfterEleven(){
        instant = LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 0)).toInstant(ZoneOffset.UTC);
    }

    public void fixToNormalTime(){
        instant = Instant.now();
    }
}
