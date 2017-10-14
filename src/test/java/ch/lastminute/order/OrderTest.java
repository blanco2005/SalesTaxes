package ch.lastminute.order;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import ch.lastminute.item.Item;
import ch.lastminute.item.ItemType;

public class OrderTest {

	Order order;

	@Before
	public void setUp() {
		order = new Order();
	}

	@Test
	public void emptyOrderTest() {
		assertEquals(order.getShoppingBasket().size(), 0);
	}

	@Test
	public void nonEmptyOrderTest() {
		order.add(new Item("description", BigDecimal.valueOf(10), ItemType.BOOK, true));
		assertEquals(order.getShoppingBasket().size(), 1);
	}

}
