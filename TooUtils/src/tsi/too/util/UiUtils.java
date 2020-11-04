package tsi.too.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.InternationalFormatter;
import javax.swing.text.MaskFormatter;

/**
 * Convenience class for commonly used UI methods.
 * 
 * @author Lucas Cristovam
 * @version 0.2
 */
public class UiUtils {
	
	/**
	 * Creates a {@link MaskFormatter} foe Brazilian date
	 * 
	 * @return the created {@link MaskFormatter}
	 * 
	 * @since 0.1
	 */
	public static MaskFormatter createBrazilianDateMaskFormatter() {
		String mask = "##/##/####";
		
		try {
			MaskFormatter maskFormatter = new MaskFormatter(mask);
			maskFormatter.setPlaceholderCharacter('_');

			return maskFormatter;
		} catch (Exception ex) {
			return null;
		}
	}
		
	/**
	 * Creates a {@link AbstractFormatterFactory} for currency.
	 * 
	 * @param minValue the minimum acceptable value.
	 * @param maxValue the maximum acceptable value.
	 * @return the created {@link AbstractFormatterFactory}.
	 * 
	 * @since 0.1
	 */
	public static AbstractFormatterFactory createCurrencyFormatterFactory(Double minValue, Double maxValue) {
		return new AbstractFormatterFactory() {
            @Override
            public AbstractFormatter getFormatter(JFormattedTextField tf) {
                NumberFormat format = DecimalFormat.getInstance();
                format.setMinimumFractionDigits(2);
                format.setMaximumFractionDigits(2);
                format.setRoundingMode(RoundingMode.HALF_UP);
                InternationalFormatter formatter = new InternationalFormatter(format);
                formatter.setAllowsInvalid(false);
                formatter.setMinimum(minValue);
                formatter.setMaximum(maxValue);
                
                return formatter;
            }
        };
	}
	
	/**
	 * Sets the table horizontal alignment.
	 * 
	 * @param table the target table.
	 * @param alignment the target alignment.
	 * 
	 * @since 0.1
	 */
	public static void setHorizontalAlignment(JTable table, int alignment) {
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
		renderer.setHorizontalAlignment(alignment);
	}
	
	/**
	 * Creates a {@link MaskFormatter} for time in HH:mm:ss.
	 * 
	 * @return the created {@link MaskFormatter}.
	 * 
	 * @since 0.2.
	 */
	public static MaskFormatter createTimeMaskFormatter() {
		String mask = "##:##:##";
		
		try {
			MaskFormatter maskFormatter = new MaskFormatter(mask);
			maskFormatter.setPlaceholderCharacter('_');

			return maskFormatter;
		} catch (Exception ex) {
			return null;
		}
	}
}
