package io.github.ggface.api.repositories.remote;

import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.List;

import io.github.ggface.api.RetrofitApi;
import io.github.ggface.api.beans.EventBean;
import io.github.ggface.api.repositories.ScheduleRepository;
import io.github.ggface.api.utils.PojoUtils;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.processors.BehaviorProcessor;
import io.reactivex.schedulers.Schedulers;

/**
 * Сетевая реализация {@link ScheduleRepository}.
 *
 * @author Ivan Novikov on 2018-08-01.
 */
public class RemoteScheduleRepository implements ScheduleRepository {

    private final RetrofitApi mRetrofitApi;
    private final BehaviorProcessor<List<EventBean>> mEventsProcessor = BehaviorProcessor.create();

    public RemoteScheduleRepository(@NonNull RetrofitApi retrofitApi) {
        mRetrofitApi = PojoUtils.checkNotNull(retrofitApi);
    }

    //region ScheduleRepository

    /**
     * {@inheritDoc}
     */
    @Override
    public Completable obtainEvents(boolean force) {
        Completable result;
        List<EventBean> events = mEventsProcessor.getValue();
        if (events == null || force) {
            result = mRetrofitApi.getScheduleApi().getEvents()
                    .retryWhen(mRetrofitApi::retryPolicy)
                    .map(list -> {
                        Collections.sort(list, (o1, o2) -> Integer.compare(o1.getWeekDay(), o2.getWeekDay()));
                        return list;
                    })
                    .doOnSuccess(mEventsProcessor::onNext)
                    .toCompletable()
                    .observeOn(Schedulers.io())
                    .observeOn(Schedulers.io());
        } else {
            return Completable.complete();
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Flowable<List<EventBean>> events() {
        return mEventsProcessor.onBackpressureLatest();
    }
    //endregion ScheduleRepository
}