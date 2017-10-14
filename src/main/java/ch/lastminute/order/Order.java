package ch.lastminute.order;

import java.util.HashSet;
import java.util.Set;

import ch.lastminute.item.Item;

public class Order {

	Set<Item> shoppingBasket;

	public Order() {
		shoppingBasket = new HashSet<>();
	}

	public void add(final Item item) {
		shoppingBasket.add(item);
	}

	public Set<Item> getShoppingBasket() {
		return shoppingBasket;
	}

}
