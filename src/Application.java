import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 *
 *
 * @author Kenny Park
 * @version 
 */
public class Application {
    static float startTimeMS;
    private static Profile localProfile;

    public static void main(String[] args) {
        startTimeMS = System.currentTimeMillis();

        Client client = new Client();

        // Wait until connection is established
        float timePassedMS = System.currentTimeMillis() - startTimeMS;
        while(!Client.instance.isConnected() && timePassedMS < 10000) {
            timePassedMS = System.currentTimeMillis() - startTimeMS;
        }

        new AppLandingFrame();
    }

    public static void openMainGUI() {
        new MainAppFrame();
    }

    public static void updateGUI() {
        FeedPanel.updateGUI();
        MainAppFrame.updateGUI();
    }

    public static String getTimeStamp() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm");
        return dateFormat.format(date);
    }

    public static void setLocalProfileID(int ID) {
        System.out.println("Local profile ID set to " + ID);
        localProfile.setProfileID(ID);
    }

    public static String getMostPopularMuffin() {
        int[] muffinPoll = new int[Muffin.values().length];

        Set<Integer> profileIDSet = Database.getProfiles().keySet();
        for (Integer profileID : profileIDSet) {
            Profile profile = Database.getProfiles().get(profileID);
            muffinPoll[profile.getMuffinIndex()]++;
        }

        int mostPopularMuffinIndex = -1;
        for (int i = 1; i < Muffin.values().length; i++) {
            if (mostPopularMuffinIndex == -1 && muffinPoll[i] > 0) {
                mostPopularMuffinIndex = i;
            } else if (mostPopularMuffinIndex != -1 && muffinPoll[i] > muffinPoll[mostPopularMuffinIndex]) {
                mostPopularMuffinIndex = i;
            }
        }
        if (mostPopularMuffinIndex == -1) {
            return "insufficient data";
        }

        return Muffin.values()[mostPopularMuffinIndex].label;
    }

    public static void setLocalProfile(Profile profile) {
        localProfile = profile;
    }

    public static Profile getLocalProfile() {
        if (localProfile != null) {
            return localProfile;
        } else {
            return new Profile();
        }
    }
}
