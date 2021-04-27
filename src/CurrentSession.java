import java.util.ArrayList;

/**
 *
 *
 * @author Kenny Park
 * @version 
 */
public class CurrentSession {
    public static CurrentSession instance;
    private static ArrayList<Post> posts = new ArrayList<>();
//    private ArrayList<>

    public static void testPopulatePosts(){

    }

    public static ArrayList<Post> getPosts() {
        return posts;
    }

    // Insert post
    public static void addPost(Post post) {
        posts.add(0, post);
    }

    public static void setPost(int postID, Post post) {

    }

    public static String getMostPopularMuffin(){
        return "BRAN";
    }

}
