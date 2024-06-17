package quiz5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInteractionManager {
    private final CommunicationManager communicationManager;
    private final List<String> chatHistory;

    public UserInteractionManager(CommunicationManager communicationManager) {
        this.communicationManager = communicationManager;
        this.chatHistory = new ArrayList<>();
    }

    public void startChat() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chatbot Application Started. Type 'exit' to end the chat.");

        while (true) {
            System.out.print("User: ");
            String userMessage = scanner.nextLine();

            if (userMessage.equalsIgnoreCase("exit")) {
                System.out.println("Chatbot: Goodbye!");
                break;
            }

            chatHistory.add("User: " + userMessage);
            String response = communicationManager.sendMessage(userMessage, chatHistory);

            if (response != null) {
                chatHistory.add("Chatbot: " + response);
                System.out.println("Chatbot: " + response);
            } else {
                System.out.println("Chatbot: Error retrieving response.");
            }
        }

        scanner.close();
    }
}

