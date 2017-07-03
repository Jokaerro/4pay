package pro.games_box.a4pay.data.datasource;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import pro.games_box.a4pay.data.api.RestApi;
import pro.games_box.a4pay.data.entity.Transaction;

/**
 * Created by Tesla on 03.07.2017.
 */

public class DataSourceImpl implements DataSource {
    private RestApi mRestApi;

    @Inject
    public DataSourceImpl(@NonNull RestApi restApi) {
        mRestApi = restApi;
    }

    @Override
    public Observable<List<Transaction>> getHistory(String userId, String dateFrom, String dateTo) {
        return mRestApi.getHistory(userId, dateFrom, dateTo);
    }
}
