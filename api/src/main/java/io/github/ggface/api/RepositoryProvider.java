package io.github.ggface.api;

import android.support.annotation.NonNull;

/**
 * Провайдер репозиториев. Обеспечивает доступ к слою данных.
 *
 * @author Ivan Novikov on 2018-07-31.
 */
public interface RepositoryProvider {

    /**
     * @return API для расписания
     */
    @NonNull
    ScheduleRemoteApi getScheduleApi();
}