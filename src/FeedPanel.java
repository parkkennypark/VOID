import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * The panel that stores all the feed info.
 *
 * @author Kenny Park
 * @version April 27, 2021
 */
public class FeedPanel extends JPanel {
    public static FeedPanel instance;


    public static void updateGUI() {
        if(instance != null) {
            instance.populateFeed();
        }
    }

    public FeedPanel() {
        instance = this;
        populateFeed();
    }

    public void populateFeed() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        removeAll();

        // Populate the feed. Treemap for sort.
        TreeMap<Integer, Post> sortedPosts = new TreeMap<Integer, Post>( Client.database.getPosts() );
        Set<Integer> postKeySet = sortedPosts.descendingKeySet();
        System.out.println("Number of posts: " + sortedPosts.size());
        for (Integer postKey : postKeySet) {
            Post post = sortedPosts.get(postKey);
            PostGUI postGUI = new PostGUI(post.getPostID());
            add(postGUI);

            TreeMap<Integer, Comment> sortedComments = new TreeMap<Integer, Comment>(post.getComments());
            Set<Integer> commentKeySet = sortedComments.keySet();
            for(Integer commentKey : commentKeySet) {
                Comment comment = sortedComments.get(commentKey);
                CommentGUI commentGUI = new CommentGUI(post.getPostID(), comment.getCommentID());
                add(commentGUI);
            }

            // Add comment button
            JPanel addCommentPanel = new JPanel();
            addCommentPanel.setLayout(new BoxLayout(addCommentPanel, BoxLayout.LINE_AXIS));
            addCommentPanel.setBorder(new EmptyBorder(0, 15, 0, 0));

            JButton addCommentButton = new JButton("<html><u>add comment</u></html>");
            addCommentButton.setFont(Style.FONT_SMALL);
            addCommentButton.setMaximumSize(new Dimension(80, 16));
            Style.styleButtonInvisibleBorder(addCommentButton);
            addCommentPanel.add(addCommentButton);
            addCommentButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!PostCreationDialog.isOpen()){
                        if(!CommentCreationDialog.isOpen()) {
                            new CommentCreationDialog(post.getPostID());
                        }
                    }
                }
            });
            addCommentPanel.add(Box.createHorizontalGlue());
            add(addCommentPanel);
        }

        // No posts
        if (sortedPosts.size() == 0) {
            System.out.println("No posts to add.");
            add(Box.createRigidArea(new Dimension(0, 10)));

            JLabel nothingLabel = new JLabel("There's nothing here.");
            nothingLabel.setFont(Style.FONT_SMALL);
            nothingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(nothingLabel);
        }

        System.out.println("Feed updated.");
    }
}
