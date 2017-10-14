package ch.lastminute.salestaxes;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ch.lastminute.item.Item;
import ch.lastminute.item.ItemType;
import ch.lastminute.rounding.RoundingPolicy;

public class SalesTaxesTest {

	SalesTaxes salesTaxes;
	RoundingPolicy roundingPolicy;

	@Before
	public void setUp() {
		roundingPolicy = Mockito.mock(RoundingPolicy.class);
		salesTaxes = new SalesTaxes(roundingPolicy);
	}

	@Test
	public void bookNotImportedTest() {
		final Item book = new Item("book", BigDecimal.valueOf(12.49), ItemType.BOOK, false);
		assertEquals(BigDecimal.valueOf(0), salesTaxes.calculateTaxes(book));
	}

	@Test
	public void musicCdNotImportedTest() {
		final Item cd = new Item("music CD", BigDecimal.valueOf(14.99), ItemType.OTHER, false);
		assertEquals(BigDecimal.valueOf(1.5), salesTaxes.calculateTaxes(cd));
	}

	@Test
	public void chocolateBarNotImportedTest() {
		final Item chocolateBar = new Item("chocolate bar", BigDecimal.valueOf(0.85), ItemType.FOOD, false);
		assertEquals(BigDecimal.valueOf(0), salesTaxes.calculateTaxes(chocolateBar));
	}

	@Test
	public void chocolateBarImportedTest() {
		final Item chocolateBar = new Item("chocolate bar", BigDecimal.valueOf(10), ItemType.FOOD, true);
		Mockito.when(roundingPolicy.round(BigDecimal.valueOf(0.5))).thenReturn(BigDecimal.valueOf(0.5));
		assertEquals(BigDecimal.valueOf(0.50), salesTaxes.calculateTaxes(chocolateBar));
		Mockito.verify(roundingPolicy, Mockito.times(1)).round(BigDecimal.valueOf(0.5));
	}

}
