import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Set;

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

        // Populate the feed
        Hashtable<Integer, Post> postHashtable = Client.database.getPosts();
        Set<Integer> postIDSet = postHashtable.keySet();
        System.out.println("Number of posts: " + postIDSet.size());
        for(int postID : postIDSet) {
            PostGUI postGUI = new PostGUI(postID);
            add(postGUI);
            try {
                // Add comments
                Post post = Client.database.getPostByID(postID);
                Set<Integer> commentIDSet = post.getComments().keySet();
                for (int commentID : commentIDSet) {
                    CommentGUI commentGUI = new CommentGUI(postID, commentID);
                    add(commentGUI);
                }

            } catch (PostNotFoundException e) {
                e.printStackTrace();
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
                            new CommentCreationDialog(postID);
                        }
                    }
                }
            });
            addCommentPanel.add(Box.createHorizontalGlue());
            add(addCommentPanel);
        }

        // No posts
        if (postIDSet.size() == 0) {
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
