import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestConsumerService {

    private final RestTemplate restTemplate;

    public RestConsumerService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    // Example 1: GET request
    public String getExample(String apiUrl) {
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            // Handle error appropriately (e.g., throw exception, log, return null)
            System.err.println("Error: " + response.getStatusCode());
            return null;
        }
    }


    // Example 2: GET request with parameters
    public String getWithParams(String apiUrl, String param1, int param2) {
        // Construct URL with parameters (more robust than string concatenation)
        String urlWithParams = apiUrl + "?param1=" + param1 + "&param2=" + param2;

        ResponseEntity<String> response = restTemplate.getForEntity(urlWithParams, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            System.err.println("Error: " + response.getStatusCode());
            return null;
        }
    }


    // Example 3: POST request with request body
    public String postExample(String apiUrl, Object requestBody) {  // Object for flexibility
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); // Important for JSON

        HttpEntity<Object> request = new HttpEntity<>(requestBody, headers); // Create request entity

        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);

         if (response.getStatusCode() == HttpStatus.CREATED || response.getStatusCode() == HttpStatus.OK) { // Check appropriate success codes
            return response.getBody();
        } else {
            System.err.println("Error: " + response.getStatusCode());
            return null;
        }
    }

    // Example 4:  PUT request
    public void putExample(String apiUrl, Object requestBody, Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> request = new HttpEntity<>(requestBody, headers);

        restTemplate.put(apiUrl + "/" + id, request); // Or use exchange() for more control

    }

    // Example 5: DELETE request
    public void deleteExample(String apiUrl, Long id) {
       restTemplate.delete(apiUrl + "/" + id);
    }



    // Example 6: Using exchange() for more control (e.g., setting headers, status code checks)
    public String getWithHeaders(String apiUrl) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Custom-Header", "my-value"); // Add custom headers

        HttpEntity<?> requestEntity = new HttpEntity<>(headers); // No body for GET

        ResponseEntity<String> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                requestEntity,
                String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            System.err.println("Error: " + response.getStatusCode());
            return null;
        }
    }



}
