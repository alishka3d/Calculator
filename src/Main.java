import java.util.Scanner;

public class Main {

    public static String[] romans = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        String str;
        while (true) {
            System.out.println("Input:");
            str = scanner.nextLine();
            if (str.equals("exit")) break;
            System.out.println("\nOutput:\n" + calc(str) + "\n");
        }
    }

    public static String calc(String input) throws Exception {
        int a = 0;
        int b = 0;
        int romNum = 0; // переменая для подсчета количества Римских цифр.

        String[] elements = input.split(" ");
        if (elements.length < 2) {
            throw new Exception("строка не является математической операцией");
        }
        if (elements.length > 3) {
            throw new Exception("формат математической операции не удовлетворяет " +
                    "заданию - два операнда и один оператор (+, -, /, *)");
        }

        for (int i = 0; i < romans.length; i++) {
            if (romans[i].equals(elements[0])) {
                a = i + 1;
                romNum++;
            }
            if (romans[i].equals(elements[2])) {
                b = i + 1;
                romNum++;
            }
        }

        if (a == 0) {
            a = Integer.parseInt(elements[0]);
        }
        if (b == 0) {
            b = Integer.parseInt(elements[2]);
        }

        if (a > 10 || b > 10) {
            throw new Exception("числа должны быть не более 10");
        }
        char[] sign = elements[1].toCharArray();
        int result = 0;
        switch (sign[0]) {
            case ('+') -> result = a + b;
            case ('-') -> result = a - b;
            case ('*') -> result = a * b;
            case ('/') -> result = a / b;
        }

        if (romNum != 0) {
            if (romNum == 1) throw new Exception("используются одновременно разные системы счисления");
            if (result <= 0) throw new Exception("в римской системе нет отрицательных чисел или нуля");

            return arabToRoman(result);
        }
        return String.valueOf(result);
    }

    public static String arabToRoman(int num) {
        String[] keys = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values = {100, 90, 50, 40, 10, 9, 5, 4, 1};

        StringBuilder ret = new StringBuilder();
        int index = 0;

        while (index < keys.length) {
            while (num >= values[index]) {
                int d = num / values[index];
                num = num % values[index];
                ret.append(keys[index].repeat(d));
            }
            index++;
        }
        return ret.toString();
    }
    
}