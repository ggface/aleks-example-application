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
}