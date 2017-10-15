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
	private final boolean imported;

	public Item(final String description, final BigDecimal shelfPrice, final ItemType itemType, final boolean imported) {
		if (shelfPrice == null || shelfPrice.compareTo(BigDecimal.ZERO) == 0 || shelfPrice.compareTo(BigDecimal.ZERO) < 0) {
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
		this.imported = imported;
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

	public boolean isImported() {
		return imported;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + description.hashCode();
		result = prime * result + (imported ? 1231 : 1237);
		result = prime * result + itemType.hashCode();
		result = prime * result + shelfPrice.hashCode();
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Item other = (Item) obj;
		if (!description.equals(other.description)) {
			return false;
		}
		if (imported != other.imported) {
			return false;
		}
		if (itemType != other.itemType) {
			return false;
		}

		else if (!shelfPrice.setScale(2).equals(other.shelfPrice.setScale(2))) {
			return false;
		}
		return true;
	}

}
