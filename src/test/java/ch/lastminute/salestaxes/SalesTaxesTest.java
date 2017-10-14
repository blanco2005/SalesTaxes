package ch.lastminute.salestaxes;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import ch.lastminute.item.Item;
import ch.lastminute.item.ItemType;

public class SalesTaxesTest {

	@Test
	public void bookNotImportedTest() {
		final Item book = new Item("book", BigDecimal.valueOf(12.49), ItemType.BOOK, false);
		final SalesTaxes salesTaxes = new SalesTaxes();
		assertEquals(BigDecimal.valueOf(0), salesTaxes.calculateTaxes(book));
	}

	@Test
	public void musicCdNotImportedTest() {
		final Item cd = new Item("music CD", BigDecimal.valueOf(14.99), ItemType.OTHER, false);
		final SalesTaxes salesTaxes = new SalesTaxes();
		assertEquals(BigDecimal.valueOf(1.5), salesTaxes.calculateTaxes(cd));
	}

}
