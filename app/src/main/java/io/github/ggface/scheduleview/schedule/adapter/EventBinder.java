package io.github.ggface.scheduleview.schedule.adapter;

import android.support.annotation.NonNull;

/**
 * ViewHolder Binder для отображения {@link EventListItem}
 *
 * @author Ivan Novikov on 2018-08-01.
 */
interface EventBinder {

    /**
     * Наполняет данными ViewHolder
     *
     * @param event актив портфеля
     */
    void bindView(@NonNull EventListItem event);
}