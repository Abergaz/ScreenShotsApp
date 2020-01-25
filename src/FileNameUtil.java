import java.text.SimpleDateFormat;
import java.util.Date;
public class FileNameUtil {
    private static FileNameUtil instance;
    private static SimpleDateFormat simpleDateFormat = null;
    private FileNameUtil() {
        simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
    }
    public static FileNameUtil getInstance() {
        if (instance == null) {
            instance = new FileNameUtil();
        }
        return instance;
    }
    public String getFileName(){
        return simpleDateFormat.format(new Date())+".png";
    }
}
