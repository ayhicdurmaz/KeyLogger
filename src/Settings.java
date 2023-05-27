import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Settings {

    public static enum Prefs {
        KEYBOARD_ONLY,
        MOUSE_ONLY,
        BOTH
    }

    public static final String SETTING_FILE_PATH = "settings.txt";
    public static final String LOG_FILE_PATH = "log.txt";

    public static String mail;
    public static int logSize, mailInterval;
    public static Prefs pref;
    public static boolean working;

    public static void saveParams() {

        String settingsLine = mail + "|" + mailInterval + "|" + logSize + "|" + pref.toString() + "|" + working;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SETTING_FILE_PATH))) {
            writer.write(settingsLine);
        } catch (IOException e) {
            e.getStackTrace();
        }

    }

    public static void loadParams() {
        String[] lines;

        try (BufferedReader reader = new BufferedReader(new FileReader(SETTING_FILE_PATH))) {

            List<String> _lines = new ArrayList<>();

            String line;

            while ((line = reader.readLine()) != null) {
                _lines.add(line);
            }

            lines = _lines.toArray(new String[0]);

        } catch (IOException e) {
            e.printStackTrace();
            lines = null;
        }
        String[] settings = lines[0].trim().split(",");
        mail = settings[0];
        mailInterval = Integer.parseInt(settings[1]);
        logSize = Integer.parseInt(settings[2]);
        pref = Prefs.valueOf(settings[3]);
        working = Boolean.parseBoolean(settings[4]);
    }
}
