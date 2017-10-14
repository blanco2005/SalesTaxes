package ch.lastminute.salestaxes;

import java.math.BigDecimal;

import ch.lastminute.item.Item;

public interface TaxCalculator {

	public BigDecimal calculateTaxes(final Item item);

}
