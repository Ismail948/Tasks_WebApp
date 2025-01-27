package com.task.Tasks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.LinkedList;
import java.util.Scanner;

public class GoogleGenerativeAI {

    private static final String API_KEY = "AIzaSyAURG7sBHG3VmUZr_H1A4dQhAKVS4IrTZI"; // Replace with your actual API key
    private static final String BASE_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent";

    public static void main(String[] args) {
        try {
            Scanner src = new Scanner(System.in);
            
            // Loop to repeatedly ask for prompts
            while (true) {
                System.out.print("Enter Prompt : ");
                String prompt = src.nextLine();
                
                // Exit the loop if the user types 'exit'
                if (prompt.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting...");
                    break;
                }

                // Create the JSON payload for the request
                String jsonPayload = "{" +
                        "\"contents\": [{" +
                        "\"parts\": [{" +
                        "\"text\": \"" + prompt + "\"" + // Insert the user prompt here
                        "}]" +
                        "}]" +
                        "}";

                // Send the POST request and get the response
                String response = sendPostRequest(BASE_URL + "?key=" + API_KEY, jsonPayload);

                // Print the response in the required format
                displayResponse(response);
            }
            src.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String sendPostRequest(String urlString, String jsonInputString) throws Exception {
        // Create a URL object
        URL url = new URL(urlString);

        // Open connection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Send the JSON payload
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Check for errors in the response code
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new Exception("API returned an error: HTTP " + responseCode);
        }

        // Read the response
        StringBuilder responseBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                responseBuilder.append(responseLine.trim());
            }
        }

        // Close the connection
        connection.disconnect();

        // Return the response
        return responseBuilder.toString();
    }

    private static void displayResponse(String response) {
        try {
            // Parse the response string into a JSON object
            JSONObject jsonResponse = new JSONObject(response);

            // Extract the content of the first candidate response
            JSONArray candidates = jsonResponse.getJSONArray("candidates");
            JSONObject candidate = candidates.getJSONObject(0);
            JSONObject content = candidate.getJSONObject("content");
            JSONArray parts = content.getJSONArray("parts");
            String aiResponse = parts.getJSONObject(0).getString("text");

            // Display the response in the required format
            System.out.println("Sulem's AI: " + "\"" + aiResponse + "\"");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
