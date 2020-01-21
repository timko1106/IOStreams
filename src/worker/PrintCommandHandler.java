package worker;

import java.io.IOException;

public class PrintCommandHandler{
    private Input is;

    public PrintCommandHandler(){
        is = new Input();
    }

    public void print (String nav, String[] sCommand) throws IOException {
        String[] textFile;
        Integer number = analyse(sCommand);
        try {
            textFile = is.ReadAndClose(is.creatingReader(nav));
        }
        catch (NullPointerException e){
            System.err.println("ERROR: нет такого файла.");
            return;
        }
        if (number == null) {
            System.out.println("Текст файла: ");
            for (String line : textFile) {
                System.out.println(line);
            }
            System.out.println("Конец файла");
        }
        else {
            System.out.println("Строка номер "+number+": "+textFile[number]);
        }
    }

    /**
     * метод анализа команды
     * @param sCommand команда делёная сплитом по пробелам
     * @return какую строку печатать (если все кинет null)
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
