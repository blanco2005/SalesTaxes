package ch.lastminute.item;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ItemTest {

	@Test
	public void getShelfPriceTest() {
		final Item item = new Item(12.49);
		assertEquals(12.49, item.getShelfPrice(), 0);
	}

	@Test
	public void anotherGetShelfPriceTest() {
		final Item item = new Item(14.99);
		assertEquals(14.99, item.getShelfPrice(), 0);
	}

}
