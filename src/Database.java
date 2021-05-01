import java.util.Hashtable;
import java.util.Set;

/**
 * @author Kenny Park
 */
public class Database {
    private static Hashtable<Integer, Post> posts = new Hashtable<>();
    private static Hashtable<Integer, Profile> profiles = new Hashtable<>();
    private static int highestPostID = 0;
    private static int highestProfileID = 0;

    public static Post putPost(Post post) {
        int postKey = post.getPostID() == -1 ? ++highestPostID : post.getPostID();
        post.setPostID(postKey);
        posts.put(postKey, post);
        System.out.println("Post ID " + postKey + " set.");
//        return new Packet(Packet.PacketType.POST_HASHTABLE, posts);
        return post;
    }

    public static Profile putProfile(Profile profile) {
        int profileKey = profile.getProfileID() == -1 ? ++highestProfileID : profile.getProfileID();
        profile.setProfileID(profileKey);
        profiles.put(profileKey, profile);
        System.out.println("Profile ID " + profileKey + " set.");
//        return new Packet(Packet.PacketType.PROFILE_HASHTABLE, profiles);
        return profile;
    }

    public static int getHighestProfileID() {
        return highestProfileID;
    }

    public static Hashtable<Integer, Post> getPosts() {
        return posts;
    }

    public static Hashtable<Integer, Profile> getProfiles() { return profiles; }

    public static void setPosts(Hashtable<Integer, Post> newPosts) {
        posts = newPosts;
//        System.out.println("Posts set. Number of posts: " + newPosts.size());
    }

    public static void setProfiles(Hashtable<Integer, Profile> newProfiles) {
        profiles = newProfiles;
//        System.out.println("Profiles set. Number of profiles: " + profiles.size());
    }

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
        System.out.println(profiles.get(profileID));
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
