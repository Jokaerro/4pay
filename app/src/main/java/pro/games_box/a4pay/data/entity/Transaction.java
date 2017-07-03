package pro.games_box.a4pay.data.entity;

/**
 * Created by Tesla on 03.07.2017.
 */

public class Transaction {

    public String transactionId;
    public String amount;
    public String debit;
    public String credit;
    public String date;

    public Transaction(String transactionId, String amount, String debit, String credit, String date) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.debit = debit;
        this.credit = credit;
        this.date = date;
    }
}
