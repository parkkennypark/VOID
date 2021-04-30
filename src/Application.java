import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 *
 * @author Kenny Park
 * @version 
 */
public class Application {
    static float startTimeMS;
    public static void main(String[] args) {
        startTimeMS = System.currentTimeMillis();

        Client client = new Client();
        Thread t = new Thread(client);
        t.start();

        // Wait until connection is established
        float timePassedMS = System.currentTimeMillis() - startTimeMS;
        while(!Client.instance.isConnected() && timePassedMS < 10000) {
            timePassedMS = System.currentTimeMillis() - startTimeMS;
        }

        new AppLandingFrame();
    }

    public static void openMainGUI() {
        new MainAppFrame();
    }

    public static void updateGUI() {

    }

    public static String getTimeStamp() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return dateFormat.format(date);
    }
}
