package com.abc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.abc.util.BigDecimalUtil;

import java.math.BigDecimal;

public class Customer {
    private String name;
    private List<Account> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }

    public String getName() {
        return name;
    }

    public Customer openAccount(Account account) {
        accounts.add(account);
        return this;
    }
    
    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public void transferMoney(int fromAccNum, int toAccNum, BigDecimal amount) {
    	if(fromAccNum>=accounts.size() || toAccNum >= accounts.size()){
    		throw new IndexOutOfBoundsException("Account does not exist for the account number: "+((fromAccNum>=accounts.size())?fromAccNum: toAccNum));
    	}
    	else if (fromAccNum != toAccNum){
    		Account fromAccount = accounts.get(fromAccNum);
    		Account toAccount = accounts.get(toAccNum);
        	fromAccount.withdraw(amount);
        	toAccount.deposit(amount);
    	}
    }
    public BigDecimal totalInterestEarned() {
        BigDecimal total = BigDecimal.ZERO;
        for (Account a : accounts)
            total = a.interestEarned();
        return total;
    }

    public String getStatement() {
        String statement = null;
        statement = "Statement for " + name + "\n";
        BigDecimal total = BigDecimal.ZERO;
        for (Account a : accounts) {
            statement += "\n" + statementForAccount(a) + "\n";
            total = total.add(a.sumTransactions());
        }
        statement += "\nTotal In All Accounts " + BigDecimalUtil.toDollars(total);
        return statement;
    }

    private String statementForAccount(Account a) {
        String s = "";

       //Translate to pretty account type
        switch(a.getAccountType()){
            case Account.CHECKING:
                s += "Checking Account\n";
                break;
            case Account.SAVINGS:
                s += "Savings Account\n";
                break;
            case Account.MAXI_SAVINGS:
                s += "Maxi Savings Account\n";
                break;
        }

        //Now total up all the transactions
        BigDecimal total = BigDecimal.ZERO;
        for (Transaction t : a.transactions) {
        	BigDecimal transactionAmt = t.amount;
        	
            s += "  " + (transactionAmt.compareTo(BigDecimal.ZERO) < 0 ? "withdrawal" : "deposit") + " " + BigDecimalUtil.toDollars(transactionAmt) + "\n";
            total = total.add(transactionAmt);
        }
        s += "Total " + BigDecimalUtil.toDollars(total);
        return s;
    }

   
}
