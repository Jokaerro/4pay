package pro.games_box.a4pay.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Tesla on 03.07.2017.
 */

public class Transaction {

    @SerializedName("TransactionID")
    @Expose
    public String transactionId;

    @SerializedName("amount")
    @Expose
    public String amount;

    @SerializedName("Debit")
    @Expose
    public String debit;

    @SerializedName("Credit")
    @Expose
    public String credit;

    @SerializedName("Date")
    @Expose
    public String date;

    public Transaction(String transactionId, String amount, String debit, String credit, String date) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.debit = debit;
        this.credit = credit;
        this.date = date;
    }
}
