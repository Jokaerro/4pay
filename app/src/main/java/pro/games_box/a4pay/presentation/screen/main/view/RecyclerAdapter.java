package pro.games_box.a4pay.presentation.screen.main.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pro.games_box.a4pay.R;
import pro.games_box.a4pay.data.entity.Transaction;

/**
 * Created by Tesla on 03.07.2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private static List<Transaction> mData;

    Context mContext;

    public RecyclerAdapter(Context context, List<Transaction> data) {
        mContext = context;
        if (data != null)
            mData = new ArrayList<>(data);
        else mData = new ArrayList<>();
    }

    public void swapData(List<Transaction> data){
        if (data != null) {
            for (int i = 0; i < data.size(); i++)
                if (data.get(i) == null)
                    data.remove(i);
            mData.clear();
            mData.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.card_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if(mData.get(position) != null) {
            holder.date.setText(mData.get(position).date);
            holder.amount.setText(mData.get(position).amount);
            holder.debit.setText(mData.get(position).debit);
            holder.credit.setText(mData.get(position).credit);
            holder.transactionId.setText(mData.get(position).transactionId);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.date) TextView date;
        @BindView(R.id.amount) TextView amount;
        @BindView(R.id.debit) TextView debit;
        @BindView(R.id.credit) TextView credit;
        @BindView(R.id.transaction_id) TextView transactionId;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
