package ch.lastminute.acceptance;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import ch.lastminute.item.Item;
import ch.lastminute.item.ItemType;
import ch.lastminute.order.Order;
import ch.lastminute.rounding.StandardRoundingPolicy;
import ch.lastminute.salestaxes.StandardTaxCalculator;
import ch.lastminute.salestaxes.TaxCalculator;

public class AcceptanceTest {

	Order order;
	TaxCalculator taxCalculator;

	/** Items for input 1 **/
	final Item bookForInput1 = new Item("book", BigDecimal.valueOf(12.49), ItemType.BOOK, false);
	final Item cdForInput1 = new Item("music CD", BigDecimal.valueOf(14.99), ItemType.OTHER, false);
	final Item chocolateForInput1 = new Item("chocolate bar", BigDecimal.valueOf(0.85), ItemType.FOOD, false);

	/** Items for input 2 **/
	final Item chocolateForInput2 = new Item("imported box of chocolates", BigDecimal.valueOf(10.00), ItemType.BOOK, true);
	final Item perfumForInput2 = new Item("imported bottle of perfume", BigDecimal.valueOf(47.50), ItemType.OTHER, true);

	/** Items for input 3 **/
	final Item perfumForInput3 = new Item("imported bottle of perfume", BigDecimal.valueOf(27.99), ItemType.OTHER, true);
	final Item notImportedPerfumForInput3 = new Item("bottle of perfume", BigDecimal.valueOf(18.99), ItemType.OTHER, false);
	final Item pillsForInput3 = new Item("packet of headache pills", BigDecimal.valueOf(9.75), ItemType.MEDICAL, false);
	final Item chocolateForInput3 = new Item("imported box of chocolates", BigDecimal.valueOf(11.25), ItemType.FOOD, true);

	@Before
	public void setUp() {
		taxCalculator = new StandardTaxCalculator(new StandardRoundingPolicy());
		order = new Order(taxCalculator);
	}

	@Test
	public void getReceiptForInput1Order() {
		order.add(bookForInput1);
		order.add(cdForInput1);
		order.add(chocolateForInput1);
		order.processOrder();
		final StringBuilder sb = new StringBuilder();
		sb.append("1 book: 12.49\n");
		sb.append("1 music CD: 16.49\n");
		sb.append("1 chocolate bar: 0.85\n");
		sb.append("Sales Taxes: 1.50\n");
		sb.append("Total: 29.83");
		assertEquals(sb.toString(), order.toString());
	}

	@Test
	public void getReceiptForInput2Test() {
		order.add(chocolateForInput2);
		order.add(perfumForInput2);
		order.processOrder();
		final StringBuilder sb = new StringBuilder();
		sb.append("1 imported box of chocolates: 10.50\n");
		sb.append("1 imported bottle of perfume: 54.65\n");
		sb.append("Sales Taxes: 7.65\n");
		sb.append("Total: 65.15");
		assertEquals(sb.toString(), order.toString());
	}

	@Test
	public void getReceiptForInput3Test() {
		order.add(perfumForInput3);
		order.add(notImportedPerfumForInput3);
		order.add(pillsForInput3);
		order.add(chocolateForInput3);
		order.processOrder();
		final StringBuilder sb = new StringBuilder();
		sb.append("1 imported bottle of perfume: 32.19\n");
		sb.append("1 bottle of perfume: 20.89\n");
		sb.append("1 packet of headache pills: 9.75\n");
		sb.append("1 imported box of chocolates: 11.85\n");
		sb.append("Sales Taxes: 6.70\n");
		sb.append("Total: 74.68");
		assertEquals(sb.toString(), order.toString());
	}
}
