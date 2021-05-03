import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI that allows the user to sign up or log in to the app.
 *
 * @author Kenny Park
 * @version April 29, 2021
 */
public class AppLandingFrame extends JFrame {
    Container content;

    JLabel errorLabel;

    public AppLandingFrame() {
        setTitle("void");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        content = getContentPane();
        content.setLayout(new BorderLayout());

        landing();
    }

    private void landing() {
        SwingUtilities.updateComponentTreeUI(content);
        content.removeAll();

        // Panel
        JPanel panel = new JPanel();
        panel.setBorder(Style.PADDING_SUBMENU);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(Box.createRigidArea(new Dimension(0, 70)));

        // Title label
        JLabel titleLabel = new JLabel("V O I D");
        titleLabel.setFont(Style.FONT_TITLE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        Dimension buttonSize = new Dimension(90, 24);

        // Sign up button
        JButton signUpButton = new JButton("sign up");
        signUpButton.setFont(Style.FONT_SMALL);
        signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        Style.styleButton(signUpButton);
        signUpButton.setMargin(null);
        signUpButton.setMaximumSize(buttonSize);
        panel.add(signUpButton);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signUp();
            }
        });

        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Log in button
        JButton logInButton = new JButton("log in");
        logInButton.setFont(Style.FONT_SMALL);
        logInButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        Style.styleButton(logInButton);
        logInButton.setMargin(null);
        logInButton.setMaximumSize(buttonSize);
        panel.add(logInButton);
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        content.add(panel, BorderLayout.CENTER);

        // Version text
        JLabel infoText = new JLabel(" ver 0.1");
        infoText.setFont(Style.FONT_SMALL);
        content.add(infoText, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void signUp() {
        SwingUtilities.updateComponentTreeUI(content);
        content.removeAll();

        // Panel
        JPanel panel = new JPanel();
        panel.setBorder(Style.PADDING_SUBMENU);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Title label
        JLabel titleLabel = new JLabel("sign up");
        titleLabel.setFont(Style.FONT_HEADER);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(titleLabel);

        // Identifier label
        JLabel identifierLabel = new JLabel("identifier");
        identifierLabel.setFont(Style.FONT_SMALL);
        identifierLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(identifierLabel);

        // Identifier field
        JTextField identifierField = new JTextField();
        identifierField.setFont(Style.FONT_NORMAL);
        identifierField.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(identifierField);

        // Password label
        JLabel passwordLabel = new JLabel("password");
        passwordLabel.setFont(Style.FONT_SMALL);
        passwordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(passwordLabel);

        // Password field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(Style.FONT_NORMAL);
        passwordField.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(passwordField);

        // Muffin label
        JLabel muffinLabel = new JLabel("favorite type of breakfast muffin");
        muffinLabel.setFont(Style.FONT_SMALL);
        muffinLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(muffinLabel);

        // Muffin dropdown
        JComboBox muffinBox = new JComboBox(Muffin.getMuffinLabels());
        muffinBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        muffinBox.setFont(Style.FONT_NORMAL);
        panel.add(muffinBox);

        // Error message
        errorLabel = new JLabel();
        errorLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        errorLabel.setFont(Style.FONT_SMALL);
        errorLabel.setForeground(Color.red);
        panel.add(errorLabel);

        content.add(panel, BorderLayout.NORTH);

        // Bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2));

        // Back button
        JButton backButton = new JButton("back");
        backButton.setFont(Style.FONT_SMALL);
        Style.styleButton(backButton);
        backButton.setMargin(null);
        bottomPanel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                landing();
            }
        });

        // Sign up button
        JButton signUpButton = new JButton("sign up");
        signUpButton.setFont(Style.FONT_SMALL);
        Style.styleButton(signUpButton);
        signUpButton.setMargin(null);
        bottomPanel.add(signUpButton);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String identifier = identifierField.getText();
                String password = String.valueOf(passwordField.getPassword());
                if (identifier.isEmpty()) {
                    showErrorMessage("Identifier cannot be empty.");
                } else if (password.isEmpty()) {
                    showErrorMessage("Password cannot be empty.");
                } else {
                    if (trySignUp(identifier, password, muffinBox.getSelectedIndex())) {
                        dispose();
                    } else {
                        showErrorMessage("Identifier is in use.");
                    }
                }
            }
        });

        content.add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void login() {
        SwingUtilities.updateComponentTreeUI(content);
        content.removeAll();

        // Panel
        JPanel panel = new JPanel();
        panel.setBorder(Style.PADDING_SUBMENU);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Title label
        JLabel titleLabel = new JLabel("log in");
        titleLabel.setFont(Style.FONT_HEADER);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(titleLabel);

        // Identifier label
        JLabel identifierLabel = new JLabel("identifier");
        identifierLabel.setFont(Style.FONT_SMALL);
        identifierLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(identifierLabel);

        // Identifier field
        JTextField identifierField = new JTextField();
        identifierField.setFont(Style.FONT_NORMAL);
        identifierField.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(identifierField);

        // Password label
        JLabel passwordLabel = new JLabel("password");
        passwordLabel.setFont(Style.FONT_SMALL);
        passwordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(passwordLabel);

        // Password field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(Style.FONT_NORMAL);
        passwordField.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(passwordField);

        panel.add(Box.createVerticalGlue());

        // Error message
        errorLabel = new JLabel();
        errorLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        errorLabel.setFont(Style.FONT_SMALL);
        errorLabel.setForeground(Color.red);
        panel.add(errorLabel);

        content.add(panel, BorderLayout.NORTH);

        // Bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2));

        // Back button
        JButton backButton = new JButton("back");
        backButton.setFont(Style.FONT_SMALL);
        Style.styleButton(backButton);
        backButton.setMargin(null);
        bottomPanel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                landing();
            }
        });

        // Log in button
        JButton loginButton = new JButton("log in");
        loginButton.setFont(Style.FONT_SMALL);
        Style.styleButton(loginButton);
        loginButton.setMargin(null);
        bottomPanel.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String identifier = identifierField.getText();
                String password = String.valueOf(passwordField.getPassword());
                if (identifier.isEmpty()) {
                    showErrorMessage("Identifier cannot be empty.");
                } else if (password.isEmpty()) {
                    showErrorMessage("Password cannot be empty.");
                } else {
                    if (tryLogin(identifier, password)) {
                        Application.openMainGUI();
                        dispose();
                    } else {
                        showErrorMessage("Invalid login credentials.");
                    }
                }
            }
        });

        content.add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    boolean trySignUp(String identifier, String password, int muffinIndex) {
        if (!Client.database.isIdentifierUnique(identifier)) {
            return false;
        }

        // Setup local profile, but with an ID of -1 since we don't know what it should be.
        Profile profile = new Profile(-1, identifier, password, muffinIndex);
//        Client.instance.sendProfileToServer(profile);
        Client.instance.sendPacketToServer(new Packet(Packet.PacketType.PROFILE, profile));
        Application.setLocalProfile(profile);

        // Ask server what my ID should be.
        Packet idQuery = new Packet(Packet.PacketType.NEW_PROFILE_ID_QUERY, null);
        Client.instance.sendPacketToServer(idQuery);
        return true;
    }

    boolean tryLogin(String identifier, String password) {
        try {
            if (Client.database.isPasswordCorrect(identifier, password)) {
                Application.setLocalProfile(Client.database.getProfileByIdentifier(identifier));
                return true;
            }
        } catch (ProfileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    void showErrorMessage(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }
}
