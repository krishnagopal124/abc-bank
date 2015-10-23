package com.abc.util;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class BigDecimalUtil {

	public BigDecimalUtil() {
		// TODO Auto-generated constructor stub
	}
	public static String toDollars(BigDecimal d){
	        return NumberFormat.getCurrencyInstance().format(d.abs());
    }
}
