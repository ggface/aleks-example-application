package io.github.ggface.scheduleview.schedule.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import io.github.ggface.api.utils.CollectionUtils;
import io.github.ggface.scheduleview.R;

/**
 * Адаптер для вывода событий из расписания.
 *
 * @author Ivan Novikov on 2018-08-01.
 */
public class EventsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String UNKNOWN_VIEW_HOLDER_ERROR = "view holder %d not found";

    @NonNull
    private List<EventListItem> mItems;

    public EventsAdapter() {
        setHasStableIds(true);
        mItems = Collections.emptyList();
    }

    //region Adapter
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        RecyclerView.ViewHolder viewHolder;
        if (viewType == R.layout.list_item_event) {
            viewHolder = new EventViewHolder(view);
        } else if (viewType == R.layout.list_item_day_divider) {
            viewHolder = new DayDividerViewHolder(view);
        } else {
            throw new IllegalArgumentException(String.format(Locale.ENGLISH,
                    UNKNOWN_VIEW_HOLDER_ERROR, viewType));
        }
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        EventListItem eventListItem = mItems.get(position);
        return eventListItem.isEvent() ? R.layout.list_item_event : R.layout.list_item_day_divider;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EventBinder binder = (EventBinder) holder;
        binder.bindView(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public long getItemId(int position) {
        return mItems.get(position).hashCode();
    }
    //endregion Adapter

    /**
     * Обновляет адаптер
     *
     * @param items список событий
     */
    public void setItems(@Nullable List<EventListItem> items) {
        mItems = CollectionUtils.wrapListNonNull(items);
        notifyDataSetChanged();
    }
}