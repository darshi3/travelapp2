package com.example.travelapp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ItineraryService {

    // mongodb env variable



    final Dotenv dotenv = Dotenv.load();
    @Autowired
    private ItineraryRepository itineraryRepository;
    @Autowired
   private ItineraryRequest itineraryRequest;
    public String generateItinerary(ItineraryRequest request) {
        // Call to ChatGPT or your logic to generate itinerary
        // Example response
        return "For your " + request.getDuration() + "-day trip to " + request.getDestination() + ", I suggest visiting the Eiffel Tower, exploring the Louvre, and taking a day trip to Versailles.";
    }

    public ItineraryRequest saveItinerary(ItineraryRequest itinerary) {
        return itineraryRepository.save(itinerary);
    }

    public List<ItineraryRequest> getAllItineraries() {
        return itineraryRepository.findAll();
        //itineraryRepository.findByDestination("USA");
    }

    public List<ItineraryRequest> getItineraryByemail(String email) {
        // Integer.toString(id);
        // System.out.println("id first is "+ itineraryRepository.findById("1"));
        return itineraryRepository.findAllByEmail(email);
    }

    public ItineraryRequest getItineraryByDestination(String destination) {
        // Integer.toString(id);
       // System.out.println("id first is "+ itineraryRepository.findById("1"));
        return itineraryRepository.findByDestination(destination);
    }

    public ItineraryRequest getItineraryById(String eid) {
       // Integer.toString(id);
        System.out.println("id first is "+ itineraryRepository.findById(eid));
        return itineraryRepository.findById(eid).orElse(null);
    }

    public String generate(ItineraryRequest request)  {
        String prompt = String.format(
                "Generate a travel itinerary for a %d-day trip to %s. The user prefers %s. Provide a detailed itinerary including all necessary details in maxtoken %d. Complete the sentences especially at end.",
                request.getDuration(), request.getDestination(), request.getPreference(), 300);
        System.out.println("prompt is "+ prompt);

            String generatedItinerary = callOpenAiApi(prompt);
            System.out.println("AAAA  generatedItinerary"+ generatedItinerary);

            // Save the details to database
        request.setGenerateditineraries(generatedItinerary);
        itineraryRepository.save(request);
        return generatedItinerary;

    }

    private String callOpenAiApi(String prompt) {

        String openAiApiKey = System.getenv("openAiApiKey");

       // String openAiApiKey = dotenv.get("OPENAIAPIKEY");


        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + openAiApiKey);
        headers.set("Content-Type", "application/json");


        String requestBody = String.format(
                "{\"model\":\"gpt-3.5-turbo\",\"messages\":[{\"role\":\"user\",\"content\":\"%s\"}],\"max_tokens\":300}",
                prompt
        );



        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                "https://api.openai.com/v1/chat/completions",
                HttpMethod.POST,
                entity,
                String.class
        );


        String generatedText = extractGeneratedText(response.getBody());
        return generatedText;
    }

    private String extractGeneratedText(String responseBody) {
        // Extract and return the generated itinerary from the response body.
        // Implement JSON parsing based on the structure of the OpenAI API response.
        // This is a placeholder; you'll need to use a JSON parser like Jackson or Gson.
        // Create an ObjectMapper instance
        try {
        ObjectMapper mapper = new ObjectMapper();

        // Parse the JSON response
        JsonNode rootNode = mapper.readTree(responseBody);

        // Navigate through the JSON tree to extract the generated text
        JsonNode choicesNode = rootNode.path("choices");
        if (choicesNode.isArray() && choicesNode.size() > 0) {
            JsonNode firstChoice = choicesNode.get(0);
            JsonNode messageNode = firstChoice.path("message");
          //  itineraryRepository.save(responseBody);


            System.out.println("THE PLAIN TEXT IS "+ messageNode.path("content").asText());
            return messageNode.path("content").asText();

        }

        return "No content found";
    } catch (Exception e) {
        e.printStackTrace();
        return "Error parsing response";
    }
      //  return responseBody; // Simplified for example purposes
    }
}
