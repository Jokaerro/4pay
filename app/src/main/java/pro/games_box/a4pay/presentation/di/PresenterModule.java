package pro.games_box.a4pay.presentation.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pro.games_box.a4pay.presentation.screen.main.presenter.MainPresenter;

/**
 * Created by Tesla on 03.07.2017.
 */

@Module
public class PresenterModule {
    @Provides
    @Singleton
    MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }
}
