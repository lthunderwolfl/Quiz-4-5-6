package quiz6;

import quiz5.CommunicationManager;
import quiz5.UserInteractionManager;

public class Main {
    public static void main(String[] args) {
        // Initialize SpecialCommunicationManager with common and special service URLs
        CommunicationManager communicationManager = new SpecialCommunicationManager(
                "https://your-common-chatbot-api-url.com/chat",
                "https://your-special-chatbot-api-url.com/chat"
        );

        UserInteractionManager userInteractionManager = new UserInteractionManager(communicationManager);
        userInteractionManager.startChat();
    }
}
