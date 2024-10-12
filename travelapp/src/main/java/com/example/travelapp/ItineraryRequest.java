package com.example.travelapp;

import org.springframework.data.annotation.Id;

//@Document(collation = "itineraryRequest")
public class ItineraryRequest {

    @Id
    private String id;

    private String email;
    private String destination;
    private int duration;
    private String preference;
    private String generateditineraries;

    public ItineraryRequest() {
    }

    public ItineraryRequest(String id, String destination, int duration, String preference, String generateditineraries) {
        this.id = id;
        this.email = email;
        this.destination = destination;
        this.duration = duration;
        this.preference = preference;
        this.generateditineraries = generateditineraries;
    }

    public String  getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public String getGenerateditineraries() {
        return generateditineraries;
    }

    public void setGenerateditineraries(String generateditineraries) {
        this.generateditineraries = generateditineraries;
    }

    @Override
    public String toString() {
        return "ItineraryRequest{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", destination='" + destination + '\'' +
                ", duration=" + duration +
                ", preference='" + preference + '\'' +
                ", generateditineraries='" + generateditineraries + '\'' +
                '}';
    }
}
