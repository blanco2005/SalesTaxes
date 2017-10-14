package ch.lastminute.order;

import java.util.LinkedList;
import java.util.List;

import ch.lastminute.item.Item;

public class Order {

	/** List since in general we can have multiple instances of the same item. **/
	List<Item> shoppingBasket;

	public Order() {
		shoppingBasket = new LinkedList<>();
	}

	public void add(final Item item) {
		shoppingBasket.add(item);
	}

	public List<Item> getShoppingBasket() {
		return shoppingBasket;
	}

}
