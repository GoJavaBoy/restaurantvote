package com.lunchvote;

import static com.lunchvote.util.ValidationUtil.getRootCause;
import static org.junit.Assert.assertThrows;

public class TestUtil {
    //  Check root cause in JUnit: https://github.com/junit-team/junit4/pull/778
    public static <T extends Throwable> void validateRootCause(Class<T> rootExceptionClass, Runnable runnable) {
        assertThrows(rootExceptionClass, () -> {
            try {
                runnable.run();
            } catch (Exception e) {
                throw getRootCause(e);
            }
        });
    }
}
