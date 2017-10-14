package ch.lastminute.integration.taxes.rounding;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import ch.lastminute.item.Item;
import ch.lastminute.item.ItemType;
import ch.lastminute.rounding.RoundingPolicy;
import ch.lastminute.rounding.StandardRoundingPolicy;
import ch.lastminute.salestaxes.StandardTaxCalculator;

public class IntegrationTaxesRoundingTest {

	StandardTaxCalculator salesTaxes;
	RoundingPolicy roundingPolicy;

	@Before
	public void setUp() {
		roundingPolicy = new StandardRoundingPolicy();
		salesTaxes = new StandardTaxCalculator(roundingPolicy);
	}

	/** Book **/
	@Test
	public void bookNotImportedTest() {
		final Item book = new Item("book", BigDecimal.valueOf(12.49), ItemType.BOOK, false);
		assertEquals(BigDecimal.valueOf(0), salesTaxes.calculateTaxes(book));
	}

	@Test
	public void bookImportedTest() {
		final Item book = new Item("book", BigDecimal.valueOf(12.49), ItemType.BOOK, true);
		assertEquals(BigDecimal.valueOf(0.65), salesTaxes.calculateTaxes(book));
	}

	/** Food **/

	@Test
	public void chocolateBarNotImportedTest() {
		final Item chocolateBar = new Item("chocolate bar", BigDecimal.valueOf(0.85), ItemType.FOOD, false);
		assertEquals(BigDecimal.valueOf(0), salesTaxes.calculateTaxes(chocolateBar));
	}

	@Test
	public void chocolateBarImportedTest() {
		final Item chocolateBar = new Item("chocolate bar", BigDecimal.valueOf(10), ItemType.FOOD, true);
		assertEquals(BigDecimal.valueOf(0.50).setScale(2), salesTaxes.calculateTaxes(chocolateBar));
	}

	@Test
	public void anotherboxChocolateImportedTest() {
		final Item chocolateBar = new Item("chocolate bar", BigDecimal.valueOf(11.25), ItemType.FOOD, true);
		assertEquals(BigDecimal.valueOf(0.60).setScale(2), salesTaxes.calculateTaxes(chocolateBar));
	}

	/** Medical **/

	@Test
	public void pillsNotImportedTest() {
		final Item pills = new Item("pills", BigDecimal.valueOf(9.75), ItemType.MEDICAL, false);
		assertEquals(BigDecimal.valueOf(0), salesTaxes.calculateTaxes(pills));
	}

	@Test
	public void pillsImportedTest() {
		final Item pills = new Item("pills", BigDecimal.valueOf(9.75), ItemType.MEDICAL, true);
		assertEquals(BigDecimal.valueOf(0.5).setScale(2), salesTaxes.calculateTaxes(pills));
	}

	/** Other **/

	@Test
	public void musicCdNotImportedTest() {
		final Item cd = new Item("music CD", BigDecimal.valueOf(14.99), ItemType.OTHER, false);
		assertEquals(BigDecimal.valueOf(1.5).setScale(2), salesTaxes.calculateTaxes(cd));
	}

	@Test
	public void perfumeNotImportedTest() {
		final Item perfume = new Item("perfume", BigDecimal.valueOf(18.99), ItemType.OTHER, false);
		assertEquals(BigDecimal.valueOf(1.9).setScale(2), salesTaxes.calculateTaxes(perfume));
	}

	@Test
	public void perfumeImportedTest() {
		final Item perfume = new Item("perfume", BigDecimal.valueOf(47.50), ItemType.OTHER, true);
		assertEquals(BigDecimal.valueOf(7.15), salesTaxes.calculateTaxes(perfume));
	}

	@Test
	public void anotherPerfumeImportedTest() {
		final Item perfume = new Item("perfume", BigDecimal.valueOf(27.99), ItemType.OTHER, true);
		assertEquals(BigDecimal.valueOf(4.2).setScale(2), salesTaxes.calculateTaxes(perfume));
	}

}
