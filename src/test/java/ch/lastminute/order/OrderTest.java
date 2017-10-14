package ch.lastminute.order;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import ch.lastminute.item.Item;
import ch.lastminute.item.ItemType;

public class OrderTest {

	@Test
	public void emptyOrderTest() {
		final Order order = new Order();
		assertEquals(order.getShoppingBasket().size(), 0);
	}

	@Test
	public void nonEmptyOrderTest() {
		final Order order = new Order();
		order.add(new Item("description", BigDecimal.valueOf(10), ItemType.BOOK, true));
		assertEquals(order.getShoppingBasket().size(), 1);
	}

}
