package quiz5;

public class Main {
    public static void main(String[] args) {
        // Use quiz5.DummyCommunicationManager for testing
        CommunicationManager communicationManager = new DummyCommunicationManager();

        // If you want to use the real quiz5.CommunicationManager, uncomment the line below
        // quiz5.CommunicationManager communicationManager = new quiz5.CommunicationManager();

        UserInteractionManager userInteractionManager = new UserInteractionManager(communicationManager);
        userInteractionManager.startChat();
    }
}

