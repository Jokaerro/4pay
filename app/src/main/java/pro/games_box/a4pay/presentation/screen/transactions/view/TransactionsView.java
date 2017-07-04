package pro.games_box.a4pay.presentation.screen.transactions.view;

import java.util.List;

import pro.games_box.a4pay.data.entity.Transaction;

/**
 * Created by Tesla on 04.07.2017.
 */

public interface TransactionsView {
    void updateListView(List<Transaction> transactions);

    void showProgressDialog();

    void hideProgressDialog();

    void errorHandling(String message);
}
