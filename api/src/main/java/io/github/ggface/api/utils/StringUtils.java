package io.github.ggface.api.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Класс для работы со строками.
 *
 * @author Ivan Novikov on 2018-07-31.
 */
public final class StringUtils {

    private StringUtils() {
    }

    /**
     * Возвращает не-{@code null}-строку.
     *
     * @param nullableString Строка, которая может быть {@code null}
     * @return {@code nullableString} или пустая строка, но не {@code null}.
     */
    @NonNull
    public static String nonNull(@Nullable String nullableString) {
        if (nullableString == null) {
            return "";
        }

        return nullableString;
    }

    /**
     * Возвращает {@code boolean} - статус строки
     *
     * @param s Строка, которая может быть {@code null}
     * @return {@code true} если строка равна {@code null} или ее длина равна 0. В противном
     * случае  {@code false}
     */
    public static boolean isEmpty(@Nullable String s) {
        return s == null || s.length() == 0;
    }

    /**
     * Возвращает {@code boolean} - статус строки
     *
     * @param s Строка, которая может быть {@code null}
     * @return {@code false} если строка равна {@code null} или ее длина равна 0. В противном
     * случае  {@code true}
     */
    public static boolean isNotEmpty(@Nullable String s) {
        return !isEmpty(s);
    }

    /**
     * Отрезает подстроку, содержащую повторяющиеся с конца символы
     *
     * @param source   исходная строка
     * @param trimChar символ для сокращения
     * @return обработанная строка
     */
    @NonNull
    public static String trimEnd(@Nullable String source, char trimChar) {
        StringBuilder workStringBuilder = new StringBuilder(nonNull(source));
        while (workStringBuilder.toString().endsWith(String.valueOf(trimChar))) {
            workStringBuilder = workStringBuilder
                    .replace(workStringBuilder.toString().lastIndexOf(trimChar),
                            workStringBuilder.toString().lastIndexOf(trimChar) + 1, "");
        }
        return workStringBuilder.toString();
    }
}