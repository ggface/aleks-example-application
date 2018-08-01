package io.github.ggface.api.repositories;

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
     * @return Результат выполнения
     */
    Completable obtainEvents();

    /**
     * @return источник списка событий
     */
    Flowable<List<EventBean>> events();
}