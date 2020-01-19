package worker;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class AddCommandHandler{
    private InputStream is;
    private OutputStream os;

    /**
     * конструктор
     */
    public AddCommandHandler(){
        is = new InputStream();
        os = new OutputStream();
    }

    /**
     * место анализа команды
     * @param sCommand команда разделённая сплитом по пробелам
     * @return текст, позицию и правильно ли была введена команда
     */
    private Object[] analyse (String[] sCommand){//ArrayIndexOutOfBoundsException
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
     * @param sCommand команда разделённая сплитом по пробелам
     * @throws IOException отлов ошибок IO
     */
    private void secondAdd (String nav, String[] sCommand) throws IOException {
        Object[] all = analyse (sCommand);
        if (Boolean.valueOf((Boolean) all[2])) {
            Integer position = Integer.valueOf((Integer) all[1]);
            String text = all[0].toString();
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
        try {
            allFile = is.ReadAndClose(is.creatingReader(navigation));
        }
        catch (FileNotFoundException e){
            System.err.println("ERROR: файл не найден.");
            secondAdd (navigation, sCommand);
            return;
        }
        catch (IOException e){
            System.err.println("ERROR: ошибка читателя.");
            return;
        }
        Object[] all = analyse (sCommand);
        if (Boolean.valueOf((Boolean) all[2])) {
            Integer position = Integer.valueOf((Integer) all[1]);
            String text = all[0].toString();
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
