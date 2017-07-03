package pro.games_box.a4pay.presentation.screen.main.view;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import pro.games_box.a4pay.data.datasource.DataSourceImpl;
import pro.games_box.a4pay.data.entity.Transaction;
import pro.games_box.a4pay.presentation.screen.base.BasePresenter;

/**
 * Created by Tesla on 03.07.2017.
 */

public interface MainView {
    void updateListView(List<Transaction> transactions);

    void showProgressDialog();

    void hideProgressDialog();

    void errorHandling(String message);
}
