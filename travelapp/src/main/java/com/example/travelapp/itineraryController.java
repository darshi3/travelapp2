package com.example.travelapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/itinerary")
public class itineraryController {

    @Autowired
    private ItineraryService itineraryService;



    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/create")
    public ResponseEntity<ItineraryRequest> createItinerary(@RequestBody ItineraryRequest itinerary) {
        return ResponseEntity.ok(itineraryService.saveItinerary(itinerary));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ItineraryRequest>> getAllItineraries() {
       // Object ItineraryRequest = ResponseEntity<ItineraryRequest>;
            return ResponseEntity.ok(itineraryService.getAllItineraries());


    }

    @GetMapping("/{id}")
    public ResponseEntity<ItineraryRequest> getAllItineraries(@PathVariable String id) {
        ItineraryRequest I1 = itineraryService.getItineraryById(id);
        if(I1 != null) {
            return ResponseEntity.ok(I1);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/generate_itinerary")
    public ResponseEntity<ItineraryRequest> generateItinerary(@RequestBody ItineraryRequest request) {
        String generatedItinerary = itineraryService.generate(request);
        // Set the generated itinerary in the request object
        request.setGenerateditineraries(generatedItinerary);
        //itineraryService.updateItinerary(request); // Save the updated itinerary with the generated details
        // Return the modified request object as the response
        return ResponseEntity.ok(request);

    }

    @GetMapping("/dest/{destination}")
    public ResponseEntity<ItineraryRequest> getAllDestinations(@PathVariable String destination) {
        ItineraryRequest I1 = itineraryService.getItineraryByDestination(destination);
        if(I1 != null) {
            return ResponseEntity.ok(I1);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<ItineraryRequest>> getAllDestByemail(@PathVariable String email) {
        List<ItineraryRequest> I1 = itineraryService.getItineraryByemail(email);
        if(I1 != null) {
            return ResponseEntity.ok(I1);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
