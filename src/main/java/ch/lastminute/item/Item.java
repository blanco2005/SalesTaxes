package ch.lastminute.item;

public class Item {

	private final double shelfPrice;

	public Item(final double shelfPrice) {
		if (shelfPrice <= 0) {
			throw new IllegalArgumentException();
		}
		this.shelfPrice = shelfPrice;
	}

	public double getShelfPrice() {
		return shelfPrice;
	}

}
