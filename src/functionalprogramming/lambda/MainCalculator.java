package functionalprogramming.lambda;

import java.util.Random;
import java.util.function.IntBinaryOperator;

public class MainCalculator {
    public static void main(String[] args) {
        Calculator calculator = (int x, int y) ->{
            Random random = new Random();
            int randomNumber = random.nextInt(50);
            return x * y + randomNumber;
        };
        System.out.println(calculator.calculate(1, 2));

        // use util.function pre-defined set of functions
        IntBinaryOperator intBinaryOperator = (int x, int y) ->{
            Random random = new Random();
            int randomNumber = random.nextInt(50);
            return x * y + randomNumber;
        };
        System.out.println(intBinaryOperator.applyAsInt(1, 2));
    }
}
