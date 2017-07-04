package pro.games_box.a4pay.presentation.di;

import javax.inject.Singleton;

import dagger.Component;
import pro.games_box.a4pay.presentation.screen.main.presenter.MainPresenter;
import pro.games_box.a4pay.presentation.screen.main.view.MainActivity;
import pro.games_box.a4pay.presentation.screen.transactions.presenter.TransactionsPresenter;
import pro.games_box.a4pay.presentation.screen.transactions.view.TransactionsActivity;

/**
 * Created by Tesla on 03.07.2017.
 */

@Singleton
@Component(modules = {AppModule.class, PresenterModule.class, DataModule.class})
public interface AppComponent {
    void injectMainActivity(MainActivity activity);

    void injectTransactionActivity(TransactionsActivity activity);

    void injectMainPresenter(MainPresenter mainPresenter);

    void injectTransactionsPresenter(TransactionsPresenter transactionsPresenter);
}
