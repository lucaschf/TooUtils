package tsi.too.ext;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Convenience class for common {@link LocalDate} operations.
 *  
 * @author Lucas Cristovam
 * @version 0.1
 */
public class LocalDateExt {
	/**
	 * Checks whether a date is between two (inclusive) dates. 
	 * 
	 * @param date the date to be verified.
	 * @param startDateInclusive the start date of the target period.
	 * @param endDateInclusive the end date of the target period.
	 * @return true if the date is between or is equal to at least one of the dates.
	 * 
	 * @since 0.1	  
	 */
	public static boolean isInPeriod(LocalDate date, LocalDate startDateInclusive, LocalDate endDateInclusive) {
		Objects.requireNonNull(date);
		Objects.requireNonNull(startDateInclusive);
		Objects.requireNonNull(endDateInclusive);
		
		return (date.isEqual(startDateInclusive) || date.isEqual(endDateInclusive)) 
				|| date.isAfter(startDateInclusive) && date.isBefore(endDateInclusive);
	}
}
