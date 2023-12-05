package bridge;

import camp.nextstep.edu.missionutils.Console;
import java.util.function.Function;
import java.util.function.Predicate;
import static validator.Validator.isBridgeLengthValid;
import static validator.Validator.isMoveValid;
import static validator.Validator.isRestartValid;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {

    public static String promptAndGetInput(String message){
        System.out.println(message);
        return Console.readLine();
    }

    public static <T> T inputWithValidations(String promptMessage, Predicate<String> validator, Function<String, T> converter){
        while (true) {
            String input = promptAndGetInput(promptMessage);
            if (!validator.test(input)) {
                System.out.println("유효하지 않은 입력입니다.");
                continue; // 다음 반복으로 넘어감
            }
            return converter.apply(input); // 유효한 경우 변환
        }
    }

    /**
     * 다리의 길이를 입력받는다.
     */
    public static int readBridgeSize() {
        return inputWithValidations(
                "다리의 길이를 입력하세요: ",
                isBridgeLengthValid(),
                Integer::parseInt
                );
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public static String readMoving() {
        return inputWithValidations(
                "이동할 칸을 입력하세요.(위 : 'U', 아래 'D') : ",
                isMoveValid(),
                Function.identity()
        );
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public static String readRestart() {
        return inputWithValidations(
                "재시작 하시려면 R, 종료하시려면 그 외 아무키나 입력하세요.",
                isRestartValid(),
                Function.identity()
        );
    }

}