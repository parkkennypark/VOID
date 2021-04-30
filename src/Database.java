import java.util.Hashtable;
import java.util.Set;

/**
 * @author Kenny Park
 */
public class Database {
    private static final Hashtable<Integer, Post> posts = new Hashtable<>();
    private static final Hashtable<Integer, Profile> profiles = new Hashtable<>();
    private static int highestPostID = 0;
    private static int highestProfileID = 0;

    public static void putPost(Post post) {
        int postKey = post.getPostID() == -1 ? ++highestPostID : post.getPostID();
        posts.put(postKey, post);
    }

    public static void putProfile(Profile profile) {
        int profileKey = profile.getProfileID() == -1 ? ++highestProfileID : profile.getProfileID();
        profiles.put(profileKey, profile);
    }

    public static Hashtable<Integer, Post> getPosts() {
        return posts;
    }

    public static Hashtable<Integer, Profile> getProfiles() { return profiles; }

    public static Post getPostByID(int postID) throws PostNotFoundException {
        if (!posts.containsKey(postID)) {
            throw new PostNotFoundException("Post with ID " + postID + " not found.");
        }
        return posts.get(postID);
    }

    public static Profile getProfileByID(int profileID) throws ProfileNotFoundException {
        if (!profiles.containsKey(profileID)) {
            throw new ProfileNotFoundException("Profile with ID " + profileID + " not found.");
        }
        return profiles.get(profileID);
    }

    public static Profile getProfileByIdentifier(String identifier) throws ProfileNotFoundException {
        Set<Integer> profileIDSet = profiles.keySet();
        for(Integer profileID : profileIDSet) {
            Profile profile = profiles.get(profileID);
            if(profile.getIdentifier().equals(identifier)) {
                return profile;
            }
        }

        throw new ProfileNotFoundException("Profile with identifier " + identifier + " not found.");
    }

    public static Comment getCommentByID(int postID, int commentID) throws CommentNotFoundException {
        Post post = null;
        try {
            post = getPostByID(postID);
        } catch (PostNotFoundException e) {
            throw new CommentNotFoundException("Comment with ID " + commentID + " not found in post with ID " + postID);
        }
        // TODO: get comment from post
        return null;
    }

    public static boolean isIdentifierUnique(String identifier) {
        Set<Integer> profileIDSet = profiles.keySet();
        for(Integer profileID : profileIDSet) {
            Profile profile = profiles.get(profileID);
            if(profile.getIdentifier().equals(identifier)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPasswordCorrect(String identifier, String password) {
        Set<Integer> profileIDSet = profiles.keySet();
        for(Integer profileID : profileIDSet) {
            Profile profile = profiles.get(profileID);
            if(profile.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
