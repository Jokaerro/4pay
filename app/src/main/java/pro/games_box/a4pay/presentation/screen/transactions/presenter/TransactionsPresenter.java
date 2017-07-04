package pro.games_box.a4pay.presentation.screen.transactions.presenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import pro.games_box.a4pay.data.datasource.DataSourceImpl;
import pro.games_box.a4pay.data.entity.Transaction;
import pro.games_box.a4pay.presentation.application.App;
import pro.games_box.a4pay.presentation.screen.base.BasePresenter;
import pro.games_box.a4pay.presentation.screen.transactions.view.TransactionsView;

/**
 * Created by Tesla on 04.07.2017.
 */

public class TransactionsPresenter extends BasePresenter<TransactionsView> {

    @Inject
    public DataSourceImpl mDataSource;

    public TransactionsPresenter(){
        App.getAppComponent().injectTransactionsPresenter(this);
    }

    public void updateHistory(String userId, String dateFrom, String dateTo){
        mView.showProgressDialog();
        mDataSource.getHistory(userId, dateFrom, dateTo)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())

                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        TransactionsPresenter.this.processError(throwable.getMessage());
                    }
                })
                .subscribe(new Consumer<List<Transaction>>() {
                               @Override
                               public void accept(@NonNull List<Transaction> s) throws Exception {
                                   TransactionsPresenter.this.processTransactions(s);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                TransactionsPresenter.this.processError(throwable.getMessage());
                            }
                        });
    }

    private void processTransactions(List<Transaction> transactions) {
        mView.updateListView(transactions);
        mView.hideProgressDialog();
    }

    private void processError(String message){
        mView.errorHandling(message);
        mView.hideProgressDialog();

    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
