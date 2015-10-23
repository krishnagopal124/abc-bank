package com.abc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;

    private final int accountType;
    public List<Transaction> transactions;

    public Account(int accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }

    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
        }
    }

	public void withdraw(BigDecimal amount) {
	    if (amount.compareTo(BigDecimal.ZERO) <= 0) {
	        throw new IllegalArgumentException("amount must be greater than zero");
	    } else {
	        transactions.add(new Transaction(amount.negate()));
	    }
	}

    public BigDecimal interestEarned() {
        BigDecimal amount = sumTransactions();
        BigDecimal thousandDol = new BigDecimal(1000);
		BigDecimal twoThousandDol = new BigDecimal(2000);
		switch(accountType){
            case SAVINGS:
            	if (amount.compareTo(thousandDol) <= 0)
                    return amount.multiply(new BigDecimal(.001));
                else
                    return amount.subtract(thousandDol).multiply(new BigDecimal(0.002)).add(BigDecimal.ONE);
            case MAXI_SAVINGS:
                if (amount.compareTo(thousandDol) <= 0) {
					BigDecimal maxiSavingsIR1 = new BigDecimal(0.02);
					return amount.multiply(maxiSavingsIR1);
				}
                else if (amount.compareTo(twoThousandDol) <= 0) {
					BigDecimal maxiSavingsIR2 = new BigDecimal(0.05);
					BigDecimal maxiSavingsMin = new BigDecimal(20);
					return (amount.subtract(thousandDol).multiply(maxiSavingsIR2)).add(maxiSavingsMin);
				}
                else
                return (amount.subtract(twoThousandDol)).multiply(new BigDecimal(0.1)).add(new BigDecimal(70));
            default: // CHECKINGS ACCOUNT
                return amount.multiply(new BigDecimal(0.001));
        }
    }

    public BigDecimal sumTransactions() {
       return checkIfTransactionsExist(true);
    }

    private BigDecimal checkIfTransactionsExist(boolean checkAll) {
        BigDecimal amount = BigDecimal.ZERO;
        for (Transaction t: transactions)
            amount = amount.add(t.amount);
        return amount;
    }

    public int getAccountType() {
        return accountType;
    }

}
