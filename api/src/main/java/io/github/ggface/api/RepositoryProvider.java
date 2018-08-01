package io.github.ggface.api;

import android.support.annotation.NonNull;

import io.github.ggface.api.repositories.ScheduleRepository;

/**
 * Провайдер репозиториев. Обеспечивает доступ к слою данных.
 *
 * @author Ivan Novikov on 2018-07-31.
 */
public interface RepositoryProvider {

    /**
     * @return репозиторий расписания
     */
    @NonNull
    ScheduleRepository getScheduleRepository();
}