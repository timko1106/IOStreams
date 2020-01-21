package worker;

public class TranslatorForTypes {
    /**
     * перевод строки в число
     * @param text строка
     * @return число (при ошибке кинет 1234)
     */
    public static Integer StringToInt (String text){
        Integer answer;
        try {
            answer = Integer.valueOf(text);
        }
        catch (NumberFormatException e){
            answer = 1234;
        }
        return answer;
    }

    /**
     * перевод объекта в число
     * @param obj объект
     * @return число
     */
    public static Integer ObjectToInt (Object obj){
        return (Integer) obj;
    }

    /**
     * перевод объекта в стоку
     * @param obj объект
     * @return строка
     */
    public static String ObjectToString (Object obj){
        return (String) obj;
    }

    /**
     * перевод объекта в булеан
     * @param obj объект
     * @return булеан
     */
    public static Boolean ObjectToBoolean (Object obj){
        return (Boolean) obj;
    }
}