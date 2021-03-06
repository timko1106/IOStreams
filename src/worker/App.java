package worker;

import java.io.IOException;
import java.util.Scanner;

public class App{
    private Scanner scanner;
    private AddCommandHandler add;
    private PrintCommandHandler print;
    private DeleteCommandHandler delete;

    /**
     * конструктор
     */
    public App(){
        scanner = new Scanner(System.in);
        add = new AddCommandHandler();
        print = new PrintCommandHandler();
        delete = new DeleteCommandHandler();
    }

    /**
     * старт кода
     * @throws IOException отлов ошибок IO
     */
    public void start () throws IOException {
        System.out.println("Введите start для запуска.");
        String answer = "";
        while (!answer.equals("start")){
            answer = scanner.nextLine();
            if (!answer.equals("start")){
                System.err.println("Ты читать не умеешь? Я же напечатал РУССКИМ ЯЗЫКОМ ввести start!");
            }
        }
        System.out.println("Запущено.");
        working ();
    }

    /**
     * работа программы
     * @throws IOException отлов ошибок IO
     */
    private void working () throws IOException {
        String[] commands = new String[]{"add", "delete", "print", "exit"};
        String cmd;
        while (true){
            System.out.println("команды:");
            for (String command:commands) {
                System.out.println(command);
            }
            cmd = scanner.nextLine();

            menuDoings(cmd.split(" "));
            if (cmd.split(" ")[0].equals("exit")){
                scanner.close();
                return;
            }
        }
    }

    /**
     * действия программы по меню
     * @param sCommand команда разделённая сплитом по пробелам
     * @throws IOException отлов ошибок IO
     */
    private void menuDoings (String[] sCommand) throws IOException {
        String[] firstPartsToNavigation = new String[]{"add", "delete", "print"};
        String navigation = "";
        for (String firstPart:firstPartsToNavigation) {
            if (sCommand[0].equals(firstPart)){
                System.out.println("Направление к файлу:");
                navigation = scanner.nextLine();
            }
        }
        switch (sCommand[0]){
            case ("add"):
                add.add(sCommand, navigation);
                break;
            case ("delete"):
                delete.delete(sCommand, navigation);
                break;
            case ("print"):
                print.print(navigation, sCommand);
                break;
            case ("exit"):
                System.out.println("Завершение работы.");
                break;
            default:
                System.err.println("ERROR: я не знаю такой команды.");
                break;
        }
    }
}
