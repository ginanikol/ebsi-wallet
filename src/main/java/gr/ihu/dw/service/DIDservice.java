package gr.ihu.dw.service;

import gr.ihu.dw.dao.DIDdata;
import gr.ihu.dw.dao.DIDdataRepository;
import gr.ihu.dw.dao.JWKdata;
import gr.ihu.dw.dao.JWkdataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class DIDservice {

    private final JWkdataRepository jWkdataRepository;
    private final DIDdataRepository diDdataRepository;
    private final Logger logger = LoggerFactory.getLogger(DIDservice.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

    public DIDservice(JWkdataRepository jWkdataRepository, DIDdataRepository diDdataRepository) {

        this.jWkdataRepository = jWkdataRepository;
        this.diDdataRepository = diDdataRepository;
    }

    public List<JWKdata> findJWKs() {
        List<JWKdata> jwks = jWkdataRepository.findAll();
        return jwks;

    }

    public Optional<JWKdata> getMostRecentRecord() {
        //get the last jwk from db
        Optional<JWKdata> mostRecentRecord = jWkdataRepository.findFirstByOrderByTimestampDesc();
        return mostRecentRecord;
        //call node js to return a DID
        // save DID in DB
    }

    public void saveDID() {
        // Get the most recent JWK from the database
        Optional<JWKdata> mostRecentRecord = jWkdataRepository.findFirstByOrderByTimestampDesc();

        if (mostRecentRecord.isPresent()) {
            // Construct the URL for the HTTP POST request
            String url = "http://localhost:8000/create-did-from-jwk";

            // Construct the JSON payload
            String jwkJson = mostRecentRecord.get().getValue(); // "value" contains the JWK JSON

            // Create an instance of the HttpClient
            HttpClient client = HttpClient.newHttpClient();

            // Create an HTTP POST request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jwkJson))
                    .build();

            try {
                // Send the HTTP POST request and receive the response
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                // Check the response status code
                int statusCode = response.statusCode();
                String responseBody = response.body();

                if (statusCode == 200) {
                    // Handle a successful response
                    System.out.println("HTTP POST request was successful.");
                    System.out.println("Response Body: " + responseBody);
                    logger.info("***Response Body:*** " + responseBody);
                    saveDIDrecord(responseBody);

                    // Save the DID in the database (you need to implement this part)
                } else {
                    // Handle an error response
                    System.err.println("HTTP POST request failed with status code " + statusCode);
                    System.err.println("Response Body: " + responseBody);
                }
            } catch (Exception e) {
                // Handle exceptions
                e.printStackTrace();
            }
        } else {
            System.err.println("No most recent JWK found in the database.");
        }

    }

    private void saveDIDrecord(String responseBody) {
        DIDdata did = new DIDdata();
        did.setValue(responseBody.substring(8)); //remove the "did : " from the response
        did.setTimestamp(LocalDateTime.parse(LocalDateTime.now().format(formatter)));
        diDdataRepository.save(did);
    }


}
