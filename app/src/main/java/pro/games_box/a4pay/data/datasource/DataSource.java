package pro.games_box.a4pay.data.datasource;

import java.util.List;

import io.reactivex.Observable;
import pro.games_box.a4pay.data.entity.Transaction;

/**
 * Created by Tesla on 03.07.2017.
 */

public interface DataSource {
    Observable<List<Transaction>> getHistory(String userId, String dateFrom, String dateTo);
}
