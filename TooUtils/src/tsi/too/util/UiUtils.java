package tsi.too.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.text.InternationalFormatter;
import javax.swing.text.MaskFormatter;

public abstract class UiUtils {
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
		
	public static JFormattedTextField createCurrencyTextField() {
		var ftfUnitaryPrice = new JFormattedTextField(0.0);
		ftfUnitaryPrice.setFormatterFactory(new AbstractFormatterFactory() {
			@Override
			public AbstractFormatter getFormatter(JFormattedTextField tf) {
				NumberFormat format = DecimalFormat.getInstance();
				format.setMinimumFractionDigits(2);
				format.setMaximumFractionDigits(2);
				format.setRoundingMode(RoundingMode.HALF_UP);
				InternationalFormatter formatter = new InternationalFormatter(format);
				formatter.setAllowsInvalid(false);
				formatter.setMinimum(0.0);
				formatter.setMaximum(1000000.00);
				return formatter;
			}
		});
		
		return ftfUnitaryPrice;
	}
	
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
}