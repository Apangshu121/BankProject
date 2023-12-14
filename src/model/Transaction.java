package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Transaction {
    private long id;
    private String account_no;
    private BigDecimal amount;
    private LocalDateTime dateTimeOfTransaction;
    private boolean debit;
    private BigDecimal balanceAsOn;
    private String comment;

    public long getId() {
        return id;
    }

    public void setId() {
        this.id+=1;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateTimeOfTransaction() {
        return dateTimeOfTransaction;
    }

    public void setDateTimeOfTransaction(LocalDateTime dateTimeOfTransaction) {
        this.dateTimeOfTransaction = dateTimeOfTransaction;
    }

    public boolean isDebit() {
        return debit;
    }

    public void setDebit(boolean debit) {
        this.debit = debit;
    }

    public BigDecimal getBalanceAsOn() {
        return balanceAsOn;
    }

    public void setBalanceAsOn(BigDecimal balanceAsOn) {
        this.balanceAsOn = balanceAsOn;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
