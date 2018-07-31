package io.github.ggface.api.utils;

import android.support.annotation.Nullable;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * Класс для работы с простыми объектами
 *
 * @author Ivan Novikov on 2018-07-031.
 */
public class PojoUtils {

//    private static final ThreadLocal<DateTimeFormatter> DATE_REMOTE = new ThreadLocal<DateTimeFormatter>() {
//        @Override
//        protected DateTimeFormatter initialValue() {
//            return DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
//        }
//    };

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
     * Конвертирует значение {@code byte} в {@code boolean}. Если значение не равно 0, то вернет
     * true, иначе - false
     *
     * @param value значение для конвертации
     * @return true или false
     */
    public static boolean toBoolean(byte value) {
        return value != 0;
    }

    /**
     * Конвертирует значение {@code boolean} в {@code byte}. Если true, то вернет 1, иначе - 0
     *
     * @param value значение для конвертации
     * @return 1 или 0
     */
    public static byte toByte(boolean value) {
        return (byte) (value ? 1 : 0);
    }

    public static String toString(@Nullable BigDecimal bigDecimal) {
        String result = "0.0";
        if (bigDecimal != null) {
            result = String.valueOf(bigDecimal.doubleValue());
        }
        return result;
    }

    public static BigDecimal toBigDecimal(@Nullable String string) {
        BigDecimal result = BigDecimal.ZERO;
        if (StringUtils.isNotEmpty(string)) {
            result = new BigDecimal(string);
        }
        return result;
    }

    /**
//     * Преобразователь String в DateTime для парсинга
//     *
//     * @param milliseconds
//     * @return объект даты
//     */
//    public static DateTime formatRemoteDate(Long milliseconds) {
//        return new DateTime(milliseconds, DateTimeZone.getDefault());
//    }
//
//    /**
//     * Преобразователь String в DateTime для парсинга
//     *
//     * @param date дата в формате сервера
//     * @return объект даты
//     */
//    public static DateTime formatRemoteDate(String date) {
//        return DATE_REMOTE.get().parseDateTime(date);
//    }

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