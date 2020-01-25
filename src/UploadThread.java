import java.io.InputStream;
public class UploadThread extends Thread {
    private InputStream inputStreamToUpload = null;
    private String fileName = null;
    public UploadThread(InputStream inputStreamToUpload, String fileName) {
        super();
        this.inputStreamToUpload = inputStreamToUpload;
        this.fileName = fileName;
    }
    @Override
    public void run() {
        DropBoxSenderUtil.getInstance().upload(inputStreamToUpload, fileName);
    }
}
