import java.util.Scanner;

public class TextModifier {

    public static void main(String[] args) {
        textModifier();
    }

    public static void textModifier() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите текст: ");
        String inputText = scanner.nextLine();

        // Обработка текста
        String modifiedText = processText(inputText);

        // Выводим изменённый текст
        System.out.println("Изменённый текст: " + modifiedText);

        scanner.close();
    }

    public static String processText(String text) {
        StringBuilder result = new StringBuilder();
        boolean lastWasSpace = false; // Переменная для отслеживания последнего символа
        int digitSum = 0; // Сумма цифр
        boolean negative = false; // Флаг для отслеживания знака минус

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);

            // Если текущий символ - пробел
            if (currentChar == ' ') {
                if (!lastWasSpace) { // Если предыдущий символ не пробел
                    result.append(currentChar); // Добавляем один пробел
                    lastWasSpace = true; // Обновляем состояние
                }
            } else if (currentChar == '-') {
                // Если перед символом '-' находится цифра
                if (i < text.length() - 1 && Character.isDigit(text.charAt(i + 1))) {
                    negative = true; // Устанавливаем флаг, чтобы учесть отрицательное число
                } else { // Меняем местами символы, находящиеся до и после знака '-'
                    if (i > 0 && i < text.length() - 1) {
                        char leftChar = text.charAt(i - 1);
                        char rightChar = text.charAt(i + 1);
                        result.setCharAt(result.length() - 1, rightChar); // Замена последнего добавленного символа
                        result.append(leftChar); // Добавляем левый символ

                        // Пропускаем следующий символ (правый)
                        i++;
                    }
                }
            } else if (Character.isDigit(currentChar)) {
                // Добавляем цифру к общей сумме без учета знака
                digitSum += Character.getNumericValue(currentChar); // Добавляем цифру в сумму
                // Сбрасываем флаг после обработки цифры
                negative = false;
            } else if (currentChar == '+') {
                result.append('!'); // Заменяем знак '+' на '!'
            } else {
                // Простой символ, добавляем его в результат
                result.append(currentChar);
                lastWasSpace = false; // Обновляем состояние
            }
        }

        // Добавляем сумму цифр в конец результата, если она не равна 0
        if (digitSum != 0) {
            result.append(" ").append(digitSum);
        }

        return result.toString();
    }
}
