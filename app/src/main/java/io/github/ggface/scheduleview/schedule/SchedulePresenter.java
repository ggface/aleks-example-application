package io.github.ggface.scheduleview.schedule;

import android.support.annotation.NonNull;

import java.util.Date;

import io.github.ggface.api.utils.PojoUtils;

/**
 * Презентер для экрана {@link ScheduleActivity}
 *
 * @author Ivan Novikov on 2018-07-31.
 */
public class SchedulePresenter implements ScheduleContract.Presenter {

    private final ScheduleContract.View mView;

    public SchedulePresenter(@NonNull ScheduleContract.View view) {
        mView = PojoUtils.checkNotNull(view);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void obtainEvents(@NonNull Date date) {

    }
}