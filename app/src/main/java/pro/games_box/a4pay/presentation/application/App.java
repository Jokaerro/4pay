package pro.games_box.a4pay.presentation.application;

import android.app.Application;

import pro.games_box.a4pay.presentation.di.AppComponent;
import pro.games_box.a4pay.presentation.di.AppModule;
import pro.games_box.a4pay.presentation.di.DaggerAppComponent;

/**
 * Created by Tesla on 03.07.2017.
 */

public class App extends Application {

    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }
}
