package ch.lastminute.salestaxes;

import java.math.BigDecimal;

import ch.lastminute.item.Item;
import ch.lastminute.item.ItemType;
import ch.lastminute.rounding.RoundingPolicy;

public class SalesTaxes {

	private final BigDecimal IMPORT_TAX_RATE = BigDecimal.valueOf(5);
	private final BigDecimal BASIC_TAX_RATE = BigDecimal.valueOf(10);
	private final RoundingPolicy roundingPolicy;

	public SalesTaxes(final RoundingPolicy roundingPolicy) {
		this.roundingPolicy = roundingPolicy;
	}

	public BigDecimal calculateTaxes(final Item item) {
		BigDecimal taxes = BigDecimal.valueOf(0);
		if (item.getImported()) {
			final BigDecimal rawTax = item.getShelfPrice().multiply(IMPORT_TAX_RATE).divide(BigDecimal.valueOf(100));
			taxes = taxes.add(roundingPolicy.round(rawTax));
		}
		if (isExemed(item)) {
			return taxes;
		}
		final BigDecimal rawTax = item.getShelfPrice().multiply(BASIC_TAX_RATE).divide(BigDecimal.valueOf(100));
		taxes = taxes.add(roundingPolicy.round(rawTax));
		return taxes;
	}

	private boolean isExemed(final Item item) {
		return item.getType() == ItemType.BOOK || item.getType() == ItemType.FOOD;
	}

}
