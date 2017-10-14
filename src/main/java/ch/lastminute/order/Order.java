package ch.lastminute.order;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ch.lastminute.item.Item;
import ch.lastminute.salestaxes.TaxCalculator;

public class Order {

	/** List since in general we can have multiple instances of the same item. **/
	private final List<Item> shoppingBasket;
	private final Map<Item, BigDecimal> item2TaxMap;
	private final TaxCalculator taxCalculator;

	public Order(final TaxCalculator taxCalculator) {
		shoppingBasket = new LinkedList<>();
		item2TaxMap = new HashMap<>();
		this.taxCalculator = taxCalculator;
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
		for (final Item item : shoppingBasket) {
			item2TaxMap.put(item, taxCalculator.calculateTaxes(item));
		}
	}

	public Map<Item, BigDecimal> getItemToTaxMap() {
		return item2TaxMap;
	}

}
