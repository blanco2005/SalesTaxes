package ch.lastminute.salestaxes;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import ch.lastminute.item.Item;
import ch.lastminute.item.ItemType;
import ch.lastminute.rounding.RoundingPolicy;

/** To derive test cases for the SalesTaxes class I initially considered the examples given in the specification. However, the specification
 * does not cover all the possible cases. In particular, it does not provide example for the <Book, imported> and <Medical, not imported>
 * cases. I derived two test cases for such cases to cover all the possible cases. */

public class StandardTaxCalculatorTest {

	StandardTaxCalculator salesTaxes;
	RoundingPolicy roundingPolicy;

	@Before
	public void setUp() {
		roundingPolicy = mock(RoundingPolicy.class);
		salesTaxes = new StandardTaxCalculator(roundingPolicy);
	}

	/** Book **/
	@Test
	public void bookNotImportedTest() {
		final Item book = new Item("book", BigDecimal.valueOf(12.49), ItemType.BOOK, false);
		assertEquals(BigDecimal.valueOf(0), salesTaxes.calculateTaxes(book));
		verify(roundingPolicy, never()).round(any());
	}

	@Test
	public void bookImportedTest() {
		final Item book = new Item("book", BigDecimal.valueOf(12.49), ItemType.BOOK, true);
		when(roundingPolicy.round(BigDecimal.valueOf(0.6245))).thenReturn(BigDecimal.valueOf(0.65));
		assertEquals(BigDecimal.valueOf(0.65), salesTaxes.calculateTaxes(book));
		verify(roundingPolicy, times(1)).round(any());
	}

	/** Food **/

	@Test
	public void chocolateBarNotImportedTest() {
		final Item chocolateBar = new Item("chocolate bar", BigDecimal.valueOf(0.85), ItemType.FOOD, false);
		assertEquals(BigDecimal.valueOf(0), salesTaxes.calculateTaxes(chocolateBar));
		verify(roundingPolicy, never()).round(any());
	}

	@Test
	public void chocolateBarImportedTest() {
		final Item chocolateBar = new Item("chocolate bar", BigDecimal.valueOf(10), ItemType.FOOD, true);
		when(roundingPolicy.round(BigDecimal.valueOf(0.5))).thenReturn(BigDecimal.valueOf(0.5));
		assertEquals(BigDecimal.valueOf(0.50), salesTaxes.calculateTaxes(chocolateBar));
		verify(roundingPolicy, times(1)).round(any());
	}

	@Test
	public void anotherboxChocolateImportedTest() {
		final Item chocolateBar = new Item("chocolate bar", BigDecimal.valueOf(11.25), ItemType.FOOD, true);
		when(roundingPolicy.round(BigDecimal.valueOf(0.5625))).thenReturn(BigDecimal.valueOf(0.60));
		assertEquals(BigDecimal.valueOf(0.60), salesTaxes.calculateTaxes(chocolateBar));
		verify(roundingPolicy, times(1)).round(any());
	}

	/** Medical **/

	@Test
	public void pillsNotImportedTest() {
		final Item pills = new Item("pills", BigDecimal.valueOf(9.75), ItemType.MEDICAL, false);
		assertEquals(BigDecimal.valueOf(0), salesTaxes.calculateTaxes(pills));
		verify(roundingPolicy, never()).round(any());
	}

	@Test
	public void pillsImportedTest() {
		final Item pills = new Item("pills", BigDecimal.valueOf(9.75), ItemType.MEDICAL, true);
		when(roundingPolicy.round(BigDecimal.valueOf(0.4875))).thenReturn(BigDecimal.valueOf(0.5));
		assertEquals(BigDecimal.valueOf(0.5), salesTaxes.calculateTaxes(pills));
		verify(roundingPolicy, times(1)).round(any());
	}

	/** Other **/

	@Test
	public void musicCdNotImportedTest() {
		final Item cd = new Item("music CD", BigDecimal.valueOf(14.99), ItemType.OTHER, false);
		when(roundingPolicy.round(BigDecimal.valueOf(1.499))).thenReturn(BigDecimal.valueOf(1.5));
		assertEquals(BigDecimal.valueOf(1.5), salesTaxes.calculateTaxes(cd));
		verify(roundingPolicy, times(1)).round(any());
	}

	@Test
	public void perfumeNotImportedTest() {
		final Item perfume = new Item("perfume", BigDecimal.valueOf(18.99), ItemType.OTHER, false);
		when(roundingPolicy.round(BigDecimal.valueOf(1.899))).thenReturn(BigDecimal.valueOf(1.9));
		assertEquals(BigDecimal.valueOf(1.9), salesTaxes.calculateTaxes(perfume));
		verify(roundingPolicy, times(1)).round(any());
	}

	@Test
	public void perfumeImportedTest() {
		final Item perfume = new Item("perfume", BigDecimal.valueOf(47.50), ItemType.OTHER, true);
		when(roundingPolicy.round(BigDecimal.valueOf(4.75))).thenReturn(BigDecimal.valueOf(4.75));
		when(roundingPolicy.round(BigDecimal.valueOf(2.375))).thenReturn(BigDecimal.valueOf(2.4));
		assertEquals(BigDecimal.valueOf(7.15), salesTaxes.calculateTaxes(perfume));
		verify(roundingPolicy, times(2)).round(any());
	}

	@Test
	public void anotherPerfumeImportedTest() {
		final Item perfume = new Item("perfume", BigDecimal.valueOf(27.99), ItemType.OTHER, true);
		when(roundingPolicy.round(BigDecimal.valueOf(2.799))).thenReturn(BigDecimal.valueOf(2.8));
		when(roundingPolicy.round(BigDecimal.valueOf(1.3995))).thenReturn(BigDecimal.valueOf(1.4));
		assertEquals(BigDecimal.valueOf(4.2), salesTaxes.calculateTaxes(perfume));
		verify(roundingPolicy, times(2)).round(any());
	}

}
