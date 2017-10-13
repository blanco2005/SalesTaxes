package ch.lastminute.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;

import ch.lastminute.errors.ErrorMsg;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class ItemTest {

	private final String VALID_DESCRIPTOR = "description";
	private final BigDecimal VALID_SHELF_PRICE = new BigDecimal(10);

	@Test
	@Parameters({ "0.0001", "1", "12.49", "14.99", })
	public void getShelfPriceTest(final double shelfPrice) {
		final Item item = new Item(VALID_DESCRIPTOR, new BigDecimal(shelfPrice), ItemType.BOOK);
		assertEquals(new BigDecimal(shelfPrice), item.getShelfPrice());
	}

	@Test
	@Parameters({ "0", "-0.0001", "-1", "-10" })
	public void invalidShelfPrice(final double shelfPrice) {
		try {
			new Item(VALID_DESCRIPTOR, new BigDecimal(shelfPrice), ItemType.BOOK);
			fail();
		} catch (final IllegalArgumentException e) {
			assertEquals(e.getMessage(), ErrorMsg.INVALID_SHELF_PRICE.toString());
		}
	}

	@Test
	@Parameters({ "music CD", "chocolate bar" })
	public void getDescriptionTest(final String description) {
		final Item item = new Item(description, VALID_SHELF_PRICE, ItemType.BOOK);
		assertEquals(description, item.getDescription());
	}

	@Test
	public void emptyDescriptionTest() {
		try {
			new Item("", VALID_SHELF_PRICE, ItemType.BOOK);
			fail();
		} catch (final IllegalArgumentException e) {
			assertEquals(e.getMessage(), ErrorMsg.DESCRIPTION_EMPTY.toString());
		}
	}

	@Test
	public void nullDescriptionTest() {
		try {
			new Item(null, VALID_SHELF_PRICE, ItemType.BOOK);
			fail();
		} catch (final IllegalArgumentException e) {
			assertEquals(e.getMessage(), ErrorMsg.DESCRIPTION_NULL.toString());
		}
	}

	@Test
	public void getTypeTest() {
		final Item item = new Item(VALID_DESCRIPTOR, VALID_SHELF_PRICE, ItemType.BOOK);
		assertEquals(ItemType.BOOK, item.getType());
	}

	@Test
	public void invalidTypeTest() {
		try {
			new Item(VALID_DESCRIPTOR, VALID_SHELF_PRICE, null);
			fail();
		} catch (final IllegalArgumentException e) {
			assertEquals(e.getMessage(), ErrorMsg.ITEM_TYPE_NULL.toString());
		}
	}

	@Test
	public void getImportedTest() {
		final Item item = new Item(VALID_DESCRIPTOR, VALID_SHELF_PRICE, ItemType.BOOK);
		assertEquals(item.getImported(), true);
	}

}
