import java.io.Serializable;
import java.util.Hashtable;
import java.util.Set;
import java.util.TreeMap;

/**
 * Contains post and profile data.
 * The server has a server instance, and a local instance exists per individual client.
 *
 * @author Kenny Park
 * @version May 1, 2021
 */
public class Database implements Serializable {
    private Hashtable<Integer, Post> posts = new Hashtable<>();
    private Hashtable<Integer, Profile> profiles = new Hashtable<>();
    private int highestPostID = 0;
    private int highestProfileID = 0;

    public void putPost(Post post) {
        int postKey = post.getPostID() == -1 ? ++highestPostID : post.getPostID();
        post.setPostID(postKey);
        posts.put(postKey, post);
        System.out.println("Post ID " + postKey + " set.");
    }

    public void putProfile(Profile profile) {
        int profileKey = profile.getProfileID() == -1 ? ++highestProfileID : profile.getProfileID();
        profile.setProfileID(profileKey);
        profiles.put(profileKey, profile);
        System.out.println("Profile ID " + profileKey + " set.");
    }

    public void deletePost(int postID) {
        posts.remove(postID);
    }

    public void deleteProfile(int profileID) {
        TreeMap<Integer, Post> sortedPosts = new TreeMap<Integer, Post>(posts);
        Set<Integer> postKeySet = sortedPosts.keySet();
        for (Integer postKey : postKeySet) {
            Post post = sortedPosts.get(postKey);
            if(post.getProfileID() == profileID) {
                deletePost(postKey);
            }
        }
        profiles.remove(profileID);
    }

    public int getHighestPostID() {
        return highestPostID;
    }

    public int getHighestProfileID() {
        return highestProfileID;
    }

    public Hashtable<Integer, Post> getPosts() {
        return posts;
    }

    public Hashtable<Integer, Profile> getProfiles() {
        return profiles;
    }

    public void setPosts(Hashtable<Integer, Post> newPosts) {
        posts = newPosts;
//        System.out.println("Posts set. Number of posts: " + newPosts.size());
    }

    public void setProfiles(Hashtable<Integer, Profile> newProfiles) {
        profiles = newProfiles;
//        System.out.println("Profiles set. Number of profiles: " + profiles.size());
    }

    public Post getPostByID(int postID) throws PostNotFoundException {
        if (!posts.containsKey(postID)) {
            throw new PostNotFoundException("Post with ID " + postID + " not found.");
        }
        return posts.get(postID);
    }

    public Profile getProfileByID(int profileID) throws ProfileNotFoundException {
        if (!profiles.containsKey(profileID)) {
            throw new ProfileNotFoundException("Profile with ID " + profileID + " not found.");
        }
        return profiles.get(profileID);
    }

    public Profile getProfileByIdentifier(String identifier) throws ProfileNotFoundException {
        Set<Integer> profileIDSet = profiles.keySet();
        for (Integer profileID : profileIDSet) {
            Profile profile = profiles.get(profileID);
            if (profile.getIdentifier().equals(identifier)) {
                return profile;
            }
        }

        throw new ProfileNotFoundException("Profile with identifier " + identifier + " not found.");
    }

    public Comment getCommentByID(int postID, int commentID) throws CommentNotFoundException {
        Post post = null;
        try {
            post = getPostByID(postID);
        } catch (PostNotFoundException e) {
            throw new CommentNotFoundException("Comment with ID " + commentID + " not found in post with ID " + postID);
        }
        return post.getComments().get(commentID);
    }

    public boolean isIdentifierUnique(String identifier) {
        Set<Integer> profileIDSet = profiles.keySet();
        for (Integer profileID : profileIDSet) {
            Profile profile = profiles.get(profileID);
            if (profile.getIdentifier().equals(identifier)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPasswordCorrect(String identifier, String password) {
        Set<Integer> profileIDSet = profiles.keySet();
        for (Integer profileID : profileIDSet) {
            Profile profile = profiles.get(profileID);
            if (profile.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
