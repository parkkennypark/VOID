/**
 *
 *
 */
public class RunLocalTest {
    /**
     * Application (Danilo)
     * Client (Kenny)
     * Comment (Moosh)
     * Database (Kenny)
     * Packet (Kenny)
     * Post (Hao)
     * Profile (Danilo)
     * Server (Kenny)
     */

    public static void main(String[] args) {



    }

    private static void testProfileClass() {
        int testID = -1;
        String testIdentifier = "Test identifier";
        String testPassword = "Test password";
        int testMuffinIndex = 1;
        Profile profile = new Profile(testID, testIdentifier, testPassword, testMuffinIndex);

        System.out.println("Testing getIdentifier");
        System.out.println("Expected output: " + testIdentifier);
        System.out.println("Received output: " + profile.getIdentifier());

        profile.setProfileID(5);
    }
}
