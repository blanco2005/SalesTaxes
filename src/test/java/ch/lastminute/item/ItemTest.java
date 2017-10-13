package ch.lastminute.item;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class ItemTest {

	@Test
	@Parameters({ "0.0001", "1", "12.49", "14.99", })
	public void getShelfPriceTest(final double shelfPrice) {
		final Item item = new Item(shelfPrice);
		assertEquals(shelfPrice, item.getShelfPrice(), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	@Parameters({ "0", "-0.0001", "-1", "-10" })
	public void invalidShelfPrice(final double shelfPrice) {
		new Item(shelfPrice);
	}

}
