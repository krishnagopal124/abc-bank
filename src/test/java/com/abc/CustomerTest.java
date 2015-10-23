package com.abc;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

public class CustomerTest {

    @Test //Test customer statement generation
    public void testApp(){

        Account checkingAccount = new Account(Account.CHECKING);
        Account savingsAccount = new Account(Account.SAVINGS);

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(new BigDecimal(100.0));
        savingsAccount.deposit(new BigDecimal(4000.0));
        savingsAccount.withdraw(new BigDecimal(200.0));

        String statement = "Statement for Henry\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "Total $100.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "Total $3,800.00\n" +
                "\n" +
                "Total In All Accounts $3,900.00";
		assertEquals(statement, henry.getStatement());
    }
    
    //Test customer one account
    @Test
    public void testOneAccount(){
        Customer oscar = new Customer("Oscar").openAccount(new Account(Account.SAVINGS));
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    //Test customer two accounts
    @Test
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar")
                .openAccount(new Account(Account.SAVINGS));
        oscar.openAccount(new Account(Account.CHECKING));
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    //Test customer three accounts
    @Test
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar")
                .openAccount(new Account(Account.SAVINGS));
        oscar.openAccount(new Account(Account.CHECKING));
        oscar.openAccount(new Account(Account.MAXI_SAVINGS));
        assertEquals(3, oscar.getNumberOfAccounts());
    }
    
    /*MONEY TRANSFER BETWEEN ACCOUNTS*/
    //Test customer money transfer between accounts 
    @Test
    public void testMoneyTransferSuccess() {
		Customer wilde = new Customer("Wilde");
		Account savingsAcc1 = new Account(Account.SAVINGS);
		Account checkingAcc1 = new Account(Account.CHECKING);
        wilde.openAccount(savingsAcc1);
		wilde.openAccount(checkingAcc1);
		savingsAcc1.deposit(new BigDecimal(1000));
		wilde.transferMoney(0, 1, new BigDecimal(357));
		  String statement = "Statement for Wilde\n" +
	                "\n" +
	                "Savings Account\n" +
	                "  deposit $1,000.00\n" +
	                "  withdrawal $357.00\n" +
	                "Total $643.00\n" +
	                "\n" +
	                "Checking Account\n" +
	                "  deposit $357.00\n" +
	                "Total $357.00\n" +
	                "\n" +
	                "Total In All Accounts $1,000.00";
		assertEquals(statement, wilde.getStatement());
    }
    //Test customer money transfer between accounts failure for wrong acc numbers
    @Test
    public void testMoneyTransferFailure() {
		try{
			Customer wilde = new Customer("Wilde");
			Account savingsAcc1 = new Account(Account.SAVINGS);
			Account checkingAcc1 = new Account(Account.CHECKING);
	        wilde.openAccount(savingsAcc1);
			wilde.openAccount(checkingAcc1);
			savingsAcc1.deposit(new BigDecimal(1000));
			wilde.transferMoney(1, 2, new BigDecimal(357));
		}catch(Exception e){
			String statement = "Account does not exist for the account number";
			Assert.assertTrue(e.getMessage().contains(statement));
		}
    }
}
