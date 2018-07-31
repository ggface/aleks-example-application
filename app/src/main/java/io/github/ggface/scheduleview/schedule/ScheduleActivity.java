package io.github.ggface.scheduleview.schedule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import io.github.ggface.api.beans.EventBean;
import io.github.ggface.scheduleview.R;

/**
 * Экран расписания.
 *
 * @author Ivan Novikov on 2018-07-31.
 */
public class ScheduleActivity extends AppCompatActivity implements ScheduleContract.View {

    private Toolbar mToolbar;
    private RecyclerView mEventsRecyclerView;
    private ScheduleContract.Presenter mPresenter;

    //region Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);

        mToolbar = findViewById(R.id.toolbar);
        mEventsRecyclerView = findViewById(R.id.events_recycler_view);

        mPresenter = new SchedulePresenter(this);
    }
    //endregion Lifecycle

    //region ScheduleContract.View

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBalanceLoadingIndicator(boolean active) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showError(int code) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEventsChanged(List<EventBean> events) {

    }
    //endregion ScheduleContract.View
}