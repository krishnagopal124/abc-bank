package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

public class TransactionTest {
	//Test instantiation of transaction
    @Test
    public void transaction() {
        Transaction t = new Transaction(new BigDecimal(5));
        assertTrue(t instanceof Transaction);
    }
    //Test instantiation of transaction amount
    @Test
    public void transactionAmount() {
        Transaction t = new Transaction(new BigDecimal(100.2));
        assertEquals(new BigDecimal(100.2), t.amount);
    }
}
