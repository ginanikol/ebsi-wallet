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
import java.util.List;
import java.util.Optional;

import static gr.ihu.dw.util.Utils.formatDate;

@Service
public class DIDservice {

    private final JWkdataRepository jWkdataRepository;
    private final DIDdataRepository diDdataRepository;
    private final Logger logger = LoggerFactory.getLogger(DIDservice.class);

    public DIDservice(JWkdataRepository jWkdataRepository, DIDdataRepository diDdataRepository) {

        this.jWkdataRepository = jWkdataRepository;
        this.diDdataRepository = diDdataRepository;
    }

    public List<DIDdata> fetchDIDs() {
        List<DIDdata> dids = diDdataRepository.findAll();
        return dids;
    }


//    public void resolveDID(String did) {
//        String url = "http://localhost:8000/resolve-did/" + did;
//
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(url))
//                .GET()
//                .build();
//
//        try {
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//
//            int statusCode = response.statusCode();
//            String responseBody = response.body();
//
//            if (statusCode == 200) {
//                System.out.println("HTTP GET request was successful.");
//                System.out.println("Resolved DID Document: " + responseBody);
//            } else {
//                System.err.println("HTTP GET request failed with status code " + statusCode);
//                System.err.println("Response Body: " + responseBody);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    public void createDID() {
        Optional<JWKdata> mostRecentRecord = jWkdataRepository.findFirstByOrderByTimestampDesc();

        if (mostRecentRecord.isPresent()) {
            // Construct the URL for the HTTP POST request to the noje js server
            String url = "http://localhost:8000/create-did-from-jwk";

            String jwkJson = mostRecentRecord.get().getValue(); // "value" contains the JWK JSON

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jwkJson))
                    .build();

            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                int statusCode = response.statusCode();
                String responseBody = response.body();

                if (statusCode == 200 || statusCode == 201) {
                    System.out.println("HTTP POST request was successful.");
                    System.out.println("Response Body: " + responseBody);
                    logger.info("***Response Body:*** " + responseBody);
                    saveDID(responseBody);
                } else {
                    System.err.println("HTTP POST request failed with status code " + statusCode);
                    System.err.println("Response Body: " + responseBody);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("No most recent JWK found in the database.");
        }

    }

    private void saveDID(String responseBody) {
        DIDdata did = new DIDdata();
        String value = processResponse(responseBody);
        did.setValue(value);
        did.setTimestamp(formatDate(LocalDateTime.now()));
        diDdataRepository.save(did);
    }

    private static String processResponse(String responseBody) {
        String value = responseBody.substring(8); //remove the {"did:" from the response
        value = value.substring(0, value.length() - 2); // remove the last two chars "}
        return value;
    }


}
