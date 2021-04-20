import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * A frame that takes input for a new post.
 *
 * @author Kenny Park
 * @version April 20, 2021
 */
public class NewPostFrame extends JFrame {
    JLabel errorLabel;

    public NewPostFrame() {
        setSize(300, 240);
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
        JTextField subjectField = new JTextField(1);
        subjectField.setAlignmentX(Component.LEFT_ALIGNMENT);
        subjectField.setFont(Style.FONT_NORMAL);
        subjectField.setBorder(Style.BORDER_OUTLINE);
        panel.add(subjectField);

        // Body label
        JLabel bodyLabel = new JLabel("body");
        bodyLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        bodyLabel.setFont(Style.FONT_SMALL);
        panel.add(bodyLabel);

        // Body area
        JTextArea bodyArea = new JTextArea(4, 1);
        bodyArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        bodyArea.setFont(Style.FONT_NORMAL);
        bodyArea.setBorder(Style.BORDER_OUTLINE);
        bodyArea.setLineWrap(true);
        bodyArea.setWrapStyleWord(true);
        panel.add(bodyArea);
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
        JButton postButton = new JButton("scream into the uncaring void");
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
                if(subject.isEmpty()) {
                    showErrorMessage("Subject cannot be empty.");
                } else if(body.isEmpty()) {
                    showErrorMessage("Body cannot be empty.");
                } else {
                    // TODO: Post

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
        //JOptionPane.showMessageDialog(this, message, "error", JOptionPane.ERROR_MESSAGE);
    }
}
