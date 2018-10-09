package com.fateking.yi.utils;

import java.util.StringJoiner;
import java.util.function.Function;

public class StringUtils {

    public static final <T> String join(CharSequence c, Iterable<T> iterable, Function<T, String> function) {
        StringJoiner joiner = new StringJoiner(",");
        for (T t : iterable) {
            joiner.add(function.apply(t));
        }
        return joiner.toString();
    }
}
