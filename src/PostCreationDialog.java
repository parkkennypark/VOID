import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A frame that takes input for a new post.
 *
 * @author Kenny Park
 * @version April 20, 2021
 */
public class PostCreationDialog extends JDialog {
    static PostCreationDialog instance;

    JLabel errorLabel;

    JTextField subjectField;
    JTextArea bodyArea;
    JButton postButton;

    // New post
    public PostCreationDialog() {
        setupFrame(-1);
        setTitle("new post");
    }

    // Edit post
    public PostCreationDialog(int postID) {
        Post post = new Post();
        try {
            post = LocalDatabase.getPostByID(postID);
        } catch (PostNotFoundException postNotFoundException) {
            postNotFoundException.printStackTrace();
        }

        setupFrame(postID);
        setTitle("edit post");
        postButton.setText("save changes");
        subjectField.setText(post.getSubject());
        bodyArea.setText(post.getBody());
        Post finalPost = post;
    }

    private void setupFrame(int postID) {
        instance = this;
        setSize(300, 260);
        setTitle("new post");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setBorder(Style.PADDING_SUBMENU);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Subject label
        JLabel subjectLabel = new JLabel("subject");
        subjectLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        subjectLabel.setFont(Style.FONT_SMALL);
        panel.add(subjectLabel);

        // Subject field
        subjectField = new JTextField(1);
        subjectField.setAlignmentX(Component.LEFT_ALIGNMENT);
        subjectField.setFont(Style.FONT_NORMAL);
        subjectField.setBorder(Style.BORDER_OUTLINE);
        panel.add(subjectField);

        // Body label
        JLabel bodyLabel = new JLabel("body");
        bodyLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        bodyLabel.setFont(Style.FONT_SMALL);
        panel.add(bodyLabel);

        // Body area/scroll pane
        bodyArea = new JTextArea(5, 1);
        bodyArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        bodyArea.setFont(Style.FONT_NORMAL);
        bodyArea.setLineWrap(true);
        bodyArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(bodyArea);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollPane.setBorder(Style.BORDER_OUTLINE);
        panel.add(scrollPane);

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
        postButton = new JButton("scream into the uncaring void");
        postButton.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        postButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        postButton.setFont(Style.FONT_SMALL);
        Style.styleButton(postButton);
        bottomPanel.add(postButton, BorderLayout.SOUTH);
        postButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String subject = subjectField.getText();
                String body = bodyArea.getText();
                if (subject.isEmpty()) {
                    showErrorMessage("Subject cannot be empty.");
                } else if (body.isEmpty()) {
                    showErrorMessage("Body cannot be empty.");
                } else {
                    LocalDatabase.sendPostToServer(postID, subject, body);
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
