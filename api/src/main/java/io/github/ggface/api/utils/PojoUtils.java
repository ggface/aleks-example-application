package io.github.ggface.api.utils;

import android.support.annotation.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * Класс для работы с простыми объектами
 *
 * @author Ivan Novikov on 2018-07-031.
 */
public class PojoUtils {

    private PojoUtils() {
    }

    public static boolean isNull(Object value) {
        if (value instanceof String) {
            return ((String) value).isEmpty();
        } else if (value instanceof Collection) {
            return ((Collection) value).isEmpty();
        } else if (value instanceof Map) {
            return ((Map) value).isEmpty();
        }
        return value == null;
    }

    public static boolean isNotNull(Object value) {
        return !isNull(value);
    }

    /**
     * Проверяет что ссылка на объект не равна null
     *
     * @param reference ссылка на объект
     * @return ссылка на объект (не может быть null)
     */
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    /**
     * Определяет, равны ли два объекта
     *
     * <ul>
     * <li>{@code true} если {@code a} и {@code b} равны null.
     * <li>{@code true} если {@code a} и {@code b} не равны null и равны в соответствии с {@link Object#equals(Object)}.
     * <li>{@code false} во всех прочих ситуациях.
     * </ul>
     *
     * @param a исходый объект
     * @param b объект для сравнения с исходым
     * @return если true, объекты схожи
     */
    public static boolean equal(@Nullable Object a, @Nullable Object b) {
        return a == b || (a != null && a.equals(b));
    }

    /**
     * Генерирует хэш-код для нескольких значений.
     * Хэш-код генерируется путем вызова {@link Arrays # hashCode (Object [])}.
     *
     * @param objects перечисление объектов
     * @return хэш-код
     */
    public static int hashCode(@Nullable Object... objects) {
        return Arrays.hashCode(objects);
    }
}