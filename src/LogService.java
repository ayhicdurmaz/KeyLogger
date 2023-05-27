import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogService {
    private final String filePath;
    private final long maxSizeBytes;
    private long currentSizeBytes;

    public LogService(String filePath, long maxSizeMegabytes) {
        this.filePath = filePath;
        this.maxSizeBytes = maxSizeMegabytes * 1024 * 1024; // Convert megabytes to bytes
        this.currentSizeBytes = 0;
    }

    public void log(String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(text);
            writer.newLine();
            writer.flush();

            // Get the file size after writing the new line
            File file = new File(filePath);
            currentSizeBytes = file.length();

            if (currentSizeBytes >= maxSizeBytes) {
                clearLogFile();
                currentSizeBytes = 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearLogFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Clear the file by writing an empty string
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
