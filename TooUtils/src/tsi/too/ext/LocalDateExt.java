package tsi.too.ext;

import java.time.LocalDate;
import java.util.Objects;

public class LocalDateExt {
	public static boolean isInPeriod(LocalDate date, LocalDate start, LocalDate end) {
		Objects.requireNonNull(date);
		Objects.requireNonNull(start);
		Objects.requireNonNull(end);
		
		return (date.isEqual(start) || date.isEqual(end)) || date.isAfter(start) && date.isBefore(end);
	}
}
