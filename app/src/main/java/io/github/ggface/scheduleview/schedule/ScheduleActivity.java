package io.github.ggface.scheduleview.schedule;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import io.github.ggface.api.RepositoryProvider;
import io.github.ggface.scheduleview.AleksApplication;
import io.github.ggface.scheduleview.R;
import io.github.ggface.scheduleview.schedule.adapter.EventListItem;
import io.github.ggface.scheduleview.schedule.adapter.EventsAdapter;

/**
 * Экран расписания.
 *
 * @author Ivan Novikov on 2018-07-31.
 */
public class ScheduleActivity extends AppCompatActivity implements ScheduleContract.View {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mEventsRecyclerView;
    private ScheduleContract.Presenter mPresenter;
    private Toast mToast;
    private EventsAdapter mEventsAdapter;

    //region Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);
        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        mEventsRecyclerView = findViewById(R.id.events_recycler_view);

        initAdapter();
        initControls();

        mPresenter = new SchedulePresenter(this, getRepositoryProvider().getScheduleRepository());

        if (savedInstanceState == null) {
            mPresenter.obtainEvents(new Date());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.subscribe();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.unsubscribe();
    }
    //endregion Lifecycle

    //region ScheduleContract.View

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBalanceLoadingIndicator(boolean active) {
        mSwipeRefreshLayout.setRefreshing(active);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showError(String message) {
        showToast(message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEventsChanged(List<EventListItem> eventListItems) {
        mEventsAdapter.setItems(eventListItems);
    }
    //endregion ScheduleContract.View

    private AleksApplication getAleksApplication() {
        return (AleksApplication) getApplication();
    }

    private RepositoryProvider getRepositoryProvider() {
        return getAleksApplication().getRepositoryProvider();
    }

    private void showToast(CharSequence message) {
        hideToast();
        mToast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        mToast.show();
    }

    private void hideToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }

    private void initAdapter() {
        mEventsAdapter = new EventsAdapter();
    }

    private void initControls() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.accent);
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            mSwipeRefreshLayout.setRefreshing(false);
            mPresenter.obtainEvents(new Date());
        });

        mEventsRecyclerView.setAdapter(mEventsAdapter);
    }
}