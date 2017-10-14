package ch.lastminute.order;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import ch.lastminute.item.Item;
import ch.lastminute.item.ItemType;

public class OrderTest {

	Order order;
	Item item1;

	@Before
	public void setUp() {
		order = new Order();
		item1 = new Item("description", BigDecimal.valueOf(10), ItemType.BOOK, true);
	}

	@Test
	public void emptyOrderTest() {
		assertEquals(order.getShoppingBasket().size(), 0);
	}

	@Test
	public void nonEmptyOrderTest() {
		order.add(item1);
		assertEquals(order.getShoppingBasket().size(), 1);
	}

	@Test
	public void orderContainsInsertedObjectsTest() {
		order.add(item1);
		assertTrue(order.getShoppingBasket().contains(item1));
	}

	@Test
	public void itemInsertedTwiceAppearsTwiceTest() {
		order.add(item1);
		order.add(item1);
		assertEquals(order.getShoppingBasket().size(), 2);
	}

	@Test
	public void orderIsEmptyAfterClearTest() {
		order.add(item1);
		order.add(item1);
		order.clear();
		assertEquals(order.getShoppingBasket().size(), 0);
	}

	@Test
	public void removeElementTest() {
		order.add(item1);
		order.removeItem(item1);
		assertEquals(order.getShoppingBasket().size(), 0);
	}

	@Test
	public void processEmptyCarTest() {
		order.processOrder();
		assertEquals(order.getItemToTaxMap().size(), 0);
	}

}
