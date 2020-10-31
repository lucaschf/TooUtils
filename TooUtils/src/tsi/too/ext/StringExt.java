package tsi.too.ext;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import tsi.too.util.LocaleUtils;

/**
 * Convenience class for {@link String} operations
 * 
 * @author Lucas Cristovam
 * 
 * @version 0.2
 */
public abstract class StringExt {

	/**
	 * Creates a copy of the string without its last character.
	 * 
	 * @param source the source {@code java.lang.String}.
	 * @return The resulting {@code java.lang.String}.
	 * 
	 * @since 0.1
	 */
	public static String removeLastChar(final String source) {
		return source.replaceFirst(".$", "");
	}

	/**
	 * Parses the {@code String} as a {@code Long} and returns the result.
	 * 
	 * @param str the {@code String} to be parsed.
	 * @return the {@code Long} value represented by the {@code String} or {@code 0}
	 *         if the {@code String} cannot be parsed.
	 * 
	 * @since 0.1
	 */
	public static long toLong(final String str) {
		try {
			return Long.parseLong(str);
		} catch (NumberFormatException ex) {
			return 0;
		}
	}

	/**
	 * Parses the {@code String} as a {@code Integer} and returns the result.
	 * 
	 * @param str the {@code String} to be parsed.
	 * @return the {@code Integer} value represented by the {@code String} or
	 *         {@code 0} if the {@code String} cannot be parsed.
	 * 
	 * @since 0.1
	 */
	public static int toInt(final String str) {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException ex) {
			return 0;
		}
	}

	/**
	 * Parses the {@code String} as a {@code Double} and returns the result.
	 * 
	 * @param str the {@code String} to be parsed.
	 * @return the {@code Double} value represented by the {@code String} or
	 *         {@code 0} if the {@code String} cannot be parsed.
	 * 
	 * @since 0.1
	 */
	public static double toDouble(final String str) {
		try {
			if (str.contains(",")) {
				return Double.parseDouble(str.replace(",", ""));
			}
			
			return Double.parseDouble(str);
		} catch (NumberFormatException ex) {
			return 0;
		}
	}

	/**
	 * Parses the {@code String} as a {@code BigDecimal} and returns the result.
	 * 
	 * @param str the {@code String} to be parsed.
	 * @return the {@code BigDecimal} value represented by the {@code String} or
	 *         {@code 0} if the {@code String} cannot be parsed.
	 * 
	 * @since 0.1
	 */
	public static BigDecimal toBigDecimal(final String str) {
		try {
			return new BigDecimal(str);
		} catch (NumberFormatException ex) {
			return BigDecimal.ZERO;
		}
	}

	/**
	 * Parses the formated Brazilian currency{@code String} as a {@code BigDecimal}
	 * and returns the result.
	 * 
	 * @param str the {@code String} to be parsed.
	 * @return the {@code BigDecimal} value represented by the {@code String} or
	 *         {@code 0} if the {@code String} cannot be parsed.
	 * @throws ParseException if the {@code String} is malformed.
	 * 
	 * @since 0.2
	 */
	public static BigDecimal fromBraziliaCurrencyString(final String str) throws ParseException {
		return fromCurrencyString(str, LocaleUtils.getBrazilianLocale());
	}

	/**
	 * Parses the currency{@code String} as a {@code BigDecimal} and returns the
	 * result.
	 * 
	 * @param str    the {@code String} to be parsed.
	 * @param locale the base locale.
	 * @return the {@code BigDecimal} value represented by the {@code String} or
	 *         {@code 0} if the {@code String} cannot be parsed.
	 * @throws ParseException if the {@code String} is malformed.
	 * 
	 * @since 0.2
	 */
	public static BigDecimal fromCurrencyString(final String str, final Locale locale) throws ParseException {
		final NumberFormat format = NumberFormat.getNumberInstance(locale);

		if (format instanceof DecimalFormat) {
			((DecimalFormat) format).setParseBigDecimal(true);
		}

		return (BigDecimal) format.parse(str.replaceAll("[^\\d.,]", ""));
	}

	public static boolean isNullOrBlank(String str) {
		return str == null || str.isBlank();
	}
}