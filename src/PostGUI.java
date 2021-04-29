import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The GUI for each individual post in the post feed.
 *
 * @author Kenny Park
 * @version 
 */
public class PostGUI extends JPanel {
    String sampleBody = "Have you ever had a dream that you, um, you had, your, you- you could, you’ll do, you- you wants, you, you could do so, you- you’ll do, you could- you, you want, you want them to do you so much you could do anything?";

    public PostGUI(int postID) {
        Post post = new Post();
        try {
            post = LocalDatabase.getPostByID(postID);
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
        int muffinIndex = 0;
        String identifierStr = post.getProfileID() + " " + Muffin.values()[muffinIndex].label + ", " + post.getTimeStamp();
        JLabel identifierLabel = new JLabel(identifierStr);
        identifierLabel.setFont(Style.FONT_SMALL);
        titlePanel.add(identifierLabel);

        titlePanel.add(Box.createHorizontalGlue());

        boolean isOwner = true;
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
                    if(!PostCreationDialog.isOpen()){
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
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO: delete post!
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
        add(scrollPane);

        // Info panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.LINE_AXIS));
        infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Comments button
        int numComments = 5;
        JButton commentsButton = new JButton(numComments + " impressions");
        commentsButton.setFont(Style.FONT_SMALL);
        Style.styleButton(commentsButton);
        infoPanel.add(commentsButton);

        infoPanel.add(Box.createHorizontalGlue());


//        add(infoPanel);


        setMaximumSize(new Dimension(getMaximumSize().width, getPreferredSize().height));
        setVisible(true);

    }
}
