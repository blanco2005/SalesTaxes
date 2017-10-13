package ch.lastminute.item;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ItemTest {

	@Test
	public void getShelfPriceTest() {
		final Item item = new Item();
		assertEquals(12.49, item.getShelfPrice(), 0);
	}

}
