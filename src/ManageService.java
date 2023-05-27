public class ManageService {
    private KeyLogger keyLogger;

    public ManageService(String filePath, long maxSizeMegabytes) {
        keyLogger = new KeyLogger(filePath, maxSizeMegabytes);
    }

    public KeyLogger getKeyLogger() {
        return keyLogger;
    }

}
