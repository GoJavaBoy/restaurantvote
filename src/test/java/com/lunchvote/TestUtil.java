package com.lunchvote;

import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;

public class TestUtil {

    public static <T> void isEqual(List<T> expected, List<T> actual, BiConsumer<T, T> matcher) {
        if (expected.size() != actual.size()) {
            throw new AssertionError("List sizes are not same");
        }
        Iterator<T> itExp = expected.iterator();
        Iterator<T> itAct = actual.iterator();
        while (itExp.hasNext()) {
            T exp = itExp.next();
            T act = itAct.next();
            matcher.accept(exp, act);
        }
    }
}
