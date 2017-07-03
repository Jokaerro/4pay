package pro.games_box.a4pay.presentation.screen.main.presenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

import pro.games_box.a4pay.data.datasource.DataSourceImpl;
import pro.games_box.a4pay.data.entity.Transaction;
import pro.games_box.a4pay.presentation.application.App;
import pro.games_box.a4pay.presentation.screen.base.BasePresenter;
import pro.games_box.a4pay.presentation.screen.main.view.MainView;

/**
 * Created by Tesla on 03.07.2017.
 */

public class MainPresenter extends BasePresenter<MainView> {

    @Inject
    public DataSourceImpl mDataSource;

    public MainPresenter(){
        App.getAppComponent().injectMainPresenter(this);
    }

    public void updateHistory(String userId, String dateFrom, String dateTo){
        if(!checkFields(userId, dateFrom, dateTo))
            return;
        mView.showProgressDialog();
        mDataSource.getHistory(userId, dateFrom, dateTo)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())

                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        MainPresenter.this.processError(throwable.getMessage());
                    }
                })
                .subscribe(new Consumer<List<Transaction>>() {
                               @Override
                               public void accept(@NonNull List<Transaction> s) throws Exception {
                                   MainPresenter.this.processTransactions(s);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                MainPresenter.this.processError(throwable.getMessage());
                            }
                        });
    }

    private boolean checkFields(String userId, String dateFrom, String dateTo){
        if(userId.isEmpty()){
            processInputError("Please input email");
            return false;

        } else if(dateFrom.isEmpty()) {
            processInputError("Please input date from");
            return false;

        } else if(dateTo.isEmpty()) {
            processInputError("Please input date to");
            return false;

        }

        return true;
    }

    private void processTransactions(List<Transaction> transactions) {
        mView.updateListView(transactions);
        mView.hideProgressDialog();
    }

    private void processInputError(String message){
        mView.errorToast(message);
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
