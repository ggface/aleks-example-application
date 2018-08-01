package io.github.ggface.scheduleview.schedule;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.github.ggface.api.beans.EventBean;
import io.github.ggface.api.repositories.ScheduleRepository;
import io.github.ggface.api.utils.PojoUtils;
import io.github.ggface.scheduleview.schedule.adapter.EventListItem;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
                .map(this::improveDays)
                .subscribeOn(Schedulers.io())
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
        mView.setBalanceLoadingIndicator(true);

        cancelObtainEvents();
        mDisposable = mScheduleRepository.obtainEvents()
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

    /**
     * Модифициует данные
     *
     * Так как гет запрос не обрабатывает даты, а вью хочется сделать с нормальным выводом по дням,
     * здесь числа недели преобразую в даты, где 1 это сегодня.
     *
     * @param sourceList список событий
     * @return список подготовленных для вывода данных
     */
    @NonNull
    private List<EventListItem> improveDays(@NonNull List<EventBean> sourceList) {
        List<EventListItem> viewList = new ArrayList<>(sourceList.size());
        int i = 0;

        int marker = 0;
        while (i <= sourceList.size() - 1) {
            EventBean eventBean = sourceList.get(i++);
            DateTime eventDate = DateTime.now().minusDays(1).plusDays(eventBean.getWeekDay());

            int currentMarket = eventDate.getYear() + eventDate.getDayOfYear();
            if (currentMarket > marker) {
                viewList.add(EventListItem.newDate(eventDate));
                marker = currentMarket;
            }

            viewList.add(EventListItem.newHistory(eventBean));
        }
        return viewList;
    }
}