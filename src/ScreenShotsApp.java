public class ScreenShotsApp {
    public static void main(String[] args) {
        if (DropBoxSenderUtil.getInstance().getAccountInfo() == null){
            System.out.println("Неудалось подключиться к dropBox");
        }else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Приложение ScreenShotsApp будет каждые 5 секунд отправдять снимок экрана в ");
            stringBuilder.append("DropBox аккаунт: ");
            stringBuilder.append( DropBoxSenderUtil.getInstance().getAccountInfo().getName().getDisplayName());
            System.out.println(stringBuilder.toString());
            WorkThread workThread = new WorkThread();
            workThread.start();
        }
    }
}
