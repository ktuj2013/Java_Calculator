
import java.util.Scanner;

public class Calculator{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Main result = new Main();
        String a = sc.nextLine();
        String answer = result.calc(a);
        String b = "-";
        if (answer.contains(b)) {
            throw new NumberFormatException("throws Exception");
        }

        System.out.println(answer);
    }
}

class Main {
    public static String calc(String input) {
        boolean romanOrArab = false;
        String exception = "throws Exception";
        Main romanExam = new Main();
        Main arabToRoman = new Main();
        int result = 0;
        String[] inputSplit = input.split(" ");
        if (inputSplit.length != 3) {
            throw new NumberFormatException(exception);
        }

        Integer firstNumber = 0;
        Integer secondNumber = 0;
        if (inputSplit[0].matches("[-+]?\\d+") || inputSplit[2].matches("[-+]?\\d+")) {
            firstNumber = Integer.valueOf(inputSplit[0]);
            secondNumber = Integer.valueOf(inputSplit[2]);
        } else if (romanExam.romanToArab(inputSplit[0]) != null || romanExam.romanToArab(inputSplit[2]) != null) {
            firstNumber = romanExam.romanToArab(inputSplit[0]);
            secondNumber = romanExam.romanToArab(inputSplit[2]);
            romanOrArab = true;

        } else {
            throw new NumberFormatException(exception);
        }




        if ((firstNumber < 1) || (firstNumber > 10) || (secondNumber < 1) || (secondNumber > 10)){
            throw new NumberFormatException(exception);
        }

        String sign = inputSplit[1];
        switch (sign) {
            case "+" :
                result = firstNumber + secondNumber;
                break;
            case "-" :
                result = firstNumber - secondNumber;
                break;
            case "*" :
                result = firstNumber * secondNumber;
                break;
            case "/" :
                result = firstNumber / secondNumber;
                break;
            default :
            {
                throw new NumberFormatException(exception);
            }
        }

        String output;

        if (romanOrArab){
            if(result < 1){
                throw new NumberFormatException(exception);
            } else {
                output = arabToRoman.arabToRome(result);
            }
        } else {
            output = Integer.toString(result);
        }

        return output;
    }

    Integer romanToArab(String romanInput){
        int result = 0;
        int[] arab = {10, 9, 5, 4, 1};
        String[] roman = {"X", "IX", "V", "IV", "I"};
        for (int i = 0; i < arab.length; i++ ) {
            while (romanInput.indexOf(roman[i]) == 0) {
                result += arab[i];
                romanInput = romanInput.substring(roman[i].length());
            }
        }

        return result;
    }

    String arabToRome(int arabInput){
        String result = "";
        int value = 0;
        int[] arab = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < arab.length; i++){
            value = arabInput / arab[i];
            for (int j = 0; j < value; j++){
                result = result.concat(roman[i]);
            }
            arabInput = arabInput % arab[i];
        }
        return result;
    }
}