package com.practica.tms_android;

public class EventCardModel {
    private String eventName;
    private String eventDescription;
    private String eventPeriod;
    private String eventLocation;

    public EventCardModel(String eventName, String eventDescription, String eventPeriod, String eventLocation) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventPeriod = eventPeriod;
        this.eventLocation = eventLocation;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventPeriod() {
        return eventPeriod;
    }

    public void setEventPeriod(String eventPeriod) {
        this.eventPeriod = eventPeriod;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }
}
