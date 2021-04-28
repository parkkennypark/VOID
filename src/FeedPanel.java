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


    public FeedPanel() {
        updateFeed();
        instance = this;
    }

    public void updateFeed() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        removeAll();

        // Populate the feed
        Hashtable<Integer, Post> postHashtable = CurrentSession.getPosts();
        Set<Integer> postIDSet = postHashtable.keySet();
        Integer[] postIDArray = new Integer[postIDSet.size()];
        postIDArray = postIDSet.toArray(postIDArray);
        for (int i = postIDArray.length - 1; i >= 0; i--) {
            int postID = postIDArray[i];
            System.out.println("Adding post " + postID + " to feed.");
            PostGUI postGUI = new PostGUI(postID);
            add(postGUI);
            // TODO: for each comment ...
//            feedPanel.add(new CommentGUI(postID, 0));
//        feedPanel.add(new PostGUI(0));
//        feedPanel.add(new CommentGUI(0, 0));
//        feedPanel.add(new CommentGUI(0, 0));

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
                    if (!PostCreationFrame.isOpen()) {
//                    new NewPostFrame(post).setAlwaysOnTop(true);
                    }
                }
            });
            addCommentPanel.add(Box.createHorizontalGlue());
            add(addCommentPanel);
        }

        // No posts
        if (postIDArray.length == 0) {
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
