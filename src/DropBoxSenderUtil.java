import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;
import com.dropbox.core.v2.users.FullAccount;
import java.io.*;
public class DropBoxSenderUtil {
    private static DropBoxSenderUtil instance;
    private DbxRequestConfig config = null;
    private DbxClientV2 client = null;
    private FullAccount account = null;
    private static final String ACCESS_TOKEN = "LshFxca1VBAAAAAAAAACZ7-crnGrzPGbyFYhhXLsCfH4flIf5bayySeU_9emDj6f";
    private DropBoxSenderUtil() {
        config= DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        client = new DbxClientV2(config, ACCESS_TOKEN);
    }
    public  static DropBoxSenderUtil getInstance() {
        if (instance == null){
            instance = new DropBoxSenderUtil();
        }
        return instance;
    }
    public FullAccount getAccountInfo(){
        if (account == null) {
            try {
                account = client.users().getCurrentAccount();
            } catch (DbxException e) {
                e.printStackTrace();
            }
        }
        return account;
    }
    public boolean upload(String fileToUpload) {
        try (InputStream in = new FileInputStream(fileToUpload)) {
            FileMetadata metadata = client.files().uploadBuilder("/" +new File(fileToUpload).getName()).uploadAndFinish(in);
            return  true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UploadErrorException e) {
            e.printStackTrace();
        } catch (DbxException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean upload(InputStream inputStreamToUpload,String fileName) {
        try {
            FileMetadata metadata = client.files().uploadBuilder("/" +fileName).uploadAndFinish(inputStreamToUpload);
            return true;
        } catch (DbxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
