package ch.lastminute.item;

import java.math.BigDecimal;

public class Item {

	private final BigDecimal shelfPrice;

	public Item(final BigDecimal shelfPrice) {
		if (shelfPrice.compareTo(BigDecimal.ZERO) == 0 || shelfPrice.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException();
		}
		this.shelfPrice = shelfPrice;
	}

	public BigDecimal getShelfPrice() {
		return shelfPrice;
	}

}
