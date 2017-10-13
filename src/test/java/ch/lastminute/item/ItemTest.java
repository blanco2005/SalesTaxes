package ch.lastminute.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class ItemTest {

	private final String VALID_DESCRIPTOR = "description";
	private final BigDecimal VALID_SHELF_PRICE = new BigDecimal(10);

	@Test
	@Parameters({ "0.0001", "1", "12.49", "14.99", })
	public void getShelfPriceTest(final double shelfPrice) {
		final Item item = new Item(VALID_DESCRIPTOR, new BigDecimal(shelfPrice));
		assertEquals(new BigDecimal(shelfPrice), item.getShelfPrice());
	}

	@Test(expected = IllegalArgumentException.class)
	@Parameters({ "0", "-0.0001", "-1", "-10" })
	public void invalidShelfPrice(final double shelfPrice) {
		new Item(VALID_DESCRIPTOR, new BigDecimal(shelfPrice));
	}

	@Test
	@Parameters({ "music CD", "chocolate bar" })
	public void getDescriptionTest(final String description) {
		final Item item = new Item(description, VALID_SHELF_PRICE);
		assertEquals(description, item.getDescription());
	}

	@Test
	public void emptyDescriptionTest() {
		try {
			new Item("", VALID_SHELF_PRICE);
			fail();
		} catch (final IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Description cannot be empty");
		}
	}

	@Test
	public void nullDescriptionTest() {
		try {
			new Item(null, VALID_SHELF_PRICE);
			fail();
		} catch (final IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Description cannot be null");
		}
	}

}
