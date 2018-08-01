package io.github.ggface.api;

import android.support.annotation.NonNull;

import io.github.ggface.api.repositories.ScheduleRepository;
import io.github.ggface.api.repositories.remote.RemoteScheduleRepository;
import io.github.ggface.api.utils.PojoUtils;

/**
 * Реализация фасада репозиториев для удаленной работы (сеть)
 *
 * @author Ivan Novikov on 2018-08-01.
 */
public class RemoteFacade implements RepositoryProvider {

    private final ScheduleRepository mScheduleRepository;

    public RemoteFacade(@NonNull RetrofitApi retrofitApi) {
        PojoUtils.checkNotNull(retrofitApi);
        mScheduleRepository = new RemoteScheduleRepository(retrofitApi);
    }

    //region RepositoryProvider

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public ScheduleRepository getScheduleRepository() {
        return mScheduleRepository;
    }
    //endregion RepositoryProvider
}
