package com.hospital.module.Services;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TimeSlotGenerator {

    public static List<LocalTime> generateTimeSlots(LocalTime startTime, LocalTime endTime, int intervalMinutes) {
        List<LocalTime> timeSlots = new ArrayList<>();
        LocalTime currentTime = startTime;

        while (!currentTime.isAfter(endTime)) {
            timeSlots.add(currentTime);
            currentTime = currentTime.plusMinutes(intervalMinutes);
        }

        return timeSlots;
    }
}