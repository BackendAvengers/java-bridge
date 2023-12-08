package bridge.controller;

import java.util.function.Supplier;

public abstract class RetryController {

    protected <T> T execution(final Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                //view 생각하지말고 다음에 코드 붙여서 써버리기
                System.out.println(e.getMessage());
            }
        }
    }

}
