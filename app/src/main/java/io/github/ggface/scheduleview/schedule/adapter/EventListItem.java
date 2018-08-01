package io.github.ggface.scheduleview.schedule.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.joda.time.DateTime;

import io.github.ggface.api.beans.EventBean;
import io.github.ggface.api.utils.PojoUtils;

/**
 * Описывает элемент списка для вывода событий и дней недели
 *
 * @author Ivan Novikov on 2018-08-01.
 */
public class EventListItem {

    private final EventBean mEventBean;
    private final DateTime mDateTime;

    private EventListItem(@Nullable EventBean eventBean, @Nullable DateTime dateTime) {
        mEventBean = eventBean;
        mDateTime = dateTime;
    }

    @NonNull
    public static EventListItem newHistory(@NonNull EventBean eventBean) {
        return new EventListItem(PojoUtils.checkNotNull(eventBean), null);
    }

    @NonNull
    public static EventListItem newDate(@NonNull DateTime dateTime) {
        return new EventListItem(null, PojoUtils.checkNotNull(dateTime));
    }

    @Nullable
    public EventBean getEvent() {
        return mEventBean;
    }

    @Nullable
    public DateTime getDateTime() {
        return mDateTime;
    }

    public boolean isEvent() {
        return mEventBean != null && mDateTime == null;
    }
}