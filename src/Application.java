import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
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

        Set<Integer> profileIDSet = Client.database.getProfiles().keySet();
        for (Integer profileID : profileIDSet) {
            Profile profile = Client.database.getProfiles().get(profileID);
            muffinPoll[profile.getMuffinIndex()]++;
        }

        String muffinStr = "";
        int mostPopularMuffinIndex = -1;
        int highestTally = 0;
        for (int i = 0; i < Muffin.values().length; i++) {
            int currentTally = muffinPoll[i];
            if (mostPopularMuffinIndex == -1 && currentTally > 0) {
                mostPopularMuffinIndex = i;
                muffinStr = Muffin.values()[i].label;
                highestTally = currentTally;
            } else if (mostPopularMuffinIndex != -1) {
                if(currentTally > highestTally) {
                    muffinStr = Muffin.values()[i].label;
                    highestTally = currentTally;
                } else if(currentTally == highestTally) {
                    muffinStr += ", " + Muffin.values()[i].label;
                }
                mostPopularMuffinIndex = i;
            }
        }
        if (mostPopularMuffinIndex == -1) {
            return "none?";
        }
        return muffinStr;
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
