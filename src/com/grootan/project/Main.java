package com.grootan.project;

import com.grootan.project.conferences.conferenceMain;
import com.grootan.project.configs.configs;
import com.grootan.project.conferences.scheduleConference;

public class Main {
    public static void main(String[] args) {

        // creating object for conference
        conferenceMain conference = new conferenceMain();

        // function to pass the required input file
        conference.fileRead(configs.INPUT_FILE);

        // getting the estimated number of days from the input file provided
        int numberOfDays = conference.getCountEventDays();

        int eventIndex = 0;

        // scheduling all the events for the estimated number of days
        for(int index = 0; index <numberOfDays; index++)
        {
            eventIndex = scheduleConference.scheduleEvents(index, conference.getEventNames(), conference.getCountEventDays(), eventIndex, conference.getCountEvents());
        }

        // printing the events list for number of days
        conference.eventsList(conference.getEventNames());
    }
}
