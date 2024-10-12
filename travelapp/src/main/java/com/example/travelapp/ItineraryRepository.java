package com.example.travelapp;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ItineraryRepository extends MongoRepository<ItineraryRequest, String> {
    //public ItineraryRequest findByeID(String id);
    public ItineraryRequest findByDestination(String destination);
    public List<ItineraryRequest> findAllByEmail(String email);
}
