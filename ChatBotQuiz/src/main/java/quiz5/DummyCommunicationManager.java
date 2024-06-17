package quiz5;

import java.util.List;

public class DummyCommunicationManager extends CommunicationManager {
    @Override
    public String sendMessage(String userMessage, List<String> chatHistory) {
        // Dummy response based on the user's message for testing purposes
        switch (userMessage.toLowerCase()) {
            case "hello":
                return "Good day";
            case "what time is it?":
                return "9:00 AM";
            case "i should go!":
                return "Wait for me!";
            default:
                return "I didn't understand that.";
        }
    }
}

