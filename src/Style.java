import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Stores style variables for use across all GUI elements.
 *
 * @author Kenny Park
 * @version April 27, 2021
 */
public class Style {
    private static final String FONT_NAME = "Serif";
    static final Font FONT_TITLE = new Font(FONT_NAME, Font.PLAIN, 28);
    static final Font FONT_HEADER = new Font(FONT_NAME, Font.BOLD, 16);
    static final Font FONT_NORMAL = new Font(FONT_NAME, Font.PLAIN, 16);
    static final Font FONT_SMALL = new Font(FONT_NAME, Font.PLAIN, 12);

    static final Border BORDER_OUTLINE = BorderFactory.createLineBorder(Color.BLACK, 1, true);
    static final Border PADDING_SUBMENU = BorderFactory.createEmptyBorder(5, 5, 5, 5);

    static void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setMargin(new Insets(0, 5, 0, 5));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBackground(Color.WHITE);
    }

    static void styleButtonInvisibleBorder(JButton button) {
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setContentAreaFilled(false);
        button.setOpaque(false);
    }
}
