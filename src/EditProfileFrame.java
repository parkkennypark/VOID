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
    static EditProfileFrame instance;

    JLabel errorLabel;
    String[] muffins = {"bran", "pumpkin-spice", "banana-nut", "chocolate", "blueberry", "oatmeal", "quiche"};

    JTextField identifier;
    JComboBox muffinBox;

    public EditProfileFrame() {
        setupFrame();
    }

//    public EditProfileFrame(Profile profile) {
//        setupFrame();
//        identifier.setText(profile.getIdentifier());
//        muffinBox.setSelectedIndex(profile.muffinIndex);
//    }

    public void setupFrame() {
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

        add(panel, BorderLayout.NORTH);

        // Muffin dropdown
        muffinBox = new JComboBox(muffins);
        muffinBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        muffinBox.setFont(Style.FONT_NORMAL);
        panel.add(muffinBox);

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
                int muffinIndex = muffinBox.getSelectedIndex();
                if (identifier.isEmpty()) {
                    showErrorMessage("Identifier cannot be empty.");
                } else if (!isIdentifierUnique(identifier)) {
                    showErrorMessage("Identifier is already in use.");
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

    public static boolean isOpen() {
        return instance != null && instance.isShowing();
    }
}
