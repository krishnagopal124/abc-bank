package com.abc;

import java.math.BigDecimal;
import java.util.Date;

import com.abc.util.DateProvider;

public class Transaction {
    public final BigDecimal amount;

    private Date transactionDate;

    public Transaction(BigDecimal amount) {
        this.amount = amount;
        this.setTransactionDate(DateProvider.getInstance().now());
    }

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

}
