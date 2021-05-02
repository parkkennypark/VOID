import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The GUI for each individual post in the post feed.
 *
 * @author Kenny Park
 * @version May 2, 2021
 */
public class CommentGUI extends JPanel {

    public CommentGUI(int postID, int commentID) {
        Comment comment = new Comment(postID);
        try {
            comment = Client.database.getCommentByID(postID, commentID);
        } catch (CommentNotFoundException e) {
            e.printStackTrace();
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(0, 15, 0, 0));

        // Info panel
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.LINE_AXIS));
        titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Identifier label
        Profile profile = new Profile();
        try {
            profile = Client.database.getProfileByID(comment.getProfileID());
        } catch (ProfileNotFoundException e) {
            e.printStackTrace();
        }
        int profileID = profile.getProfileID();
        String identifier = profile.getIdentifier();
        String muffin = Muffin.values()[profile.getMuffinIndex()].label;
        String timeStamp = comment.getTimestamp();
        String identifierStr = identifier + " (" + muffin + "), " + timeStamp;
        JLabel identifierLabel = new JLabel(identifierStr);
        identifierLabel.setFont(Style.FONT_SMALL);
        identifierLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        titlePanel.add(identifierLabel);
        identifierLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new UserPostsDialog(profileID);
            }
        });

        titlePanel.add(Box.createHorizontalGlue());

        boolean isOwner = Application.getLocalProfile().getProfileID() == comment.getProfileID();
        if (isOwner) {
            // Edit button
            JButton editButton = new JButton("<html><u>edit</u></html>");
            editButton.setFont(Style.FONT_SMALL);
            editButton.setMaximumSize(new Dimension(20, 16));
            Style.styleButtonInvisibleBorder(editButton);
            titlePanel.add(editButton);
            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!PostCreationDialog.isOpen()) {
                        if (!CommentCreationDialog.isOpen()) {
                            new CommentCreationDialog(postID, commentID);
                        }
                    }
                }
            });

            // Delete button
            JButton deleteButton = new JButton("<html><u>delete</u></html>");
            deleteButton.setFont(Style.FONT_SMALL);
            deleteButton.setMaximumSize(new Dimension(20, 16));
            Style.styleButtonInvisibleBorder(deleteButton);
            titlePanel.add(deleteButton);
            Comment finalComment = comment;
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Client.instance.sendPacketToServer(new Packet(Packet.PacketType.DELETE_COMMENT_QUERY, finalComment));
                }
            });
        }

        add(titlePanel);

        // Body area
        JTextArea bodyArea = new JTextArea(2, 1);
        bodyArea.setText(comment.getText());
        bodyArea.setFont(Style.FONT_NORMAL);
        bodyArea.setLineWrap(true);
        bodyArea.setWrapStyleWord(true);
        bodyArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(bodyArea);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setWheelScrollingEnabled(false);
        add(scrollPane);

        setMaximumSize(new Dimension(getMaximumSize().width, getPreferredSize().height));
        setVisible(true);

    }
}
