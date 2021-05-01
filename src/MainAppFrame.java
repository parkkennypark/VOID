import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Kenny Park
 * @version April 20, 2021
 */
public class MainAppFrame extends JFrame {

    public static MainAppFrame instance;

    public static void updateGUI() {
        if(instance != null) {
            instance.makeFrame();
        }
    }

    public MainAppFrame() {
        instance = this;
        makeFrame();
    }

    public void makeFrame() {
        /* set up JFrame */
        setTitle("void");
        setSize(600, 800);
        setMinimumSize(new Dimension(500, 300));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container content = getContentPane();
        content.setLayout(new BorderLayout());

//        content.add(mainGui, BorderLayout.CENTER);

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
                if (!PostCreationDialog.isOpen()) {
                    new PostCreationDialog();
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
        String mostPopularMuffin = Application.getMostPopularMuffin();
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
        bottomPanel.setBorder(new EmptyBorder(4, 0, 4, 0));
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));

        // Account label
        String identifier = Application.getLocalProfile().getIdentifier();
        JLabel accountLabel = new JLabel("signed in as: " + identifier);
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
                if (!ProfileEditDialog.isOpen()) {
                    new ProfileEditDialog(Application.getLocalProfile().getProfileID());
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
                if (!ProfileEditDialog.isOpen()) {

                }
            }
        });

        centerPanel.add(bottomPanel, BorderLayout.SOUTH);

        content.add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}
