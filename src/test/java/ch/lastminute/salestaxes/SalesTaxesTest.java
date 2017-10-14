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
		Mockito.verify(roundingPolicy, Mockito.never()).round(Mockito.any());
	}

	@Test
	public void musicCdNotImportedTest() {
		final Item cd = new Item("music CD", BigDecimal.valueOf(14.99), ItemType.OTHER, false);
		Mockito.when(roundingPolicy.round(BigDecimal.valueOf(1.499))).thenReturn(BigDecimal.valueOf(1.5));
		assertEquals(BigDecimal.valueOf(1.5), salesTaxes.calculateTaxes(cd));
		Mockito.verify(roundingPolicy, Mockito.times(1)).round(Mockito.any());
	}

	@Test
	public void chocolateBarNotImportedTest() {
		final Item chocolateBar = new Item("chocolate bar", BigDecimal.valueOf(0.85), ItemType.FOOD, false);
		assertEquals(BigDecimal.valueOf(0), salesTaxes.calculateTaxes(chocolateBar));
		Mockito.verify(roundingPolicy, Mockito.never()).round(Mockito.any());
	}

	@Test
	public void chocolateBarImportedTest() {
		final Item chocolateBar = new Item("chocolate bar", BigDecimal.valueOf(10), ItemType.FOOD, true);
		Mockito.when(roundingPolicy.round(BigDecimal.valueOf(0.5))).thenReturn(BigDecimal.valueOf(0.5));
		assertEquals(BigDecimal.valueOf(0.50), salesTaxes.calculateTaxes(chocolateBar));
		Mockito.verify(roundingPolicy, Mockito.times(1)).round(Mockito.any());
	}

	@Test
	public void perfumeImportedTest() {
		final Item perfume = new Item("perfume", BigDecimal.valueOf(47.50), ItemType.OTHER, true);
		Mockito.when(roundingPolicy.round(BigDecimal.valueOf(4.75))).thenReturn(BigDecimal.valueOf(4.75));
		Mockito.when(roundingPolicy.round(BigDecimal.valueOf(2.375))).thenReturn(BigDecimal.valueOf(2.4));
		assertEquals(BigDecimal.valueOf(7.15), salesTaxes.calculateTaxes(perfume));
		Mockito.verify(roundingPolicy, Mockito.times(2)).round(Mockito.any());
	}

	@Test
	public void anotherPerfumeImportedTest() {
		final Item perfume = new Item("perfume", BigDecimal.valueOf(27.99), ItemType.OTHER, true);
		Mockito.when(roundingPolicy.round(BigDecimal.valueOf(2.799))).thenReturn(BigDecimal.valueOf(2.8));
		Mockito.when(roundingPolicy.round(BigDecimal.valueOf(1.3995))).thenReturn(BigDecimal.valueOf(1.4));
		assertEquals(BigDecimal.valueOf(4.2), salesTaxes.calculateTaxes(perfume));
		Mockito.verify(roundingPolicy, Mockito.times(2)).round(Mockito.any());
	}

	@Test
	public void perfumeNotImportedTest() {
		final Item perfume = new Item("perfume", BigDecimal.valueOf(18.99), ItemType.OTHER, false);
		Mockito.when(roundingPolicy.round(BigDecimal.valueOf(1.899))).thenReturn(BigDecimal.valueOf(1.9));
		assertEquals(BigDecimal.valueOf(1.9), salesTaxes.calculateTaxes(perfume));
		Mockito.verify(roundingPolicy, Mockito.times(1)).round(Mockito.any());
	}

	@Test
	public void pillsNotImportedTest() {
		final Item perfume = new Item("pills", BigDecimal.valueOf(9.75), ItemType.MEDICAL, false);
		assertEquals(BigDecimal.valueOf(0), salesTaxes.calculateTaxes(perfume));
		Mockito.verify(roundingPolicy, Mockito.never()).round(Mockito.any());
	}

	@Test
	public void anotherboxChocolateImportedTest() {
		final Item chocolateBar = new Item("chocolate bar", BigDecimal.valueOf(11.25), ItemType.FOOD, true);
		Mockito.when(roundingPolicy.round(BigDecimal.valueOf(0.5625))).thenReturn(BigDecimal.valueOf(0.55));
		assertEquals(BigDecimal.valueOf(0.60), salesTaxes.calculateTaxes(chocolateBar));
		Mockito.verify(roundingPolicy, Mockito.times(1)).round(Mockito.any());
	}

}
