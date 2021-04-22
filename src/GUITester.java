import javax.swing.*;
import java.awt.*;

/**
 *
 *
 * @author Kenny Park
 * @version 
 */
public class GUITester extends JComponent implements Runnable {

    GUITester guiTester;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new GUITester());
    }

    @Override
    public void run() {
        /* set up JFrame */
        JFrame frame = new JFrame("void chat");
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        guiTester = new GUITester();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        Button b1 = new Button("b1");
        b1.setMaximumSize(new Dimension(200, 20));
        Button b2 = new Button("b2");
        b2.setMaximumSize(new Dimension(200, 20));
        Button b3 = new Button("b3");
        b3.setMaximumSize(new Dimension(200, 20));
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);

        content.add(panel);

        frame.setVisible(true);
    }
}
