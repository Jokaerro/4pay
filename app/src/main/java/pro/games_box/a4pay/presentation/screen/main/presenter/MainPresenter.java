package pro.games_box.a4pay.presentation.screen.main.presenter;

import android.content.Context;
import pro.games_box.a4pay.presentation.application.App;
import pro.games_box.a4pay.presentation.screen.base.BasePresenter;
import pro.games_box.a4pay.presentation.screen.main.view.MainView;
import pro.games_box.a4pay.presentation.screen.transactions.view.TransactionsActivity;

/**
 * Created by Tesla on 03.07.2017.
 */

public class MainPresenter extends BasePresenter<MainView> {

    public MainPresenter(){
        App.getAppComponent().injectMainPresenter(this);
    }

    public void processAuth(Context context, String userId, String dateFrom, String dateTo){
        if(!checkFields(userId, dateFrom, dateTo))
            return;
        else
            TransactionsActivity.start(context, userId, dateFrom, dateTo);
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

    private void processInputError(String message){
        mView.errorToast(message);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
