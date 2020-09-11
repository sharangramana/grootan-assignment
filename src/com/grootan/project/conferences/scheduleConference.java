package com.grootan.project.conferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import com.grootan.project.configs.configs;
import com.grootan.project.events.event;

// class to schedule all the events for the estimated number of days
public class scheduleConference {

    // function to schedule the events
    public static int scheduleEvents(int eventCountIndex, List<event> eventNames,
                                     int eventCount, int startEventIndex, int totalEventCount){

        // gives the required time stamp
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm a");
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR, 9);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.AM_PM, Calendar.AM);

        int sumMorning = configs.MORNING_DURATION;  // 180
        int sumAfternoon = configs.AFTERNOON_DURATION; // 240

        int eventIndex;

        String sessionTime; // holds the event name along with the time it's scheduled
        String SessionTitle; // indicates the day in which it's perfomed


        for(eventIndex=startEventIndex; eventIndex< totalEventCount;eventIndex++) {

            // Schedules the morning session of each day
            if (sumMorning >= eventNames.get(eventIndex).getMinutes()) {
                sumMorning = sumMorning - eventNames.get(eventIndex).getMinutes();

                sessionTime = sdf.format(cal.getTime()) + " " + eventNames.get(eventIndex).getName() + " " +
                        eventNames.get(eventIndex).getMinutes() + "min";

                eventNames.get(eventIndex).setName(sessionTime);
                cal.add(Calendar.MINUTE, eventNames.get(eventIndex).getMinutes());
                SessionTitle = "Day" + " " + (eventCountIndex + 1);
                eventNames.get(eventIndex).setDays(SessionTitle);
            }
            if (sumMorning < eventNames.get(eventIndex).getMinutes())
                break;

            if (sumMorning > 0)
                continue;

            if (sumMorning <= 0)
                break;
        }


        eventNames.get(eventIndex).setLunchTime(true);
        sessionTime = "12:00 PM" + " " + "Lunch";
        eventNames.get(eventIndex).setLunch(sessionTime);
        cal.add(Calendar.MINUTE, 60);

        eventIndex++;

        // Schedules the afternoon session of each day
        for(;eventIndex< totalEventCount;eventIndex++) {

            if (sumAfternoon >= eventNames.get(eventIndex).getMinutes()) {
                sumAfternoon = sumAfternoon - eventNames.get(eventIndex).getMinutes();
                sessionTime = sdf.format(cal.getTime()) + " " + eventNames.get(eventIndex).getName() + " " + eventNames.get(eventIndex).getMinutes() + "min";

                eventNames.get(eventIndex).setName(sessionTime);
                cal.add(Calendar.MINUTE, eventNames.get(eventIndex).getMinutes());
                SessionTitle = "Day" + " " + (eventCountIndex + 1);
                eventNames.get(eventIndex).setDays(SessionTitle);
            }
            if (sumAfternoon < eventNames.get(eventIndex).getMinutes())
                break;

            if (sumAfternoon > 0)
                continue;

            if (sumAfternoon <= 0)
                break;
        }

        // in the end, adding the event of networking
        if(totalEventCount == (eventIndex))
            --eventIndex;
        eventNames.get(eventIndex).setNetworkingTIme(true);
        sessionTime = sdf.format(cal.getTime()) + " " + "Networking Event";
        eventNames.get(eventIndex).setNetworking(sessionTime);

        eventIndex++;
        return eventIndex; // returns the index which is last processed

    }
}
