import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.DigestException;

/**
 * @author Kenny Park
 * @version April 20, 2021
 */
public class GUI extends JComponent implements Runnable {

    GUI gui;
    NewPostFrame newPostFrame;
    EditProfileFrame editProfileFrame;


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new GUI());
    }

    @Override
    public void run() {
        /* set up JFrame */
        JFrame frame = new JFrame("void chat");
        frame.setSize(600, 800);
        frame.setMinimumSize(new Dimension(500, 300));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        gui = new GUI();

        content.add(gui, BorderLayout.CENTER);

        /** Top panel (title) **/
        JPanel topPanel = new JPanel();
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        topPanel.setBorder(BorderFactory.createCompoundBorder(Style.BORDER_OUTLINE, padding));
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
                if (newPostFrame == null || !newPostFrame.isShowing()) {
                    newPostFrame = new NewPostFrame();
                    newPostFrame.setAlwaysOnTop(true);
                }
            }
        });

        content.add(topPanel, BorderLayout.NORTH);

        /** Center panel (post feed) **/
        JPanel centerPanel = new JPanel();
        centerPanel.setBorder(padding);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        /** Feed title **/
        JPanel feedTitlePanel = new JPanel();
        feedTitlePanel.setLayout(new BoxLayout(feedTitlePanel, BoxLayout.LINE_AXIS));

        // Vacuum label
        JLabel vacuumLabel = new JLabel("the vacuum");
        vacuumLabel.setFont(Style.FONT_SMALL);
        vacuumLabel.setHorizontalAlignment(SwingConstants.LEFT);
        feedTitlePanel.add(vacuumLabel);

        feedTitlePanel.add(Box.createHorizontalGlue());

        // Muffin label
        JLabel muffinLabel = new JLabel("most popular muffin: bran");
        muffinLabel.setFont(Style.FONT_SMALL);
        vacuumLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        feedTitlePanel.add(muffinLabel);

        centerPanel.add(feedTitlePanel);

        // Feed scroll view
        JPanel feedPanel = new JPanel();
        feedPanel.setLayout(new BoxLayout(feedPanel, BoxLayout.Y_AXIS));
        feedPanel.setBackground(Color.WHITE);
//        feedPanel.setPreferredSize(new Dimension(500, 500));

        feedPanel.add(new PostGUI());
        feedPanel.add(new PostGUI());
        feedPanel.add(new PostGUI());
        feedPanel.add(new PostGUI());
        feedPanel.add(new PostGUI());
        feedPanel.add(new PostGUI());
        feedPanel.add(new PostGUI());
        feedPanel.add(new PostGUI());


        JScrollPane scrollPane = new JScrollPane(feedPanel);
        scrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
        centerPanel.add(scrollPane);

        content.add(centerPanel, BorderLayout.CENTER);

        /** Bottom panel (profile info) **/
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(Style.BORDER_OUTLINE);

        JLabel accountLabel = new JLabel("signed in as:");
        accountLabel.setFont(Style.FONT_SMALL);
        bottomPanel.add(accountLabel);

        JLabel nameLabel = new JLabel("Kenny Park");
        nameLabel.setFont(Style.FONT_SMALL);
        bottomPanel.add(nameLabel);

        JLabel separatorLabel = new JLabel("  |  ");
        bottomPanel.add(separatorLabel);

        JButton editProfileButton = new JButton("edit profile");
        editProfileButton.setFont(Style.FONT_SMALL);
        Style.styleButton(editProfileButton);
        bottomPanel.add(editProfileButton);
        editProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (editProfileFrame == null || !editProfileFrame.isShowing()) {
                    editProfileFrame = new EditProfileFrame();
                    editProfileFrame.setAlwaysOnTop(true);
                }
            }
        });

        content.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
