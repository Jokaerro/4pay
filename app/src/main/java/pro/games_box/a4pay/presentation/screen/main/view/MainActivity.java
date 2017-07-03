package pro.games_box.a4pay.presentation.screen.main.view;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import pro.games_box.a4pay.R;
import pro.games_box.a4pay.data.entity.Transaction;
import pro.games_box.a4pay.presentation.application.App;
import pro.games_box.a4pay.presentation.screen.main.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainView {
    private List<Transaction> mTransactions;
    private ProgressDialog mProgressDialog;

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

    }

    @OnClick(R.id.enter_btn)
    void getTransactions(){
        mPresenter.updateHistory("1234", "16.07.17 14:40", "21.07.17 14:40");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mPresenter.destroy();
    }

    @Override
    public void updateListView(List<Transaction> transactions) {

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
    }
}
