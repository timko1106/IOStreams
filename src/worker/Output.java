package worker;

import java.io.*;

public class Output {
    /**
     * создание писца
     * @param nav путь к файлу
     * @return писца
     * @throws FileNotFoundException отлов ошибки FileNotFound
     * @throws UnsupportedEncodingException отлов ошибки UnsupportedEncoding
     */
    public Writer createWriter (String nav) throws FileNotFoundException, UnsupportedEncodingException {
        Writer out = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(nav), "UTF8"
                )
        );
        return out;
    }

    /**
     * запись и уничтожение писца
     * @param wr писец
     * @param text текст
     * @throws IOException отлов ошибок IO
     */
    public void writeAndClose (Writer wr, String[] text) throws IOException {
        for (String txt:text) {
            wr.write(txt+"\n");
        }
        wr.flush();
        wr.close();
    }
}
