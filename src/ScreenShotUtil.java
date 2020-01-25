import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
public class ScreenShotUtil {
    private static ScreenShotUtil instance;
    private Robot robot = null;
    private Rectangle rectangle = null;
    private ByteArrayOutputStream byteArrayOutputStream = null;
    private ScreenShotUtil() {
        try {
            robot = new Robot();

        } catch (AWTException e) {
            e.printStackTrace();
        }
        rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
    }
    public static ScreenShotUtil getInstance() {
        if (instance == null) {
            instance = new ScreenShotUtil();
        }
        return instance;
    }
    private BufferedImage grabScreen() {
        if (robot != null) {
            return robot.createScreenCapture(rectangle);
        }
        return null;
    }
    public InputStream getScreen() {
        BufferedImage bufferedImage = grabScreen();
        if (bufferedImage != null) {
            if (byteArrayOutputStream == null) byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.reset();
            try {
                ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
                return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
