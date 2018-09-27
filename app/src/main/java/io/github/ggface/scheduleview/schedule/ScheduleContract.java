package io.github.ggface.scheduleview.schedule;

import java.util.List;

import io.github.ggface.scheduleview.mvp.BasePresenter;
import io.github.ggface.scheduleview.mvp.BaseView;
import io.github.ggface.scheduleview.schedule.adapter.EventListItem;


/**
 * Контракт для экрана расписания {@link ScheduleActivity}
 *
 * @author Ivan Novikov on 2018-07-31.
 */
interface ScheduleContract {

    interface View extends BaseView<Presenter> {

        /**
         * Сообщает о смене списка событий
         *
         * @param eventListItems список данных для вывода событий и дней
         */
        void onEventsChanged(List<EventListItem> eventListItems);
    }

    interface Presenter extends BasePresenter {

        /**
         * Запрос на получение событий
         *
         * @param force если true, игнорируем кеш
         */
        void obtainEvents(boolean force);
    }
}