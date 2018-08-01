package io.github.ggface.scheduleview.schedule.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import io.github.ggface.api.beans.EventBean;
import io.github.ggface.api.utils.PojoUtils;
import io.github.ggface.scheduleview.R;
import io.github.ggface.scheduleview.utils.FormatUtils;

/**
 * ViewHolder для отображения события.
 *
 * @author Ivan Novikov on 2018-07-31.
 */
public class EventViewHolder extends RecyclerView.ViewHolder implements EventBinder {

    private TextView mEventNameTextView;
    private TextView mEventTimeRangeTextView;

    EventViewHolder(View view) {
        super(view);
        mEventNameTextView = view.findViewById(R.id.event_name_text_view);
        mEventTimeRangeTextView = view.findViewById(R.id.event_time_range_text_view);
    }

    //region EventBinder

    /**
     * {@inheritDoc}
     */
    @Override
    public void bindView(@NonNull EventListItem event) {
        EventBean eventBean = PojoUtils.checkNotNull(event.getEvent());
        mEventNameTextView.setText(eventBean.getName());
        mEventTimeRangeTextView.setText(FormatUtils.formatTimeRange(eventBean));
    }
    //endregion EventBinder
}