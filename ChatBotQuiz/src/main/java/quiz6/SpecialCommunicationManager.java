package quiz6;

import quiz5.CommunicationManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class SpecialCommunicationManager extends CommunicationManager {
    private final String commonServiceUrl;
    private final String specialServiceUrl;

    public SpecialCommunicationManager(String commonServiceUrl, String specialServiceUrl) {
        this.commonServiceUrl = commonServiceUrl;
        this.specialServiceUrl = specialServiceUrl;
    }

    @Override
    public String sendMessage(String userMessage, List<String> chatHistory) {
        String serviceUrl = determineServiceUrl(userMessage, chatHistory);

        try {
            URL url = new URL(serviceUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            String jsonInputString = buildJsonInput(userMessage, chatHistory);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    return response.toString();
                }
            } else {
                System.out.println("HTTP error code: " + responseCode);
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String determineServiceUrl(String userMessage, List<String> chatHistory) {
        if (userMessage.toLowerCase().contains("help")) {
            return specialServiceUrl;
        }
        for (String message : chatHistory) {
            if (message.toLowerCase().contains("help")) {
                return specialServiceUrl;
            }
        }
        return commonServiceUrl;
    }

    private String buildJsonInput(String userMessage, List<String> chatHistory) {
        StringBuilder jsonInputString = new StringBuilder();
        jsonInputString.append("{\"message\":\"").append(userMessage).append("\", \"history\":[");

        for (int i = 0; i < chatHistory.size(); i++) {
            jsonInputString.append("\"").append(chatHistory.get(i)).append("\"");
            if (i < chatHistory.size() - 1) {
                jsonInputString.append(",");
            }
        }

        jsonInputString.append("]}");
        return jsonInputString.toString();
    }
}

