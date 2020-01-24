import java.io.InputStream;
import java.util.Date;

public class WorkThread extends Thread {
    private String fileName = null;
    private Date startSendDate = null;
    private Date endSendDate = null;

    @Override
    public void run() {
        InputStream inputStream = ScreenShotUtil.getInstance().getScreen();
        while (inputStream != null) {
            fileName = FileNameUtil.getInstance().getFileName();
            startSendDate = new Date();
            DropBoxSenderUtil.getInstance().upload(inputStream, fileName);
            endSendDate = new Date();
            System.out.println(startSendDate.toString() + " Отправлен снимок " + fileName);
            try {
                sleep(5000+startSendDate.getTime()-endSendDate.getTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            inputStream = ScreenShotUtil.getInstance().getScreen();
        }

    }
}
