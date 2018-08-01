package io.github.ggface.scheduleview.schedule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import io.github.ggface.api.RepositoryProvider;
import io.github.ggface.api.beans.EventBean;
import io.github.ggface.scheduleview.AleksApplication;
import io.github.ggface.scheduleview.R;
import timber.log.Timber;

/**
 * Экран расписания.
 *
 * @author Ivan Novikov on 2018-07-31.
 */
public class ScheduleActivity extends AppCompatActivity implements ScheduleContract.View {

    private Toolbar mToolbar;
    private RecyclerView mEventsRecyclerView;
    private ScheduleContract.Presenter mPresenter;
    private Toast mToast;

    //region Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);

        mToolbar = findViewById(R.id.toolbar);
        mEventsRecyclerView = findViewById(R.id.events_recycler_view);

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
    public void onEventsChanged(List<EventBean> events) {
        Timber.tag("sys_view").d("onEventsChanged(%s)", events);
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

}