package worker;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class AddCommandHandler{
    private Input is;
    private Output os;

    /**
     * конструктор
     */
    public AddCommandHandler(){
        is = new Input();
        os = new Output();
    }

    /**
     * место анализа команды
     * @param sCommand команда разделённая сплитом по пробелам
     * @return текст, позицию и правильно ли была введена команда
     */
    private Object[] analyse (String[] sCommand){
        Integer position = 0;
        String text = "";
        Boolean good = true;
        try {
            try {
                position = Integer.valueOf(sCommand[1]);
                for (int i = 2; i < sCommand.length; i++) {
                    text = text + sCommand[i] + " ";
                }
            } catch (NumberFormatException e) {
                for (int i = 1; i < sCommand.length; i++) {
                    text = text + sCommand[i] + " ";
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.err.println("ERROR: неправильно введена команда.");
            good = false;
        }
        return new Object[]{text, position, good};
    }

    /**
     * метод по добавлению когда файла не существует
     * @param nav путь к файлу
     * @param doing работать или не надо (т. е. команда непонятная)
     * @param position позиция текста
     * @param text текст
     * @throws IOException отлов ошибок IO
     */
    private void secondAdd (String nav, boolean doing, Integer position, String text) throws IOException {
        if (doing) {
            String texts = "";
            for (int i = 0; i < position; i++) {
                texts += "\n";
            }
            texts += text;
            String[] txt = texts.split("\n");
            os.writeAndClose(os.createWriter(nav), txt);
        }
    }

    /**
     * добавление когда файл существует
     * @param sCommand команда разделённая сплитом по пробелам
     * @param navigation путь к файлу
     * @throws IOException отлов ошибок IO
     */
    public void add (String[] sCommand, String navigation) throws IOException {
        //Первая часть ада
        String[] allFile;
        Object[] all = analyse (sCommand);
        Integer position = Integer.valueOf((Integer) all[1]);
        String text = all[0].toString();
        Boolean doing = Boolean.valueOf((Boolean) all[2]);
        try {
            allFile = is.ReadAndClose(is.creatingReader(navigation));
        }
        catch (NullPointerException e){
            System.err.println("ERROR: файл не найден.");
            secondAdd (navigation, doing, position, text);
            return;
        }
        catch (IOException e){
            System.err.println("ERROR: ошибка читателя.");
            return;
        }
        if (doing) {
            while (position >= allFile.length) {
                String[] oldAllFile = allFile;
                allFile = Arrays.copyOf(oldAllFile, oldAllFile.length + 1);
            }

            //вторая часть ада
            String[] newAllFile;
            if (position == 0) {
                newAllFile = Arrays.copyOf(allFile, allFile.length + 1);
                newAllFile[allFile.length] = text;
            } else {
                String[] firstPart = Arrays.copyOf(allFile, position);
                String[] secondPart = new String[allFile.length - firstPart.length + 1];
                for (int i = firstPart.length; i < allFile.length; i++) {
                    secondPart[i - firstPart.length] = allFile[i];
                }
                newAllFile = Arrays.copyOf(firstPart, allFile.length + 1);
                newAllFile[position] = text;
                for (int z = position + 1; z < allFile.length; z++) {
                    newAllFile[z] = secondPart[z - position];
                }
            }
            os.writeAndClose(os.createWriter(navigation), newAllFile);
        }
    }
}
