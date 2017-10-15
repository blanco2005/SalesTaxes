package ch.lastminute.integration.order.taxes;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ch.lastminute.item.Item;
import ch.lastminute.item.ItemType;
import ch.lastminute.order.Order;
import ch.lastminute.rounding.RoundingPolicy;
import ch.lastminute.salestaxes.StandardTaxCalculator;
import ch.lastminute.salestaxes.TaxCalculator;

public class IntegrationOrderTaxesTest {

	Order order;
	Item itemExample = new Item("description", BigDecimal.valueOf(10), ItemType.BOOK, true);
	TaxCalculator taxCalculator;
	RoundingPolicy roundingPolicy;

	/** Items for input 1 **/
	final Item bookForInput1 = new Item("book", BigDecimal.valueOf(12.49), ItemType.BOOK, false);
	final Item cdForInput1 = new Item("music CD", BigDecimal.valueOf(14.99), ItemType.OTHER, false);
	final Item chocolateForInput1 = new Item("chocolate bar", BigDecimal.valueOf(0.85), ItemType.FOOD, false);

	/** Items for input 2 **/
	final Item chocolateForInput2 = new Item("imported box of chocolates", BigDecimal.valueOf(10.00), ItemType.FOOD, true);
	final Item perfumForInput2 = new Item("imported bottle of perfume", BigDecimal.valueOf(47.50), ItemType.OTHER, true);

	/** Items for input 3 **/
	final Item perfumForInput3 = new Item("imported bottle of perfume", BigDecimal.valueOf(27.99), ItemType.OTHER, true);
	final Item notImportedPerfumForInput3 = new Item("bottle of perfume", BigDecimal.valueOf(18.99), ItemType.OTHER, false);
	final Item pillsForInput3 = new Item("packet of headache pills", BigDecimal.valueOf(9.75), ItemType.MEDICAL, false);
	final Item chocolateForInput3 = new Item("imported box of chocolates", BigDecimal.valueOf(11.25), ItemType.FOOD, true);

	@Before
	public void setUp() {
		roundingPolicy = Mockito.mock(RoundingPolicy.class);
		taxCalculator = new StandardTaxCalculator(roundingPolicy);
		order = new Order(taxCalculator);
	}

	@Test
	public void processNonEmptyCartLeadToCorrectTaxMapTest() {
		order.add(itemExample);
		Mockito.when(roundingPolicy.round(BigDecimal.valueOf(0.5))).thenReturn(BigDecimal.valueOf(0.5));
		order.processOrder();
		assertEquals(order.getItemToTaxMap().get(itemExample), BigDecimal.valueOf(0.5));
		Mockito.verify(roundingPolicy, Mockito.times(1)).round(Mockito.any());
	}

	@Test
	public void interactionForInput1Test() {
		order.add(bookForInput1);
		order.add(cdForInput1);
		order.add(chocolateForInput1);
		Mockito.when(roundingPolicy.round(BigDecimal.valueOf(1.499))).thenReturn(BigDecimal.valueOf(1.5));
		order.processOrder();
		assertEquals(BigDecimal.valueOf(29.83), order.getTotalCost());
		assertEquals(BigDecimal.valueOf(1.50).setScale(2), order.getTotalTaxes());
		Mockito.verify(roundingPolicy, Mockito.times(1)).round(Mockito.any());
	}

	@Test
	public void interactionForInput2Test() {
		order.add(chocolateForInput2);
		order.add(perfumForInput2);
		Mockito.when(roundingPolicy.round(BigDecimal.valueOf(0.5))).thenReturn(BigDecimal.valueOf(0.5));
		Mockito.when(roundingPolicy.round(BigDecimal.valueOf(2.375))).thenReturn(BigDecimal.valueOf(2.4));
		Mockito.when(roundingPolicy.round(BigDecimal.valueOf(4.75))).thenReturn(BigDecimal.valueOf(4.75));
		order.processOrder();
		assertEquals(BigDecimal.valueOf(65.15), order.getTotalCost());
		assertEquals(BigDecimal.valueOf(7.65), order.getTotalTaxes());
		Mockito.verify(roundingPolicy, Mockito.times(3)).round(Mockito.any());
	}

	@Test
	public void interactionForInput3Test() {
		order.add(perfumForInput3);
		order.add(notImportedPerfumForInput3);
		order.add(pillsForInput3);
		order.add(chocolateForInput3);
		Mockito.when(roundingPolicy.round(BigDecimal.valueOf(2.799))).thenReturn(BigDecimal.valueOf(2.8));
		Mockito.when(roundingPolicy.round(BigDecimal.valueOf(1.3995))).thenReturn(BigDecimal.valueOf(1.4));
		Mockito.when(roundingPolicy.round(BigDecimal.valueOf(1.899))).thenReturn(BigDecimal.valueOf(1.9));
		Mockito.when(roundingPolicy.round(BigDecimal.valueOf(0.5625))).thenReturn(BigDecimal.valueOf(0.6));
		order.processOrder();
		assertEquals(BigDecimal.valueOf(74.68), order.getTotalCost());
		assertEquals(BigDecimal.valueOf(6.70).setScale(2), order.getTotalTaxes());
		Mockito.verify(roundingPolicy, Mockito.times(4)).round(Mockito.any());
	}

}
