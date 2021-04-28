import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

/**
 * @author Kenny Park
 * @version April 20, 2021
 */
public class MainGUI extends JComponent implements Runnable {

    MainGUI mainGui;

    public static JFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new MainGUI());
    }

    public static void updateGUI() {
        frame.setVisible(true);
    }

    @Override
    public void run() {
        /* set up JFrame */
        frame = new JFrame("void chat");
        frame.setSize(600, 800);
        frame.setMinimumSize(new Dimension(500, 300));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        mainGui = new MainGUI();

        content.add(mainGui, BorderLayout.CENTER);

        /* Top panel (title) */
        JPanel topPanel = new JPanel();
//        topPanel.setBorder(BorderFactory.createCompoundBorder(Style.BORDER_OUTLINE, padding));
//        topPanel.setBorder();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        // Empty space
        topPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        // Title
        JLabel title = new JLabel("V O I D");
        title.setFont(Style.FONT_TITLE);
        title.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        topPanel.add(title);

        // Subtitle
        JLabel subtitle = new JLabel("- scream endlessly with friends -");
        subtitle.setFont(Style.FONT_SMALL);
//        topPanel.add(subtitle);

        // Empty space
        topPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        // Post button
        JButton postButton = new JButton("c o n t r i b u t e   a   t h o u g h t   t o   t h e   v a c u u m");
        postButton.setFont(Style.FONT_SMALL);
        postButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        Style.styleButton(postButton);
        topPanel.add(postButton, BorderLayout.CENTER);
        postButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!PostCreationFrame.isOpen()) {
                    new PostCreationFrame().setAlwaysOnTop(true);
                }
            }
        });

        content.add(topPanel, BorderLayout.NORTH);

        /* Center panel (post feed) */
        JPanel centerPanel = new JPanel();
        centerPanel.setBorder(new EmptyBorder(10, 10, 0, 10));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        /* Feed title */
        JPanel feedTitlePanel = new JPanel();
        feedTitlePanel.setLayout(new BoxLayout(feedTitlePanel, BoxLayout.LINE_AXIS));

        // Vacuum label
        JLabel vacuumLabel = new JLabel("the vacuum");
        vacuumLabel.setFont(Style.FONT_SMALL);
        vacuumLabel.setHorizontalAlignment(SwingConstants.LEFT);
        feedTitlePanel.add(vacuumLabel);

        feedTitlePanel.add(Box.createHorizontalGlue());

        // Best muffin label
        String mostPopularMuffin = CurrentSession.getMostPopularMuffin();
        JLabel muffinLabel = new JLabel("most popular muffin: " + mostPopularMuffin);
        muffinLabel.setFont(Style.FONT_SMALL);
        vacuumLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        feedTitlePanel.add(muffinLabel);

        centerPanel.add(feedTitlePanel);

        // Feed scroll pane
        JScrollPane scrollPane = new JScrollPane(new FeedPanel());
        scrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
        centerPanel.add(scrollPane);

        /* Bottom panel (profile info) */
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(new EmptyBorder(2, 0, 2, 0));
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));

        // Account label
        String username = "Kenny Park";
        JLabel accountLabel = new JLabel("signed in as: " + username);
        accountLabel.setFont(Style.FONT_SMALL);
        bottomPanel.add(accountLabel);

        JLabel separatorLabel = new JLabel("  |  ");
//        bottomPanel.add(separatorLabel);

        bottomPanel.add(Box.createHorizontalGlue());

        // Edit profile button
        JButton editProfileButton = new JButton("edit profile");
        editProfileButton.setFont(Style.FONT_SMALL);
        Style.styleButton(editProfileButton);
        bottomPanel.add(editProfileButton);
        editProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!ProfileEditFrame.isOpen()) {
                    new ProfileEditFrame().setAlwaysOnTop(true);
                }
            }
        });

        bottomPanel.add(Box.createRigidArea(new Dimension(5, 0)));

        // Delete profile button
        JButton deleteProfileButton = new JButton("delete profile");
        deleteProfileButton.setFont(Style.FONT_SMALL);
        Style.styleButton(deleteProfileButton);
        bottomPanel.add(deleteProfileButton);
        deleteProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!ProfileEditFrame.isOpen()) {
                    new ProfileEditFrame().setAlwaysOnTop(true);
                }
            }
        });

        centerPanel.add(bottomPanel, BorderLayout.SOUTH);

        content.add(centerPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
