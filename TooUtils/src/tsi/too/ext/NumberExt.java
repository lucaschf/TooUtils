package tsi.too.ext;

import java.text.NumberFormat;

import tsi.too.util.LocaleUtils;

/**
 * Convenience class for common numeric operations.
 * 
 * @author Lucas Cristovam
 * @version 0.1
 */

public abstract class NumberExt {

	/**
	 * Formats a number for the Brazilian currency.
	 * 
	 * @param number the number to format.
	 * @return a String formatted as Brazilian currency.
	 * 
	 * @since 0.1
	 */
    public static String toBrazilianCurrency(final Number number) {
        return NumberFormat.getCurrencyInstance(LocaleUtils.getBrazilianLocale()).format(number);
    }
}