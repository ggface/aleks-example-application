package io.github.ggface.api;

import android.support.annotation.NonNull;

import java.util.Date;
import java.util.List;

import io.github.ggface.api.beans.EventBean;
import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * Контракт репозитория для работы с расписанием
 *
 * @author Ivan Novikov on 2018-07-31.
 */
public interface ScheduleRepository {

    /**
     * Запрашивает список событий
     *
     * @param date дата для выборки событий
     * @return Результат выполнения
     */
    Completable obtainCountries(@NonNull Date date);

    /**
     * @return источник списка событий
     */
    Flowable<List<EventBean>> countries();
}