package io.github.ggface.api;

import android.support.annotation.NonNull;
import android.util.Pair;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import io.github.ggface.api.beans.EventBean;
import io.github.ggface.api.deserializers.EventDeserializer;
import io.reactivex.Flowable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit API
 *
 * @author Ivan Novikov on 2018-07-31.
 */
public class RetrofitApi implements RepositoryProvider {

    private static final int REQUEST_REPEAT_DELAY = 1;

    private final Gson mGson;
    private final ScheduleRemoteApi mScheduleRemoteApi;

    public RetrofitApi() {
        mGson = new GsonBuilder()
                .registerTypeAdapter(EventBean.class, new EventDeserializer())
                .create();

        HttpLoggingInterceptor logger = new HttpLoggingInterceptor()
                .setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logger)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        mScheduleRemoteApi = new Retrofit.Builder()
                .client(client)
                .baseUrl(BuildConfig.API_HOST)
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ScheduleRemoteApi.class);
    }

    //region RepositoryProvider

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public ScheduleRemoteApi getScheduleApi() {
        return mScheduleRemoteApi;
    }

    //endregion RepositoryProvider

    /**
     * // TODO: 2018-07-31 JD
     *
     * @param errors поставщик ошибок
     * @return поставщик
     */
    public Flowable<?> retryPolicy(Flowable<? extends Throwable> errors) {
        return errors.zipWith(Flowable.range(1, 2), Pair::new).flatMap(pair -> {
            Throwable error = pair.first;
            int tryCount = pair.second;
            if (tryCount > 1) {
                return Flowable.error(error);
            }
            return Flowable.timer(REQUEST_REPEAT_DELAY, TimeUnit.SECONDS);
        });
    }
}