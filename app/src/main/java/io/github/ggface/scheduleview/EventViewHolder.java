package io.github.ggface.scheduleview;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import io.github.ggface.api.beans.EventBean;
import io.github.ggface.api.utils.PojoUtils;

/**
 * ViewHolder для отображения события.
 *
 * @author Ivan Novikov on 2018-07-31.
 */
public class EventViewHolder extends RecyclerView.ViewHolder {

    private TextView mEventNameTextView;
    private TextView mEventTimeRangeTextView;

    @Nullable
    private EventBean mEventBean;

    public EventViewHolder(View view) {
        super(view);
        mEventNameTextView = view.findViewById(R.id.event_name_text_view);
        mEventTimeRangeTextView = view.findViewById(R.id.event_time_range_text_view);
    }

    /**
     * Заполняет данными view
     *
     * @param eventBean событие
     */
    public void bindView(@NonNull EventBean eventBean) {
        mEventBean = PojoUtils.checkNotNull(eventBean);

        mEventNameTextView.setText(eventBean.getName());
        mEventTimeRangeTextView.setText("12:00 - 13:00");
    }
}
