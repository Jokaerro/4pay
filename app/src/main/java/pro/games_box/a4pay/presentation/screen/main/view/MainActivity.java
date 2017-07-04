package pro.games_box.a4pay.presentation.screen.main.view;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pro.games_box.a4pay.R;
import pro.games_box.a4pay.data.entity.Transaction;
import pro.games_box.a4pay.presentation.application.App;
import pro.games_box.a4pay.presentation.screen.main.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainView {
    private final Calendar dateFrom = Calendar.getInstance();

    @BindView(R.id.auth)
    RelativeLayout mAuth;

    @BindView(R.id.login_email_et)
    EditText mEmail;

    @BindView(R.id.login_date_from_et)
    EditText mDateFrom;

    @BindView(R.id.login_date_to_et)
    EditText mDateTo;

    @Inject
    MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        App.getAppComponent().injectMainActivity(this);

        mPresenter.setView(this);

        mDateFrom.setFocusable(false);
        mDateFrom.setFocusableInTouchMode(false);

        mDateTo.setFocusable(false);
        mDateTo.setFocusableInTouchMode(false);
    }

    @OnClick(R.id.login_date_from_et)
    void getDateFrom(){
        getCalendar("from");
    }

    @OnClick(R.id.login_date_to_et)
    void getDateTo(){
        getCalendar("to");
    }

    @OnClick(R.id.enter_btn)
    void getTransactions(){
        mPresenter.processAuth(this, mEmail.getText().toString(), mDateFrom.getText().toString(), mDateTo.getText().toString());
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mPresenter.destroy();
    }

    @Override
    public void errorToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void getCalendar(final String whoseDate){
        DatePickerDialog dialog;
        Calendar time = Calendar.getInstance();
        dialog = new DatePickerDialog(MainActivity.this,
                android.app.AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                dateFrom.set(Calendar.YEAR, year);
                dateFrom.set(Calendar.MONTH, monthOfYear);
                dateFrom.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDate(dateFrom, whoseDate);
            }
        },time.get(Calendar.YEAR ), time.get(Calendar.MONTH), time.get(Calendar.DAY_OF_MONTH));

        dialog.show();
    }

    private void updateDate(Calendar date, String whoseDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.US);
        if (date == null) {
            if(whoseDate.equals("from")) {
                mDateFrom.setText("");
            } else {
                mDateTo.setText("");
            }
        } else {
            if(whoseDate.equals("from")) {
                mDateFrom.setText(sdf.format(date.getTime()));
            } else {
                mDateTo.setText(sdf.format(date.getTime()));
            }
        }
    }
}
