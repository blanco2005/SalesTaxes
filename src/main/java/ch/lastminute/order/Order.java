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

	public void processOrder() {
		for (final Item item : shoppingBasket) {
			if (item2TaxMap.containsKey(item)) {
				continue;
			}
			item2TaxMap.put(item, taxCalculator.calculateTaxes(item));
		}
	}

	public BigDecimal getTotalCost() {
		BigDecimal total = BigDecimal.valueOf(0);
		for (final Item item : shoppingBasket) {
			total = total.add(item.getShelfPrice().add(item2TaxMap.get(item)));
		}
		return total.setScale(2);
	}

	public BigDecimal getTotalTaxes() {
		BigDecimal totalTaxes = BigDecimal.valueOf(0);
		for (final Item item : shoppingBasket) {
			totalTaxes = totalTaxes.add(item2TaxMap.get(item));
		}
		return totalTaxes.setScale(2);
	}

	@Override
	public String toString() {
		if (shoppingBasket.isEmpty()) {
			throw new IllegalStateException("The order is empty!");
		}
		final StringBuilder sb = new StringBuilder();
		for (final Item item : shoppingBasket) {
			sb.append("1");
			sb.append(" ");
			sb.append(item.getDescription());
			sb.append(":");
			sb.append(" ");
			sb.append(item.getShelfPrice().add(item2TaxMap.get(item)).setScale(2));
			sb.append("\n");
		}
		sb.append("Sales Taxes:");
		sb.append(" ");
		sb.append(getTotalTaxes());
		sb.append("\n");
		sb.append("Total:");
		sb.append(" ");
		sb.append(getTotalCost());
		return sb.toString();
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

	public Map<Item, BigDecimal> getItemToTaxMap() {
		return item2TaxMap;
	}

}
