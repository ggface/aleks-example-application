package io.github.ggface.api.utils;

import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Класс для работы с коллекциями
 *
 * @author Ivan Novikov on 2017-12-07.
 */
public final class CollectionUtils {

    /**
     * Возвращает отфильтрованный список
     *
     * @param list      список для фильтрации
     * @param predicate условие для фильтрации
     * @param <T>       тип элеметов списка
     * @return отфильтрованный или пустой список {@link Collections#EMPTY_LIST}, если {@param list} пустой или null
     */
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result;
        if (list == null || list.isEmpty()) {
            result = Collections.emptyList();
        } else {
            PojoUtils.checkNotNull(predicate);
            result = new ArrayList<>();
            //noinspection ForLoopReplaceableByForEach
            for (int i = 0; i < list.size(); i++) {
                T item = list.get(i);
                try {
                    if (predicate.test(item)) {
                        result.add(item);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return result;
    }

    /**
     * Возвращает true, если {@param collection} не содержит элементов или null
     *
     * @param collection коллекция для проверки
     * @return true если не содержит элементов или null, иначе false
     */
    public static <T> boolean isEmpty(@Nullable Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

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

    /**
     * Оборачивает Set
     *
     * @param set исходный набор
     * @return новый {@code HashSet}. Если {@param set} равен null, вернет пустой {@code HashSet}
     */
    @NonNull
    public static <T> Set<T> wrapSetNonNull(Set<T> set) {
        Set<T> result = new HashSet<>();
        if (set != null) {
            result.addAll(set);
        }
        return result;
    }

    /**
     * Оборачивает Map
     *
     * @param map исходная хеш-таблица
     * @return новый {@code HashSet}. Если {@param set} равен null, вернет пустой {@code HashSet}
     */
    @NonNull
    public static <K, V> Map<K, V> wrapMapNonNull(@Nullable Map<K, V> map) {
        Map<K, V> result = new HashMap<>();
        if (map != null) {
            result.putAll(map);
        }
        return result;
    }

    /**
     * Объеденяет два List
     *
     * @param list1 список
     * @param list2 список
     * @return список, объединивший два List
     */
    @NonNull
    public static <T> List<T> mergeListNonNull(@Nullable List<T> list1, @Nullable List<T> list2) {
        List<T> result = new ArrayList<>();
        result.addAll(wrapListNonNull(list1));
        result.addAll(wrapListNonNull(list2));
        return result;
    }

    public static <F, T> List<T> transform(@NonNull List<F> fromList, @NonNull Function<F, T> function) {
        List<T> toList = new ArrayList<>();
        for (F element : fromList) {
            try {
                toList.add(function.apply(element));
            } catch (Exception ignore) {
            }
        }
        return toList;
    }

    public static <T> List<T> readArrayList(Parcel parcel, ClassLoader loader) {
        List<T> list = new ArrayList<>();
        parcel.readList(list, loader);
        return list;
    }
}