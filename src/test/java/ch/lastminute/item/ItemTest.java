package ch.lastminute.item;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class ItemTest {

	@Test
	@Parameters({ "12.49", "14.99", "1" })
	public void getShelfPriceTest(final double shelfPrice) {
		final Item item = new Item(shelfPrice);
		assertEquals(shelfPrice, item.getShelfPrice(), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void negativePriceTest() {
		new Item(-10);
	}

	@Test(expected = IllegalArgumentException.class)
	public void priceEqualsToZeroTest() {
		new Item(0);
	}

}
