package io.github.ggface.api.utils;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для работы с коллекциями
 *
 * @author Ivan Novikov on 2017-12-07.
 */
public final class CollectionUtils {

    /**
     * Оборачивает List
     *
     * @param list исходный список
     * @return новый список. Если {@param list} равен null, вернет пустой список
     */
    @NonNull
    public static <T> List<T> wrapListNonNull(List<T> list) {
        List<T> result = new ArrayList<>();
        if (list != null) {
            result.addAll(list);
        }
        return result;
    }
}