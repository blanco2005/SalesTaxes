package ch.lastminute.salestaxes;

import java.math.BigDecimal;

import ch.lastminute.item.Item;
import ch.lastminute.item.ItemType;

public class SalesTaxes {

	public BigDecimal calculateTaxes(final Item item) {
		if (item.getType() == ItemType.BOOK || item.getType() == ItemType.FOOD) {
			return BigDecimal.valueOf(0);
		}
		else {
			return BigDecimal.valueOf(1.5);
		}
	}

}
