package pro.games_box.a4pay.presentation.di;

import javax.inject.Singleton;

import dagger.Component;
import pro.games_box.a4pay.presentation.screen.main.presenter.MainPresenter;
import pro.games_box.a4pay.presentation.screen.main.view.MainActivity;

/**
 * Created by Tesla on 03.07.2017.
 */

@Singleton
@Component(modules = {AppModule.class, PresenterModule.class, DataModule.class})
public interface AppComponent {
    void injectMainActivity(MainActivity activity);

    void injectMainPresenter(MainPresenter mainPresenter);
}
