package tsi.too.util;

import java.util.Locale;

/**
 * Convenience class for common Locale operations.
 * 
 * @author Lucas Cristovam
 * 
 * @version 0.1
 */
public abstract class LocaleUtils {
	
	/**
	 * Get a {@code Locale} as a Brazilian Locale.
	 * 
	 * @return The resulting {@code Locale}.
	 * 
	 * @since 0.1
	 */
	public static Locale getBrazilianLocale() {
        return new Locale("pt", "BR");
    }
}