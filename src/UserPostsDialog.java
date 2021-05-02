import javax.swing.*;
import java.awt.*;
import java.util.Set;
import java.util.TreeMap;

/**
 * A frame that displays all posts and comments from a user.
 *
 * @author Kenny Park
 * @version May 2, 2021
 */
public class UserPostsDialog extends JDialog {

    public UserPostsDialog(int profileID) {
        Profile profile = new Profile();
        try {
            profile = Client.database.getProfileByID(profileID);
        } catch (ProfileNotFoundException e) {
            e.printStackTrace();
        }

        setSize(500, 400);
        setTitle("Posts by " + profile.getIdentifier());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(Style.PADDING_SUBMENU);

        // Populate the panel. Treemap for sort.
        TreeMap<Integer, Post> sortedPosts = new TreeMap<Integer, Post>(Client.database.getPosts());
        Set<Integer> postKeySet = sortedPosts.descendingKeySet();
        System.out.println("Number of posts: " + sortedPosts.size());
        for (Integer postKey : postKeySet) {
            Post post = sortedPosts.get(postKey);
            if (post.getProfileID() == profileID) {
                PostGUI postGUI = new PostGUI(post.getPostID());
                postGUI.setAlignmentX(Component.CENTER_ALIGNMENT);
                panel.add(postGUI);
                panel.add(Box.createRigidArea(new Dimension(0, 8)));
            }

            TreeMap<Integer, Comment> sortedComments = new TreeMap<Integer, Comment>(post.getComments());
            Set<Integer> commentKeySet = sortedComments.keySet();
            for (Integer commentKey : commentKeySet) {
                Comment comment = sortedComments.get(commentKey);
                if (comment.getProfileID() == profileID) {
                    JLabel replyingToLabel = new JLabel("Comment on \"" + post.getSubject() + "\":");
                    replyingToLabel.setFont(Style.FONT_SMALL);
                    replyingToLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    panel.add(replyingToLabel);
                    CommentGUI commentGUI = new CommentGUI(post.getPostID(), comment.getCommentID());
                    commentGUI.setBorder(null);
                    commentGUI.setAlignmentX(Component.CENTER_ALIGNMENT);
                    panel.add(commentGUI);
                    panel.add(Box.createRigidArea(new Dimension(0, 8)));
                }
            }
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane);

        setVisible(true);
    }
}
