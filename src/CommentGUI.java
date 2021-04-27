import javax.swing.*;
import javax.swing.border.Border;
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
public class CommentGUI extends JPanel {
    String sampleBody = "Neat";

    public CommentGUI(Comment comment) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(0, 15, 0, 0));

        // Info panel
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.LINE_AXIS));
        titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Identifier label
        JLabel identifierLabel = new JLabel("Kenny Park (Bran), 8:15PM | 04/24 ");
        identifierLabel.setFont(Style.FONT_SMALL);
        identifierLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        titlePanel.add(identifierLabel);
        titlePanel.add(Box.createHorizontalGlue());

        boolean isOwner = true;
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
                    if(!NewPostFrame.isOpen()){
                        // TODO change to edit comment
//                        new NewPostFrame(post).setAlwaysOnTop(true);
                    }
                }
            });

            // Delete button
            JButton deleteButton = new JButton("<html><u>delete</u></html>");
            deleteButton.setFont(Style.FONT_SMALL);
            deleteButton.setMaximumSize(new Dimension(20, 16));
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
        JTextArea bodyArea = new JTextArea(2, 1);
        bodyArea.setText(sampleBody);
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

        infoPanel.add(Box.createHorizontalGlue());


//        add(infoPanel);


        setMaximumSize(new Dimension(getMaximumSize().width, getPreferredSize().height));
        setVisible(true);

    }
}
