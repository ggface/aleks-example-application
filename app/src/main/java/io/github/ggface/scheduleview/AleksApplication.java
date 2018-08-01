package io.github.ggface.scheduleview;

import android.os.StrictMode;
import android.support.multidex.MultiDexApplication;

import com.crashlytics.android.Crashlytics;
import com.squareup.leakcanary.LeakCanary;

import io.fabric.sdk.android.Fabric;
import io.github.ggface.api.RemoteFacade;
import io.github.ggface.api.RepositoryProvider;
import io.github.ggface.api.RetrofitApi;
import timber.log.Timber;

/**
 * Класс приложения
 *
 * @author Ivan Novikov on 2018-07-31.
 */
public class AleksApplication extends MultiDexApplication {

    private RepositoryProvider mRepositoryProvider;

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        Fabric.with(this, new Crashlytics());

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());

            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
        }

        mRepositoryProvider = new RemoteFacade(new RetrofitApi());
    }

    public RepositoryProvider getRepositoryProvider() {
        return mRepositoryProvider;
    }
}