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
public class PostGUI extends JPanel {
    public PostGUI(int postID) {
        Post post = new Post();
        try {
            post = Client.database.getPostByID(postID);
        } catch (PostNotFoundException postNotFoundException) {
            postNotFoundException.printStackTrace();
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(5, 2, 0, 2));

        // Info panel
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.LINE_AXIS));
        titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Subject label
        JLabel subjectLabel = new JLabel(post.getSubject());
        subjectLabel.setFont(Style.FONT_HEADER);
        titlePanel.add(subjectLabel);

        // Separator label
        JLabel separatorLabel = new JLabel("  -  ");
        separatorLabel.setFont(Style.FONT_SMALL);
        titlePanel.add(separatorLabel);

        // Identifier label
        Profile profile = new Profile();
        try {
            profile = Client.database.getProfileByID(post.getProfileID());
        } catch (ProfileNotFoundException e) {
            e.printStackTrace();
        }
        int profileID = profile.getProfileID();
        String identifier = profile.getIdentifier();
        String muffin = Muffin.values()[profile.getMuffinIndex()].label;
        String timeStamp = post.getTimeStamp();
        String identifierStr = identifier + " (" + muffin + "), " + timeStamp;
        JLabel identifierLabel = new JLabel(identifierStr);
        identifierLabel.setFont(Style.FONT_SMALL);
        titlePanel.add(identifierLabel);
        identifierLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new UserPostsDialog(profileID);
            }
        });

        titlePanel.add(Box.createHorizontalGlue());

        boolean isOwner = Application.getLocalProfile().getProfileID() == post.getProfileID();
        if (isOwner) {
            // Edit button
            JButton editButton = new JButton("<html><u>edit</u></html>");
            editButton.setFont(Style.FONT_SMALL);
            editButton.setMaximumSize(new Dimension(15, 16));
            Style.styleButtonInvisibleBorder(editButton);
            titlePanel.add(editButton);
            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!PostCreationDialog.isOpen()) {
                        new PostCreationDialog(postID).setAlwaysOnTop(true);
                    }
                }
            });

            // Delete button
            JButton deleteButton = new JButton("<html><u>delete</u></html>");
            deleteButton.setFont(Style.FONT_SMALL);
            deleteButton.setMaximumSize(new Dimension(15, 16));
            Style.styleButtonInvisibleBorder(deleteButton);
            titlePanel.add(deleteButton);
            Post finalPost = post;
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Client.instance.sendPacketToServer(new Packet(Packet.PacketType.DELETE_POST_QUERY, finalPost));
                }
            });
        }

        add(titlePanel);

        // Body area
        JTextArea bodyArea = new JTextArea(4, 1);
        bodyArea.setText(post.getBody());
        bodyArea.setAlignmentX(Component.LEFT_ALIGNMENT);
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
