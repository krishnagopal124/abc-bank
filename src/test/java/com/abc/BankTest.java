package com.abc;

import org.junit.Test;

import com.abc.util.BigDecimalUtil;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

public class BankTest {
	
	/* BANK SUMMARY TESTS*/
	//Bank Manager retrieves customer summary - one customer one account
    @Test
    public void oneCustomerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(Account.CHECKING));
        bank.addCustomer(john);
        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }
 
    //Bank Manager retrieves customer summary - one customer multiple accounts
    @Test
    public void oneCustomerMulAccSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(Account.CHECKING));
        john.openAccount(new Account(Account.SAVINGS));
        john.openAccount(new Account(Account.MAXI_SAVINGS));
        bank.addCustomer(john);
        assertEquals("Customer Summary\n - John (3 accounts)", bank.customerSummary());
    }
  //Bank Manager retrieves customer summary - multiple customers one account each
    @Test
    public void multipleCustomersSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        Customer morra = new Customer("Morra");
        Customer loki = new Customer("Loki");
        john.openAccount(new Account(Account.CHECKING));
        loki.openAccount(new Account(Account.SAVINGS));
        morra.openAccount(new Account(Account.CHECKING));
        morra.openAccount(new Account(Account.SAVINGS));
        bank.addCustomer(john);
        bank.addCustomer(loki);
        bank.addCustomer(morra);
        assertEquals("Customer Summary\n - John (1 account)\n - Loki (1 account)\n - Morra (2 accounts)", bank.customerSummary());
    }//Bank Manager retrieves customer summary - multiple customers multiple accounts each
    @Test
    public void mulCusMulAccSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        Customer morra = new Customer("Morra");
        john.openAccount(new Account(Account.CHECKING));
        morra.openAccount(new Account(Account.CHECKING));
        morra.openAccount(new Account(Account.SAVINGS));
        bank.addCustomer(john);
        bank.addCustomer(morra);
        assertEquals("Customer Summary\n - John (1 account)\n - Morra (2 accounts)", bank.customerSummary());
    }
    
    /*	INTEREST PAID TESTS*/
	//test checking account deposits
    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.CHECKING);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(new BigDecimal(100));
        assertEquals(BigDecimalUtil.toDollars(new BigDecimal(0.1)), BigDecimalUtil.toDollars(bank.totalInterestPaid()));
    }
    
    //test savings account
    @Test
    public void savings_account() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(new BigDecimal(1500.0));
        assertEquals(BigDecimalUtil.toDollars(new BigDecimal(2.0)), BigDecimalUtil.toDollars(bank.totalInterestPaid()));
    }

    //test interest on maxi_savings account
    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(new BigDecimal(3000.0));
        assertEquals(BigDecimalUtil.toDollars(new BigDecimal(170)), BigDecimalUtil.toDollars(bank.totalInterestPaid()));
    }
    

}
