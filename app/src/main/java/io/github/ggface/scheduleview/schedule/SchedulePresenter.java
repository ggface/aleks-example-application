package io.github.ggface.scheduleview.schedule;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Date;

import io.github.ggface.api.repositories.ScheduleRepository;
import io.github.ggface.api.utils.PojoUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Презентер для экрана {@link ScheduleActivity}
 *
 * @author Ivan Novikov on 2018-07-31.
 */
public class SchedulePresenter implements ScheduleContract.Presenter {

    private final ScheduleContract.View mView;
    private final ScheduleRepository mScheduleRepository;
    private final CompositeDisposable mSubscriptionDisposable;

    @Nullable
    private Disposable mDisposable;

    SchedulePresenter(@NonNull ScheduleContract.View view,
                      @NonNull ScheduleRepository scheduleRepository) {
        mView = PojoUtils.checkNotNull(view);
        mScheduleRepository = PojoUtils.checkNotNull(scheduleRepository);

        mSubscriptionDisposable = new CompositeDisposable();
    }

    @Override
    public void subscribe() {
        mSubscriptionDisposable.add(mScheduleRepository.events()
                .distinctUntilChanged()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mView::onEventsChanged));
    }

    @Override
    public void unsubscribe() {
        cancelObtainEvents();
        mSubscriptionDisposable.clear();
    }

    @Override
    public void obtainEvents(@NonNull Date date) {
        mView.setBalanceLoadingIndicator(false);

        cancelObtainEvents();
        mDisposable = mScheduleRepository.obtainEvents(date)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> mView.setBalanceLoadingIndicator(false),
                        t -> {
                            mView.setBalanceLoadingIndicator(false);
                            mView.showError(t.getMessage());
                        });
    }

    private void cancelObtainEvents() {
        if (mDisposable != null) {
            mDisposable.dispose();
            mDisposable = null;
        }
    }
}