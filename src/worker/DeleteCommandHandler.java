package worker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class DeleteCommandHandler {
    private Input is;
    private Output os;

    /**
     * конструктор
     */
    public DeleteCommandHandler(){
        is = new Input();
        os = new Output();
    }

    /**
     * удаление строки из файла
     * @param sCommand команда делённая сплитом по пробелам
     * @param nav путь к файлу
     * @throws IOException отлов ошибки IO
     */
    public void delete (String[] sCommand, String nav) throws IOException {
        Integer number = analyse(sCommand);
        String[] oldText;
        try {
            oldText = is.ReadAndClose(is.creatingReader(nav));
        }
        catch (NullPointerException e){
            System.err.println("ERROR: такого файла нет.");
            return;
        }
        ArrayList<String> file = new ArrayList<>();
        Collections.addAll(file, oldText);
        if (number == null){
            Iterator<String> iterator = file.iterator();
            while (iterator.hasNext()){
                iterator.next();
                if (!iterator.hasNext()){
                    iterator.remove();
                }
            }
        }
        else {
            if (number >= file.size()){
                System.err.println("ERROR: такой линии в файле не существует.");
                return;
            }
            int pos = 0;
            Iterator<String> iterator = file.iterator();
            while (iterator.hasNext()){
                pos++;
                iterator.next();
                if (pos == number){
                    iterator.remove();
                }
            }
        }
        String[] newFile = new String[file.size()];
        for (int i = 0; i < file.size(); i++) {
            newFile[i] = file.get(i);
        }
        os.writeAndClose(os.createWriter(nav), newFile);
    }

    /**
     * анализ команды
     * @param sCommand команда делённая сплитом по пробелам
     * @return номер строки для удаления (если нет то возвращает null)
     */
    private Integer analyse (String[] sCommand){
        try {
            Integer answer;
            answer = TranslatorForTypes.StringToInt(sCommand[1]);
            if (answer == 1234) {
                return null;
            }
            return answer;
        }
        catch (ArrayIndexOutOfBoundsException e){
            return null;
        }
    }
}
