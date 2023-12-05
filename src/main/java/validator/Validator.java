package validator;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class Validator {

    public static Predicate<String> isBridgeLengthValid() {
        return input -> {
            try {
                int length = Integer.parseInt(input);
                return length >= 3 && length <= 20;
            } catch (NumberFormatException e) {
                return false;
            }
        };
    }

    public static Predicate<String> isMoveValid() {
        return input -> input.equals("U") || input.equals("D");
    }

    public static Predicate<String> isRestartValid() {
        return input -> input.equals("R");
    }
}
