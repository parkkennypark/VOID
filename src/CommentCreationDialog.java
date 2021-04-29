import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 *
 * @author Kenny Park
 * @version April 28, 2021
 */
public class CommentCreationDialog extends JDialog {
    static CommentCreationDialog instance;

    JTextField commentField;
    JLabel errorLabel;
    JButton commentButton;

    // Called when making a new comment
    public CommentCreationDialog(int postID) {
        setupFrame(postID, -1);
    }

    // Called when editing an existing comment
    public CommentCreationDialog(int postID, int commentID) {
        Comment comment = null;
        try {
            comment = LocalDatabase.getCommentByID(postID, commentID);
        } catch (CommentNotFoundException e) {
            e.printStackTrace();
        }
        setupFrame(postID, commentID);
        commentButton.setText("save changes");
        commentField.setText(comment.getText());
    }

    private void setupFrame(int postID, int commentID) {
        Post post = null;
        try {
            post = LocalDatabase.getPostByID(postID);
        } catch (PostNotFoundException e) {
            e.printStackTrace();
            return;
        }

        instance = this;
        setTitle("replying to \"" + post.getSubject() + "\"");
        setSize(300, 260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setBorder(Style.PADDING_SUBMENU);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Comment label
        JLabel commentLabel = new JLabel("comment");
        commentLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        commentLabel.setFont(Style.FONT_SMALL);
        panel.add(commentLabel);

        // Comment field
        commentField = new JTextField();
        commentField.setAlignmentX(Component.LEFT_ALIGNMENT);
        commentField.setFont(Style.FONT_NORMAL);
        commentField.setBorder(Style.BORDER_OUTLINE);
        panel.add(commentField);

        add(panel, BorderLayout.NORTH);

        // Bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        // Error message
        errorLabel = new JLabel();
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        errorLabel.setFont(Style.FONT_SMALL);
        errorLabel.setForeground(Color.red);
        bottomPanel.add(errorLabel);

        // Post button
        commentButton = new JButton("add comment");
        commentButton.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        commentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        commentButton.setFont(Style.FONT_SMALL);
        Style.styleButton(commentButton);
        bottomPanel.add(commentButton, BorderLayout.SOUTH);
        commentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = commentField.getText();
                if (text.isEmpty()) {
                    showErrorMessage("Comment cannot be empty.");
                } else {
                    // TODO: SEND TO DATABASE
//                    LocalDatabase.(postID, subject, body);
                    dispose();
                }
            }
        });

        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    void showErrorMessage(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }

    public static boolean isOpen() {
        return instance != null && instance.isShowing();
    }
}
