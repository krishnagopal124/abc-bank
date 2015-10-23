package com.abc;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class AccountTest {

	public AccountTest() {
		// TODO Auto-generated constructor stub
	}
	 //Test illegal Argument exception on negative deposit 
    @Test
    public void testForDepositException(){
       try{
    	   Account checkingAccount = new Account(Account.SAVINGS);
    	   checkingAccount.deposit(new BigDecimal(-20));
        }
       catch(IllegalArgumentException e ){
    	   Assert.assertEquals("amount must be greater than zero",e.getMessage());
       }
       
    }
    //Test illegal Argument exception on negative withdrawal 
    @Test
    public void testForWithdrawalException(){
       try{
    	   Account checkingAccount = new Account(Account.SAVINGS);
    	   checkingAccount.withdraw(new BigDecimal(-20));
        }
       catch(IllegalArgumentException e ){
    	   Assert.assertEquals("amount must be greater than zero",e.getMessage());
       }
       
    }

}
