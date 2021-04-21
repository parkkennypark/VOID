import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A frame that handles changes to the user's profile.
 *
 * @author Kenny Park
 * @version April 20, 2021
 */
public class EditProfileFrame extends JFrame {
    JLabel errorLabel;

    public EditProfileFrame() {
        setSize(300, 260);
        setTitle("edit profile");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setBorder(Style.PADDING_SUBMENU);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Identifier label
        JLabel identifierLabel = new JLabel("identifier");
        identifierLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        identifierLabel.setFont(Style.FONT_SMALL);
        panel.add(identifierLabel);

        // Identifier field
        JTextField identifierField = new JTextField(1);
        identifierField.setAlignmentX(Component.LEFT_ALIGNMENT);
        identifierField.setFont(Style.FONT_NORMAL);
        identifierField.setBorder(Style.BORDER_OUTLINE);
        panel.add(identifierField);

        // Muffin label
        JLabel muffinLabel = new JLabel("favorite type of breakfast muffin");
        muffinLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        muffinLabel.setFont(Style.FONT_SMALL);
        panel.add(muffinLabel);

        // Muffin field
        JTextField muffinField = new JTextField(1);
        muffinField.setAlignmentX(Component.LEFT_ALIGNMENT);
        muffinField.setFont(Style.FONT_NORMAL);
        muffinField.setBorder(Style.BORDER_OUTLINE);
        panel.add(muffinField);

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

        // Save button
        JButton saveButton = new JButton("save changes");
        saveButton.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.setFont(Style.FONT_SMALL);
        Style.styleButton(saveButton);
        bottomPanel.add(saveButton);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String identifier = identifierField.getText();
                String muffin = muffinField.getText();
                if (identifier.isEmpty()) {
                    showErrorMessage("Identifier cannot be empty.");
                } else if (!isIdentifierUnique(identifier)) {
                    showErrorMessage("Identifier is already in use.");
                } else if (muffin.isEmpty()) {
                    showErrorMessage("Muffin cannot be empty.");
                }
                else {
                    // Inputs are valid
                    // TODO: save changes

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

    boolean isIdentifierUnique(String identifier) {

        return true;
    }
}
