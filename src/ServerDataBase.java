import java.util.ArrayList;
public class ServerDataBase {
    private ArrayList<Post> posts = new ArrayList<Post>();
    private ArrayList<Profile> profiles = new ArrayList<Profile>();

    public ServerDataBase(ArrayList<Post> posts, ArrayList<Profile> profiles) {
        this.posts = new ArrayList<Post>(posts);
        this.profiles = new ArrayList<Profile>(profiles);
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public void addProfile(Profile profile) {
        profiles.add(profile);
    }

//    public boolean isIdentifierBeingUsed(String identifier) {
//        int i = 0;
//        while(i < profiles.size()) {
//            if(profiles.get(i).getIdentifier().equals(identifier)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public boolean authenticateProfile(String identifier, String password) {
//        int i = 0;
//        while(i < profiles.size()) {
//            if(profiles.get(i).getIdentifier().equals(identifier)) {
//                if(profiles.get(i).getPassword().equals(password)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    public void editPost(int postID, Post post) {
//        int i = 0;
//        while(i < posts.size()) {
//            if(posts.get(i).getPostID() == postID) {
//                posts.get(i).setSubject(post.getSubject());
//                posts.get(i).setBody(post.getBody());
//            }
//        }
//    }
//
//    /*public void editProfile(String identifer, Profile profile) {
//        int i = 0;
//        while(i < profiles.size()) {
//            if(profiles.get(i).getIdentifier().equals(identifer)) {
//                profiles.set(i, profile);
//            }
//        }
//    }*/
//
//    public void deletePost(int postID) {
//        int i = 0;
//        while(i < posts.size()) {
//            if(posts.get(i).getPostID() == postID) {
//                posts.remove(i);
//            }
//        }
//    }
//
//    public void deleteProfile(String identifier) {
//        int i = 0;
//        while(i < profiles.size()) {
//            if(profiles.get(i).getIdentifier.equals(identifier)) {
//                profiles.remove(i);
//            }
//        }
//    }
//
//    public void deleteAllPostsFromProfile(String identifier) {
//        int i = 0;
//        while(i < posts.size()) {
//            if(posts.get(i).getIdentifier().equals(identifier)) {
//                posts.remove(i);
//            }
//        }
//    }
}