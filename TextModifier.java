import java.util.Scanner;

public class TextModifier {

    public static void main(String[] args) {
        String result = textModifier();
        System.out.println("Изменённый текст: " + result);
    }

    public static String textModifier() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите текст: ");
        String inputText = scanner.nextLine();

        // 1. Удаление лишних пробелов
        StringBuilder processedText = new StringBuilder();
        boolean lastWasSpace = false;

        // Убираем лишние пробелы
        for (char c : inputText.toCharArray()) {
            if (c != ' ') {
                processedText.append(c);
                lastWasSpace = false;
            } else if (!lastWasSpace) {
                processedText.append(c);
                lastWasSpace = true;
            }
        }

        StringBuilder finalText = new StringBuilder();
        int sumOfDigits = 0;

        // 2. Обработка символов
        for (int i = 0; i < processedText.length(); i++) {
            char currentChar = processedText.charAt(i);

            if (currentChar == '-') {
                // Проверяем наличие символов слева и справа от '-'
                if (i > 0 && i < processedText.length() - 1) {
                    char leftChar = finalText.charAt(finalText.length() - 1); // символ слева от '-'
                    char rightChar = processedText.charAt(i + 1); // символ справа от '-'

                    // Удаляем последний добавленный символ (leftChar)
                    finalText.deleteCharAt(finalText.length() - 1);
                    // Меняем местами символы и добавляем их в правильном порядке
                    finalText.append(rightChar);
                    finalText.append(leftChar);
                    i++; // Пропускаем следующий символ (правый от '-')
                }
                continue; // Пропускаем знак '-'
            } else if (currentChar == '+') {
                finalText.append('!'); // Заменяем '+' на '!'
            } else if (Character.isDigit(currentChar)) {
                sumOfDigits += Character.getNumericValue(currentChar); // Считаем сумму цифр
            } else {
                finalText.append(currentChar); // Добавляем обычный символ
            }
        }

        // Если были цифры, добавляем их сумму в конец
        if (sumOfDigits > 0) {
            finalText.append(" ").append(sumOfDigits);
        }

        scanner.close();
        return finalText.toString().trim(); // Обрезаем итоговую строку от пробелов
    }
}