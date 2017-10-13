package ch.lastminute.item;

import java.math.BigDecimal;

public class Item {

	private BigDecimal shelfPrice;
	private String description;

	public Item() {

	}

	public Item(final String description) {
		this.description = description;
	}

	public Item(final BigDecimal shelfPrice) {
		if (shelfPrice.compareTo(BigDecimal.ZERO) == 0 || shelfPrice.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException();
		}
		this.shelfPrice = shelfPrice;
	}

	public BigDecimal getShelfPrice() {
		return shelfPrice;
	}

	public String getDescription() {
		return description;
	}

}
