package dev.deyve;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class Hello implements RequestHandler<Map<String, Object>, String> {

    @Override
    public String handleRequest(Map<String, Object> input, Context context) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {

            // Convert input to JSON string (assuming input is a JSON object or can be serialized to JSON)
            String jsonString = objectMapper.writeValueAsString(input);

            // Process the JSON string as needed
            // Example: Deserialize JSON to a specific Java object
            Person person = objectMapper.readValue(jsonString, Person.class);

            // Perform operations
            return "Hello " + person.getName() + "!";

        } catch (Exception e) {
            context.getLogger().log("Error processing JSON: " + e.getMessage());
            return "{\"status\":\"error\"}";
        }
    }

    public static class Person {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
