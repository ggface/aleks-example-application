package io.github.ggface.api;

import java.util.List;

import io.github.ggface.api.beans.EventBean;
import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * API для работы с расписанием
 *
 * @author Ivan Novikov on 2018-07-31.
 */
public interface ScheduleRemoteApi {

    @GET("schedule/get_group_lessons_v2/1")
    Single<List<EventBean>> getEvents();
}