package ch.lastminute.salestaxes;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ch.lastminute.item.Item;
import ch.lastminute.item.ItemType;
import ch.lastminute.rounding.RoundingPolicy;

/** To derive test cases for the SalesTaxes class I initially considered the examples given in the specification. However, the specification
 * does not cover all the possible cases. In particular, it does not provide example for the <Book, imported> and <Medical, not imported>
 * cases. I derived two test cases for such cases to cover all the possible cases. */

public class SalesTaxesTest {

	SalesTaxes salesTaxes;
	RoundingPolicy roundingPolicy;

	@Before
	public void setUp() {
		roundingPolicy = Mockito.mock(RoundingPolicy.class);
		salesTaxes = new SalesTaxes(roundingPolicy);
	}

	/** Book **/
	@Test
	public void bookNotImportedTest() {
		final Item book = new Item("book", BigDecimal.valueOf(12.49), ItemType.BOOK, false);
		assertEquals(BigDecimal.valueOf(0), salesTaxes.calculateTaxes(book));
		Mockito.verify(roundingPolicy, Mockito.never()).round(Mockito.any());
	}

	@Test
	public void bookImportedTest() {
		final Item book = new Item("book", BigDecimal.valueOf(12.49), ItemType.BOOK, true);
		Mockito.when(roundingPolicy.round(BigDecimal.valueOf(0.6245))).thenReturn(BigDecimal.valueOf(0.65));
		assertEquals(BigDecimal.valueOf(0.65), salesTaxes.calculateTaxes(book));
		Mockito.verify(roundingPolicy, Mockito.times(1)).round(Mockito.any());
	}

	/** Food **/

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
	public void anotherboxChocolateImportedTest() {
		final Item chocolateBar = new Item("chocolate bar", BigDecimal.valueOf(11.25), ItemType.FOOD, true);
		Mockito.when(roundingPolicy.round(BigDecimal.valueOf(0.5625))).thenReturn(BigDecimal.valueOf(0.60));
		assertEquals(BigDecimal.valueOf(0.60), salesTaxes.calculateTaxes(chocolateBar));
		Mockito.verify(roundingPolicy, Mockito.times(1)).round(Mockito.any());
	}

	/** Medical **/

	@Test
	public void pillsNotImportedTest() {
		final Item pills = new Item("pills", BigDecimal.valueOf(9.75), ItemType.MEDICAL, false);
		assertEquals(BigDecimal.valueOf(0), salesTaxes.calculateTaxes(pills));
		Mockito.verify(roundingPolicy, Mockito.never()).round(Mockito.any());
	}

	@Test
	public void pillsImportedTest() {
		final Item pills = new Item("pills", BigDecimal.valueOf(9.75), ItemType.MEDICAL, true);
		Mockito.when(roundingPolicy.round(BigDecimal.valueOf(0.4875))).thenReturn(BigDecimal.valueOf(0.5));
		assertEquals(BigDecimal.valueOf(0.5), salesTaxes.calculateTaxes(pills));
		Mockito.verify(roundingPolicy, Mockito.times(1)).round(Mockito.any());
	}

	/** Other **/

	@Test
	public void musicCdNotImportedTest() {
		final Item cd = new Item("music CD", BigDecimal.valueOf(14.99), ItemType.OTHER, false);
		Mockito.when(roundingPolicy.round(BigDecimal.valueOf(1.499))).thenReturn(BigDecimal.valueOf(1.5));
		assertEquals(BigDecimal.valueOf(1.5), salesTaxes.calculateTaxes(cd));
		Mockito.verify(roundingPolicy, Mockito.times(1)).round(Mockito.any());
	}

	@Test
	public void perfumeNotImportedTest() {
		final Item perfume = new Item("perfume", BigDecimal.valueOf(18.99), ItemType.OTHER, false);
		Mockito.when(roundingPolicy.round(BigDecimal.valueOf(1.899))).thenReturn(BigDecimal.valueOf(1.9));
		assertEquals(BigDecimal.valueOf(1.9), salesTaxes.calculateTaxes(perfume));
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

}
