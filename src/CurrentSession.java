import javax.swing.*;
import java.util.Hashtable;

/**
 * Handles
 *
 * @author Kenny Park
 */
public class CurrentSession {
    private static Hashtable<Integer, Post> localPosts = new Hashtable<>();

    private static String tempIdentifier = "Kenny Park";
    private static String tempMuffin = "bran";
//    private ArrayList<>

    public static void testPopulatePosts() {

    }

    public static Hashtable<Integer, Post> getPosts() {
        return localPosts;
    }

    // Insert post
    public static void sendPostToServer(int postID, String subject, String body) {
        Post post = new Post(postID, tempIdentifier, tempMuffin, subject, body, "");
        String postStr = post.toString();

        // TODO: actually send it lol
        System.out.println("Sent post " + post.getPostID());
        receivePostStrFromServer(postStr);
    }

    public static void receivePostStrFromServer(String postStr) {
        Post incomingPost = new Post(postStr);
        incomingPost.setPostID(localPosts.size());

        int postID = incomingPost.getPostID();

        localPosts.put(postID, incomingPost);
        System.out.println(localPosts.get(postID));
        System.out.println("Received post " + incomingPost.getPostID());

        FeedPanel.instance.updateFeed();
        MainGUI.updateGUI();
    }

    public static Post getPostByID(int postID) throws PostNotFoundException {
        if(!localPosts.contains(postID)) {
//            throw new PostNotFoundException("Post with ID " + postID + " not found.");
        }
        return localPosts.get(postID);
    }

    public static String getMostPopularMuffin() {
        return "BRAN";
    }

}
