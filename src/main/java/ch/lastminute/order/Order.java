package ch.lastminute.order;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

	public void clear() {
		shoppingBasket.clear();
	}

	public void removeItem(final Item item) {
		shoppingBasket.remove(item);
	}

	public void processOrder() {

	}

	public Map<Item, BigDecimal> getItemToTaxMap() {
		return new HashMap<>();
	}

}
