package ch.lastminute.item;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class ItemTest {

	@Test
	@Parameters({ "0.0001", "1", "12.49", "14.99", })
	public void getShelfPriceTest(final double shelfPrice) {
		final Item item = new Item(new BigDecimal(shelfPrice));
		assertEquals(new BigDecimal(shelfPrice), item.getShelfPrice());
	}

	@Test(expected = IllegalArgumentException.class)
	@Parameters({ "0", "-0.0001", "-1", "-10" })
	public void invalidShelfPrice(final double shelfPrice) {
		new Item(new BigDecimal(shelfPrice));
	}

	@Test
	public void getDescriptionTest() {
		final Item item = new Item("description");
		assertEquals("description", item.getDescription());
	}

	@Test
	public void anotherGetDescriptionTest() {
		final Item item = new Item("description description");
		assertEquals("description description", item.getDescription());
	}

}
