import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public class Task4 {
    public static void main(String[] a) {
        System.out.println("Task N1");
        System.out.println(format(10,7,"hello my name is Bessie and this is my essay"));
        System.out.println();

        System.out.println("Task N2");
        System.out.println(split("()()()"));
        System.out.println(split("((()))"));
        System.out.println(split("((()))(())()()(()())"));
        System.out.println(split("((())())(()(()()))"));
        System.out.println();

        System.out.println("Task N3");
        System.out.println(toCamelCase("hello_edabit"));
        System.out.println(toSnakeCase("helloEdabit"));
        System.out.println(toCamelCase("is_modal_open"));
        System.out.println(toSnakeCase("getColor"));
        System.out.println();

        float[] firstArray = {9, 17, 30, 1.5f};
        float[] secondArray = {16, 18, 30, 1.8f};
        float[] thirdArray = {13.25f, 15, 30, 1.5f};
        System.out.println("Task N4");
        System.out.println(overTime(firstArray));
        System.out.println(overTime(secondArray));
        System.out.println(overTime(thirdArray));
        System.out.println();

        System.out.println("Task N5");
        System.out.println(BMI("205 pounds", "73 inches"));
        System.out.println(BMI("55 kilos", "1.65 meters"));
        System.out.println(BMI("154 pounds", "2 meters"));
        System.out.println();

        System.out.println("Task N6");
        System.out.println(bugger(39));
        System.out.println(bugger(999));
        System.out.println(bugger(4));
        System.out.println();

        System.out.println("Task N7");
        System.out.println(toStarShorthand("abbccc"));
        System.out.println(toStarShorthand("77777geff"));
        System.out.println(toStarShorthand("abc"));
        System.out.println(toStarShorthand(""));
        System.out.println();

        System.out.println("Task N8");
        System.out.println(doesRhyme("Sam I am!", "Green eggs and ham."));
        System.out.println(doesRhyme("Sam I am!", "Green eggs and HAM."));
        System.out.println(doesRhyme("You are off to the races", "a splendid day."));
        System.out.println(doesRhyme("and frequently do?", "you gotta move."));
        System.out.println();

        System.out.println("Task N9");
        //System.out.println(trouble(451999277, 41_177_722_899));
        System.out.println(trouble(1222345, 12345));
        System.out.println(trouble(666789, 12345667));
        System.out.println(trouble(33789, 12345337));
        System.out.println();

        System.out.println("Task N10");
        System.out.println(countUniqueBooks("AZYWABBCATTTA", 'A'));
        System.out.println(countUniqueBooks("$AA$BBCATT$C$$B$", '$'));
        System.out.println(countUniqueBooks("ZZABCDEF", 'Z'));
        System.out.println();
    }

    // форматирует эссе
    // n - количество слов; k - максимальное количество символов в строке
    public static String format(int n, int k, String essay) {
        StringBuilder result = new StringBuilder();
        List<String> list = List.of(essay.split(" ")); // список всех слов

        int strSum = 0; // счетчик символов в строке результата

        for (int i = 0; i < n; i++) {
            if (strSum + list.get(i).length() > k) {
                result.append("\n");
                result.append(list.get(i));
                strSum = list.get(i).length();
            } else {
                if (i != 0) {
                    result.append(" ");
                }
                result.append(list.get(i));
                strSum += list.get(i).length();
            }
        }
        return result.toString();
    }

    // разделяет строку из скобок на список кластеров
    public static List<String> split(String str) {
        List<String> result = new ArrayList<>(); // пустой список для записи результата
        int openCount = 0; // счетчик открытых скобок в кластере
        int closedCount = 0; // счетчик закрытых скобок в кластере

        for (int i = 0; i < str.length(); i++) {
            // если количества открытых и закрытых скобок совпадают создает новый кластер в списке и обнуляет счетчики
            // добавляет первый кластер в список
            if (openCount == closedCount) {
                result.add("");
                openCount = 0;
                closedCount = 0;
            }

            if (str.charAt(i) == '(') {
                openCount += 1;
            } else {
                closedCount += 1;
            }

            // добавление в кластер символа скобки
            result.set(result.size()-1,
                       result.get(result.size()-1) + str.charAt(i));
        }
        return result;
    }

    // переводит строку из snake_case в camelCase
    public static String toCamelCase(String str) {
        List<String> list = new ArrayList<>(List.of(str.split("_")));

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i != 0) {
                StringBuilder word = new StringBuilder(list.get(i));
                word.setCharAt(0,Character.toUpperCase(list.get(i).charAt(0)));
                list.set(i, word.toString());
            }
            result.append(list.get(i));
        }
        return result.toString();
    }

    // переводит строку из camelCase в snake_case
    public static String toSnakeCase(String str) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            if (Character.isUpperCase(str.charAt(i))) {
                result.append('_');
            }
            result.append(Character.toLowerCase(str.charAt(i)));
        }
        return result.toString();
    }

    // расчитывает оплату труда с учетом переработки
    public static String overTime(float[] array) {
        StringBuilder result = new StringBuilder("$");
        float salary = 0;

        while (array[0] < array[1]) {
            if (array[0] >= 9 && array[0] < 17) { // если регулярно
                if (array[1] - array[0] < 1) {
                    salary += array[2] * (array[1] - array[0]);
                    break;
                } else {
                    salary += array[2];
                }
            } else { // если в нерабочее время
                if (array[1] - array[0] < 1) {
                    salary += array[2] * (array[1] - array[0]) * array[3];
                    break;
                } else {
                    salary += array[2] * array[3];
                }
            }
            array[0] += 1;
        }

        result.append(String.format(Locale.ENGLISH, "%.2f", salary));
        return result.toString();
    }

    // рассчет ИМТ
    public static String BMI(String weight, String height) {
        double h, w;
        List<String> heightList = new ArrayList<>(List.of(height.split(" ")));
        List<String> weightList = new ArrayList<>(List.of(weight.split(" ")));
        StringBuilder result = new StringBuilder();

        // перевод в метры при необходимости
        if (heightList.get(1).equals("inches")) {
            h = Double.parseDouble(heightList.get(0)) * 0.0254;
        } else {
            h = Double.parseDouble(heightList.get(0));
        }

        // перевод в килограммы при необходимости
        if (weightList.get(1).equals("pounds")) {
            w = Double.parseDouble(weightList.get(0)) * 0.45;
        } else {
            w = Double.parseDouble(weightList.get(0));
        }
        double BMI = w/(h*h);
        result.append(String.format(Locale.ENGLISH, "%.2f", BMI));

        if (BMI < 18.5) {
            result.append(" Underweight");
        } else if (BMI >= 18.5 && BMI < 25) {
            result.append(" Normal weight");
        } else {
            result.append(" Overweight");
        }
        return result.toString();
    }

    // возвращает мультипликативное постоянство, которое представляет собой количество перемножений цифр в числе,
    // пока не получится однозанчное число
    public static int bugger(int number) {
        int count = 0; // счетчик перемножений

        while (true) {
            if (number < 10) { // если однозначное
                return count;
            }
            int p = 1; // промежуточное значение для подсчета произведения
            String strNumber = String.valueOf(number);

            for (int i = 0; i < strNumber.length(); i++) {
                p = p * (strNumber.charAt(i) - '0'); // код символа цифры минус код нуля равен значению этой цифры
            }
            count++;
            number = p;
        }
    }

    // преобразует строку в звездную стенографию
    public static String toStarShorthand(String str) {
        if ("".equals(str)) {
            return "";
        }

        StringBuilder result = new StringBuilder().append(str.charAt(0));
        int count = 1; // счетчик повторяющихся символов

        for (char i = 1; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(i-1)) {
                count++;
            } else if (count > 1) {
                result.append('*').append(count).append(str.charAt(i));
                count = 1;
            } else {
                result.append(str.charAt(i));
            }

            if (i == str.length()-1 && count > 1) { // обработка для последнего повторяющегося символа
                result.append('*').append(count);
            }
        }
        return result.toString();
    }

    // проверяет рифмуются ли строки
    public static boolean doesRhyme(String first, String second) {
        List<Character> vowels = new ArrayList<>(List.of('a', 'e', 'u', 'i', 'o', 'y')); // список всех гласных

        List<String> firstList = List.of(first.split(" ")); // выделение последнего слова в первой строке
        String firstLastWord = firstList.get(firstList.size()-1);

        List<String> secondList = List.of(second.split(" ")); // выделение последнего слова во второй строке
        String secondLastWord = secondList.get(secondList.size()-1);

        StringBuilder firstVowels = new StringBuilder();
        StringBuilder secondVowels = new StringBuilder();

        for (int i = 0; i < firstLastWord.length(); i++) { // сбор гласных
            if (vowels.contains(Character.toLowerCase(firstLastWord.charAt(i)))) {
                firstVowels.append(firstLastWord.charAt(i));
            }
        }
        for (int i = 0; i < secondLastWord.length(); i++) {
            if (vowels.contains(Character.toLowerCase(secondLastWord.charAt(i)))) {
                secondVowels.append(secondLastWord.charAt(i));
            }
        }

        return firstVowels.toString().equalsIgnoreCase(secondVowels.toString());
    }

    // принимает два целых числа и возвращает true, если
    // число повторяется три раза подряд в любом месте первого параметра и то же самое число
    // повторяется два раза подряд во втором параметре
    public static boolean trouble(int first, int second) {
        String firstString = String.valueOf(first); // преобразование чисел в строки
        String secondString = String.valueOf(second);

        char r = ' '; // повторяющаяся цифра
        int count = 1; // счетчик повторов

        for (int i = 1; i < firstString.length(); i++) { // поиск цифры, повторяющейся в первом числе три раза
            if (firstString.charAt(i) == firstString.charAt(i-1)) {
                count += 1;
            }
            r = firstString.charAt(i); // код символа цифры минус код нуля равен значению этой цифры
            if (count == 3) {
                break;
            }
        }

        if (r == ' ') { // если никакая цифра не повторилась три раза
            return false;
        }

        for (int i = 1; i < secondString.length(); i++) {
            // если эта же цифра повторилась два раза
            if (secondString.charAt(i) == secondString.charAt(i-1) && secondString.charAt(i) == r) {
                return true;
            }
        }
        return false;
    }

    // возвращает общее количество
    // уникальных символов между всеми парами концов книги
    public static int countUniqueBooks(String str, char c) {
        boolean inBook = false; // находится ли во вхождении
        HashSet<Character> uniqueSet = new HashSet<>(); // набор уникальных символов во всех вхождениях

        for (int i = 0; i < str.length(); i++) {
            // если символ внутри вхождения является уникальным
            if (inBook && !uniqueSet.contains(str.charAt(i)) && str.charAt(i) != c) {
                uniqueSet.add(str.charAt(i));
            }
            // начало вхождения
            if (str.charAt(i) == c && !inBook) {
                inBook = true;

            // выход из вхождения
            } else if (str.charAt(i) == c && inBook) {
                inBook = false;
            }
        }
        return uniqueSet.size();
    }
}