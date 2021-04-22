import javax.swing.*;
import java.awt.*;

/**
 * The GUI for each individual post in the post feed.
 *
 * @author Kenny Park
 * @version 
 */
public class PostGUI extends JPanel {

    String sampleBody = "Have you ever had a dream that you, um, you had, your, you- you could, you’ll do, you- you wants, you, you could do so, you- you’ll do, you could- you, you want, you want them to do you so much you could do anything?";

    public PostGUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Info panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.LINE_AXIS));
        infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Subject label
        JLabel subjectLabel = new JLabel("Dreams");
        subjectLabel.setFont(Style.FONT_HEADER);
        infoPanel.add(subjectLabel);

        infoPanel.add(Box.createHorizontalGlue());

        // Identifier label
        JLabel identifierLabel = new JLabel("Kenny Park, 8:15PM | 04/24");
        identifierLabel.setFont(Style.FONT_SMALL);
        infoPanel.add(identifierLabel);

        add(infoPanel);

        // Body area
        JTextArea bodyArea = new JTextArea(4, 1);
        bodyArea.setText(sampleBody);
        bodyArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        bodyArea.setFont(Style.FONT_NORMAL);
        bodyArea.setLineWrap(true);
        bodyArea.setWrapStyleWord(true);
        bodyArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(bodyArea);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(scrollPane);

        JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
//        separator.
//        add(separator);

        setMaximumSize(new Dimension(getMaximumSize().width, getPreferredSize().height));
        setVisible(true);
    }
}
