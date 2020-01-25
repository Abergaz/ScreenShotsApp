import java.io.InputStream;
import java.util.Date;
public class WorkThread extends Thread {
    private String fileName = null;
    private Date screenTime = null;
    @Override
    public void run() {
        screenTime = new Date();
        InputStream inputStream = ScreenShotUtil.getInstance().getScreen();
        while (inputStream != null) {
            fileName = FileNameUtil.getInstance().getFileName();
            System.out.println(screenTime + " Отправлен снимок " + fileName);
            new UploadThread(inputStream,fileName).start();
            try {
                sleep(5000+screenTime.getTime()- new Date().getTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            screenTime = new Date();
            inputStream = ScreenShotUtil.getInstance().getScreen();
        }
    }
}
