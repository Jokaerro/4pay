package pro.games_box.a4pay.presentation.screen.transactions.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pro.games_box.a4pay.R;
import pro.games_box.a4pay.data.entity.Transaction;
import pro.games_box.a4pay.presentation.application.App;
import pro.games_box.a4pay.presentation.screen.main.view.RecyclerAdapter;
import pro.games_box.a4pay.presentation.screen.transactions.presenter.TransactionsPresenter;

public class TransactionsActivity extends AppCompatActivity implements TransactionsView {

    private static final String TRANSACTIONS_USERID = "userid";
    private static final String TRANSACTIONS_DATA_FROM = "datefrom";
    private static final String TRANSACTIONS_DATA_TO = "dateto";

    private String mUserId;
    private String mDateFrom;
    private String mDateTO;

    private List<Transaction> mTransactions = new ArrayList<>();
    private ProgressDialog mProgressDialog;
    private RecyclerAdapter mAdapter;

    @Inject
    TransactionsPresenter mPresenter;

    @BindView(R.id.transactions)
    RecyclerView mRecycler;

    public static void start(Context context, String userId, String dateFrom, String dateTo) {
        Intent intent = new Intent(context, TransactionsActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString(TRANSACTIONS_USERID, userId);
        bundle.putString(TRANSACTIONS_DATA_FROM, dateFrom);
        bundle.putString(TRANSACTIONS_DATA_TO, dateTo);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        Bundle bundle = getIntent().getExtras();
        mUserId = bundle.getString(TRANSACTIONS_USERID);
        mDateFrom = bundle.getString(TRANSACTIONS_DATA_FROM);
        mDateTO = bundle.getString(TRANSACTIONS_DATA_TO);

        ButterKnife.bind(this);

        App.getAppComponent().injectTransactionActivity(this);

        mPresenter.setView(this);

        initListView();
    }

    @Override
    protected void onStart() {
        super.onStart();

        mPresenter.updateHistory(mUserId, mDateFrom, mDateTO);
    }

    private void initListView() {
        mRecycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(llm);

        mAdapter = new RecyclerAdapter(this, mTransactions);
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void updateListView(List<Transaction> transactions) {
        mAdapter.swapData(transactions);
    }

    @Override
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
        }
        mProgressDialog.show();

    }

    @Override
    public void hideProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }

    }

    @Override
    public void errorHandling(String message) {
        String errorMessage = getResources().getString(R.string.error_msg) + " " + message;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        dialog.setTitle(getString(R.string.error));
        dialog.setMessage(errorMessage);
        dialog.setButton(DialogInterface.BUTTON_NEUTRAL, getString(R.string.error_button), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        dialog.show();

        testRecycler();

    }

    private void testRecycler(){
        Transaction tr1 = new Transaction( "1233123", "124.82", "+7985123123", "+7684023123", "16.07.17 14:40");
        Transaction tr2 = new Transaction( "1233126", "126.14", "+7985123154", "+7946123123", "19.07.17 18:50");
        Transaction tr3 = new Transaction( "1233126", "126.14", "+7985123154", "+7946123123", "19.07.17 18:50");

        ArrayList<Transaction> trs = new ArrayList<>();
        trs.add(tr1);
        trs.add(tr2);
        trs.add(tr3);

        updateListView(trs);
    }
}
