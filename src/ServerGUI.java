import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The GUI for the server.
 *
 * @author Kenny Park
 * @version May 2, 2021
 */
public class ServerGUI extends JFrame {
    public ServerGUI() {
        setSize(200, 150);
        setTitle("server");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        JLabel runningLabel = new JLabel("server is running.");
        runningLabel.setFont(Style.FONT_SMALL);
        runningLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(runningLabel);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton stopButton = new JButton("stop server");
        stopButton.setFont(Style.FONT_SMALL);
        stopButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        Style.styleButton(stopButton);
        panel.add(stopButton);
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Server.instance.stopServer();
                Runtime.getRuntime().exit(0);
            }
        });

        add(panel);

        setVisible(true);
    }
}
