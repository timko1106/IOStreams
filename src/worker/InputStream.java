package worker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InputStream{
    /**
     * создатель читателя
     * @param nav путь к файлу
     * @return читателя
     * @throws FileNotFoundException отлов ошибки FileNotFound
     */
    public BufferedReader creatingReader (String nav) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(nav));
        return br;
    }

    /**
     * чтение и уничтожение читателя
     * @param br читатель
     * @return что он прочитал
     * @throws IOException отлов ошибок IO
     */
    public String[] ReadAndClose (BufferedReader br) throws IOException {
        String string = "";
        String strings = "";
        while ((string = br.readLine()) != null) {
            strings = strings + string + "\n";
        }
        br.close();
        String[] strs = strings.split("\n");
        return strs;
    }
}
