package worker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Input {
    /**
     * создатель читателя
     * @param nav путь к файлу
     * @return читателя
     * @throws FileNotFoundException отлов ошибки FileNotFound
     */
    public BufferedReader creatingReader (String nav) throws FileNotFoundException {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(nav));
            return br;
        }
        catch (FileNotFoundException e){
            return null;
        }
    }

    /**
     * чтение и уничтожение читателя
     * ЗАМЕЧАНИЕ: при слиянии с методом creatingReader когда нет файла вылетит ошибка NullPointerException
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
