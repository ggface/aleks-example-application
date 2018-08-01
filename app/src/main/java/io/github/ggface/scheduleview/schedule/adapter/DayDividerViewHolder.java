package io.github.ggface.scheduleview.schedule.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import io.github.ggface.api.utils.PojoUtils;
import io.github.ggface.scheduleview.R;
import io.github.ggface.scheduleview.utils.FormatUtils;

/**
 * ViewHolder для отображения разделителя событий (Отображает дату).
 *
 * @author Ivan Novikov on 2018-08-01.
 */
public class DayDividerViewHolder extends RecyclerView.ViewHolder implements EventBinder {

    private final TextView mDateTextView;

    DayDividerViewHolder(View view) {
        super(view);
        mDateTextView = view.findViewById(R.id.date_text_view);
    }

    //region EventBinder

    /**
     * {@inheritDoc}
     */
    @Override
    public void bindView(@NonNull EventListItem event) {
        mDateTextView.setText(FormatUtils.formatDayLong(PojoUtils.checkNotNull(event).getDateTime()));
    }
    //endregion EventBinder
}
