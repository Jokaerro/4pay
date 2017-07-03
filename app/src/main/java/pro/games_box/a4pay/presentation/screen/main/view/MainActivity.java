package pro.games_box.a4pay.presentation.screen.main.view;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pro.games_box.a4pay.R;
import pro.games_box.a4pay.data.entity.Transaction;
import pro.games_box.a4pay.presentation.application.App;
import pro.games_box.a4pay.presentation.screen.main.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainView {
    private List<Transaction> mTransactions = new ArrayList<>();
    private ProgressDialog mProgressDialog;
    private RecyclerAdapter mAdapter;


    @BindView(R.id.transactions)
    RecyclerView mRecycler;

    @BindView(R.id.auth)
    RelativeLayout mAuth;

    @BindView(R.id.reset)
    FloatingActionButton mReset;

    @Inject
    MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        App.getAppComponent().injectMainActivity(this);

        initListView();

        mPresenter.setView(this);
    }

    private void initListView() {
        mRecycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(llm);

        mAdapter = new RecyclerAdapter(this, mTransactions);
        mRecycler.setAdapter(mAdapter);
    }

    @OnClick(R.id.enter_btn)
    void getTransactions(){
        mPresenter.updateHistory("1234", "16.07.17 14:40", "21.07.17 14:40");
    }

    @OnClick(R.id.reset)
    void getReset(){
        mAuth.setVisibility(View.VISIBLE);
        mRecycler.setVisibility(View.GONE);
        mReset.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mPresenter.destroy();
    }

    @Override
    public void updateListView(List<Transaction> transactions) {
        mAdapter.swapData(transactions);
        mAuth.setVisibility(View.GONE);
        mRecycler.setVisibility(View.VISIBLE);
        mReset.setVisibility(View.VISIBLE);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        dialog.setTitle(getString(R.string.error));
        dialog.setMessage(message);
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
