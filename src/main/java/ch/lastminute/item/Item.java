package ch.lastminute.item;

import java.math.BigDecimal;

public class Item {

	private final String description;
	private final BigDecimal shelfPrice;

	public Item(final String description, final BigDecimal shelfPrice) {
		if (shelfPrice.compareTo(BigDecimal.ZERO) == 0 || shelfPrice.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException();
		}
		this.description = description;
		this.shelfPrice = shelfPrice;
	}

	public BigDecimal getShelfPrice() {
		return shelfPrice;
	}

	public String getDescription() {
		return description;
	}

}
