import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Set;

/**
 * Handles
 *
 * @author Kenny Park
 */
public class LocalDatabase extends Database {
    private static Profile localProfile;

    public static void receivePostStrFromServer(String postStr) {
        Post incomingPost = new Post(postStr);

        // TODO: post ID will be set by the server, this is for testing !!
        incomingPost.setPostID((int) (Math.random() * 100));


        putPost(incomingPost);
        System.out.println("Received post " + incomingPost.getPostID());


        FeedPanel.instance.updateFeed();
        MainAppFrame.updateGUI();
    }


    public static void receiveCommentStrFromServer(String commentStr) {
        Comment incomingComment = new Comment(commentStr);

    }


    public static String getMostPopularMuffin() {
        int[] muffinPoll = new int[Muffin.values().length];

        Set<Integer> profileIDSet = getProfiles().keySet();
        for (Integer profileID : profileIDSet) {
            Profile profile = getProfiles().get(profileID);
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
