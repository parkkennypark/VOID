import javax.swing.*;
import java.awt.color.CMMException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Set;

/**
 * Handles
 *
 * @author Kenny Park
 */
public class LocalDatabase {
    private static final Hashtable<Integer, Post> localPosts = new Hashtable<>();
    private static final Hashtable<Integer, Profile> localProfiles = new Hashtable<>();

    private static String tempIdentifier = "Kenny Park";
    private static String tempMuffin = "bran";
//    private ArrayList<>

    public static void testPopulateProfiles() {
        Profile profile1 = new Profile(0);
        Profile profile2 = new Profile(1);
        Profile profile3 = new Profile(1);
        localProfiles.put(1, profile1);
        localProfiles.put(2, profile2);
        localProfiles.put(3, profile3);
        System.out.println(getMostPopularMuffin());
    }

    public static Hashtable<Integer, Post> getPosts() {
        return localPosts;
    }

    // Insert post
    public static void sendPostToServer(int postID, String subject, String body) {
        Post post = new Post(postID, -1, subject, body, getTimeStamp());
        String postStr = post.toString();

        // TODO: actually send it lol
        System.out.println("Sent post " + post.getPostID());
        receivePostStrFromServer(postStr);
    }

    public static void receivePostStrFromServer(String postStr) {
        Post incomingPost = new Post(postStr);

        // TODO: post ID will be set by the server, this is for testing !!
        incomingPost.setPostID(localPosts.size());

        int postID = incomingPost.getPostID();

        localPosts.put(postID, incomingPost);
        System.out.println(localPosts.get(postID));
        System.out.println("Received post " + incomingPost.getPostID());

        FeedPanel.instance.updateFeed();
        MainGUI.updateGUI();
    }

    public static void sendCommentToServer(int postID, int commentID, String text) {
        Comment comment = new Comment(postID, commentID, text);
        String commentStr = comment.toString();

        Post post = null;
        try {
            post = getPostByID(postID);
        } catch (PostNotFoundException e) {
            e.printStackTrace();
            return;
        }

//        post.addComment()
    }

    public static void receiveCommentStrFromServer(String commentStr) {
        Comment incomingComment = new Comment(commentStr);

    }

    public static Post getPostByID(int postID) throws PostNotFoundException {
        if(!localPosts.containsKey(postID)) {
            throw new PostNotFoundException("Post with ID " + postID + " not found.");
        }
        return localPosts.get(postID);
    }

    public static Comment getCommentByID(int postID, int commentID) throws CommentNotFoundException{
        Post post = null;
        try {
            post = getPostByID(postID);
        } catch (PostNotFoundException e) {
            e.printStackTrace();
        }

        // TODO: get comment from post
        return null;
    }

    public static String getMostPopularMuffin() {
        int[] muffinPoll = new int[Muffin.values().length];

        Set<Integer> profileIDSet = localProfiles.keySet();
        for(Integer profileID : profileIDSet) {
            Profile profile = localProfiles.get(profileID);
            muffinPoll[profile.getMuffinIndex()]++;
        }

        int mostPopularMuffinIndex = -1;
        for(int i = 1; i < Muffin.values().length; i++) {
            if(mostPopularMuffinIndex == -1 && muffinPoll[i] > 0) {
                mostPopularMuffinIndex = i;
            }
            else if(mostPopularMuffinIndex != -1 && muffinPoll[i] > muffinPoll[mostPopularMuffinIndex]) {
                mostPopularMuffinIndex = i;
            }
        }
        if(mostPopularMuffinIndex == -1) {
            return "insufficient data";
        }

        return Muffin.values()[mostPopularMuffinIndex].label;
    }

    public static String getTimeStamp() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return dateFormat.format(date);
    }

}
