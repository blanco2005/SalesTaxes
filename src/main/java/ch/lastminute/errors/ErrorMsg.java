package ch.lastminute.errors;

public enum ErrorMsg {

	INVALID_SHELF_PRICE("Shelf price must be greater than zero"), DESCRIPTION_NULL("Description cannot be null"), DESCRIPTION_EMPTY(
			"Description cannot be empty"), ITEM_TYPE_NULL("Item cannot be null");

	private final String message;

	ErrorMsg(final String message) {
		this.message = message;
	}

	@Override
	public final String toString() {
		return message;
	}
}
