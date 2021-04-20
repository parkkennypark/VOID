import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.DigestException;

/**
 *
 *
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
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        gui = new GUI();

        content.add(gui, BorderLayout.CENTER);

        /** Top panel **/
        JPanel topPanel = new JPanel();
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        topPanel.setBorder(BorderFactory.createCompoundBorder(Style.BORDER_OUTLINE, padding));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        // Empty space
        topPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Title
        JLabel title = new JLabel("V O I D");
        title.setFont(Style.FONT_TITLE);
        title.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        topPanel.add(title);

        // Subtitle
        JLabel subtitle = new JLabel("- scream endlessly with friends -");
        subtitle.setFont(Style.FONT_SMALL);
//        topPanel.add(subtitle);

        // Post button
        JButton postButton = new JButton("c o n t r i b u t e   a   t h o u g h t   t o   t h e   v a c u u m");
        postButton.setFont(Style.FONT_SMALL);
        postButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        Style.styleButton(postButton);
        topPanel.add(postButton, BorderLayout.CENTER);
        postButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(newPostFrame == null || !newPostFrame.isShowing()) {
                    newPostFrame = new NewPostFrame();
                    newPostFrame.setAlwaysOnTop(true);
                }
            }
        });

        content.add(topPanel, BorderLayout.NORTH);

        /** Bottom panel **/
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

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
                if(editProfileFrame == null || !editProfileFrame.isShowing()) {
                    editProfileFrame = new EditProfileFrame();
                    editProfileFrame.setAlwaysOnTop(true);
                }
            }
        });

        content.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
