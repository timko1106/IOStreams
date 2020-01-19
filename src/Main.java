import worker.App;

import java.io.IOException;

public class Main{
    /**
     * главный метод
     * @param args аргументы
     * @throws IOException отлов ошибок IO
     */
    public static void main(String[] args) throws IOException {
        App app = new App();
        app.start();
    }
}
