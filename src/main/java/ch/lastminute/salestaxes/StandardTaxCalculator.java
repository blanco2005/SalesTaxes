package ch.lastminute.salestaxes;

import java.math.BigDecimal;

import ch.lastminute.item.Item;
import ch.lastminute.item.ItemType;
import ch.lastminute.rounding.RoundingPolicy;

public class StandardTaxCalculator implements TaxCalculator {

	private final BigDecimal IMPORT_TAX_RATE = BigDecimal.valueOf(5);
	private final BigDecimal BASIC_TAX_RATE = BigDecimal.valueOf(10);
	private final RoundingPolicy roundingPolicy;

	public StandardTaxCalculator(final RoundingPolicy roundingPolicy) {
		this.roundingPolicy = roundingPolicy;
	}

	@Override
	public BigDecimal calculateTaxes(final Item item) {
		BigDecimal taxes = BigDecimal.valueOf(0);
		if (item.isImported()) {
			final BigDecimal rawTax = computeRawTax(item, IMPORT_TAX_RATE);
			taxes = taxes.add(roundingPolicy.round(rawTax));
		}
		if (isExemedFromBasicTax(item)) {
			return taxes;
		}
		final BigDecimal rawTax = computeRawTax(item, BASIC_TAX_RATE);
		;
		taxes = taxes.add(roundingPolicy.round(rawTax));
		return taxes;
	}

	private boolean isExemedFromBasicTax(final Item item) {
		return item.getType() == ItemType.BOOK || item.getType() == ItemType.FOOD || item.getType() == ItemType.MEDICAL;
	}

	private BigDecimal computeRawTax(final Item item, final BigDecimal rate) {
		return item.getShelfPrice().multiply(rate).divide(BigDecimal.valueOf(100));
	}

}
