package ch.lastminute.item;

import java.math.BigDecimal;

import ch.lastminute.errors.ErrorMsg;

public class Item {

	/** I assume description can be neither null or empty. **/
	private final String description;
	/** I assume shelf price must be greater than zero. **/
	private final BigDecimal shelfPrice;
	/** I use an enum since the different types of items have no differences (same fields, same methods), so a hierarchy it's useless in this
	 * case. **/
	private final ItemType itemType;

	public Item(final String description, final BigDecimal shelfPrice, final ItemType itemType) {
		if (shelfPrice.compareTo(BigDecimal.ZERO) == 0 || shelfPrice.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException(ErrorMsg.INVALID_SHELF_PRICE.toString());
		}
		if (description == null) {
			throw new IllegalArgumentException(ErrorMsg.DESCRIPTION_NULL.toString());
		}
		if (description.isEmpty()) {
			throw new IllegalArgumentException(ErrorMsg.DESCRIPTION_EMPTY.toString());
		}
		if (itemType == null) {
			throw new IllegalArgumentException(ErrorMsg.ITEM_TYPE_NULL.toString());
		}
		this.description = description;
		this.shelfPrice = shelfPrice;
		this.itemType = itemType;
	}

	public BigDecimal getShelfPrice() {
		return shelfPrice;
	}

	public String getDescription() {
		return description;
	}

	public ItemType getType() {
		return itemType;
	}

	public boolean getImported() {
		return true;
	}

}
