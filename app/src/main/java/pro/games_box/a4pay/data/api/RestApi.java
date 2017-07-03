package pro.games_box.a4pay.data.api;

import java.util.List;

import io.reactivex.Observable;
import pro.games_box.a4pay.data.entity.Transaction;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Tesla on 03.07.2017.
 */

public interface  RestApi {
    @GET("history")
    Observable<List<Transaction>> getHistory(@Query("userid") String userId,
                                             @Query("datefrom") String dateFrom,
                                             @Query("dateto") String dateTo);
}
