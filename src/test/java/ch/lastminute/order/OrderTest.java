package ch.lastminute.order;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ch.lastminute.item.Item;
import ch.lastminute.item.ItemType;
import ch.lastminute.salestaxes.TaxCalculator;

public class OrderTest {

	Order order;
	Item itemExample = new Item("description", BigDecimal.valueOf(10), ItemType.BOOK, true);
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
		taxCalculator = Mockito.mock(TaxCalculator.class);
		order = new Order(taxCalculator);
	}

	@Test
	public void emptyOrderTest() {
		assertEquals(order.getShoppingBasket().size(), 0);
	}

	@Test
	public void nonEmptyOrderTest() {
		order.add(itemExample);
		assertEquals(order.getShoppingBasket().size(), 1);
	}

	@Test
	public void orderContainsInsertedObjectsTest() {
		order.add(itemExample);
		assertTrue(order.getShoppingBasket().contains(itemExample));
	}

	@Test
	public void itemInsertedTwiceAppearsTwiceTest() {
		order.add(itemExample);
		order.add(itemExample);
		assertEquals(order.getShoppingBasket().size(), 2);
	}

	@Test
	public void orderIsEmptyAfterClearTest() {
		order.add(itemExample);
		order.add(itemExample);
		order.clear();
		assertEquals(order.getShoppingBasket().size(), 0);
	}

	@Test
	public void removeElementTest() {
		order.add(itemExample);
		order.removeItem(itemExample);
		assertEquals(order.getShoppingBasket().size(), 0);
	}

	@Test
	public void processEmptyCartTest() {
		order.processOrder();
		assertEquals(order.getItemToTaxMap().size(), 0);
	}

	@Test
	public void processNonEmptyCartLeadToNonEmptyTaxMapTest() {
		order.add(itemExample);
		order.processOrder();
		assertEquals(order.getItemToTaxMap().size(), 1);
	}

	@Test
	public void processNonEmptyCartLeadToCorrectTaxMapTest() {
		order.add(itemExample);
		Mockito.when(taxCalculator.calculateTaxes(itemExample)).thenReturn(BigDecimal.valueOf(0.5));
		order.processOrder();
		assertEquals(order.getItemToTaxMap().get(itemExample), BigDecimal.valueOf(0.5));
		Mockito.verify(taxCalculator, Mockito.times(1)).calculateTaxes(itemExample);
	}

	@Test
	public void processCartWithDuplicatedItemsComputesTaxesOnlyOnceTest() {
		order.add(itemExample);
		order.add(itemExample);
		Mockito.when(taxCalculator.calculateTaxes(itemExample)).thenReturn(BigDecimal.valueOf(0.5));
		order.processOrder();
		Mockito.verify(taxCalculator, Mockito.times(1)).calculateTaxes(itemExample);
	}

	@Test
	public void getTotalCostForInput1Test() {
		order.add(bookForInput1);
		order.add(cdForInput1);
		order.add(chocolateForInput1);
		Mockito.when(taxCalculator.calculateTaxes(bookForInput1)).thenReturn(BigDecimal.valueOf(0));
		Mockito.when(taxCalculator.calculateTaxes(cdForInput1)).thenReturn(BigDecimal.valueOf(1.5));
		Mockito.when(taxCalculator.calculateTaxes(chocolateForInput1)).thenReturn(BigDecimal.valueOf(0));
		order.processOrder();
		assertEquals(BigDecimal.valueOf(29.83), order.getTotalCost());
	}

	@Test
	public void getTotalCostForInput2Test() {
		order.add(chocolateForInput2);
		order.add(perfumForInput2);
		Mockito.when(taxCalculator.calculateTaxes(chocolateForInput2)).thenReturn(BigDecimal.valueOf(0.5));
		Mockito.when(taxCalculator.calculateTaxes(perfumForInput2)).thenReturn(BigDecimal.valueOf(7.15));
		order.processOrder();
		assertEquals(BigDecimal.valueOf(65.15), order.getTotalCost());
	}

	@Test
	public void getTotalCostForInput3Test() {
		order.add(perfumForInput3);
		order.add(notImportedPerfumForInput3);
		order.add(pillsForInput3);
		order.add(chocolateForInput3);
		Mockito.when(taxCalculator.calculateTaxes(perfumForInput3)).thenReturn(BigDecimal.valueOf(4.2));
		Mockito.when(taxCalculator.calculateTaxes(notImportedPerfumForInput3)).thenReturn(BigDecimal.valueOf(1.90));
		Mockito.when(taxCalculator.calculateTaxes(pillsForInput3)).thenReturn(BigDecimal.valueOf(0));
		Mockito.when(taxCalculator.calculateTaxes(chocolateForInput3)).thenReturn(BigDecimal.valueOf(0.6));
		order.processOrder();
		assertEquals(BigDecimal.valueOf(74.68), order.getTotalCost());
	}

	@Test
	public void getTotalTaxesForInput1Test() {
		order.add(bookForInput1);
		order.add(cdForInput1);
		order.add(chocolateForInput1);
		Mockito.when(taxCalculator.calculateTaxes(bookForInput1)).thenReturn(BigDecimal.valueOf(0));
		Mockito.when(taxCalculator.calculateTaxes(cdForInput1)).thenReturn(BigDecimal.valueOf(1.5));
		Mockito.when(taxCalculator.calculateTaxes(chocolateForInput1)).thenReturn(BigDecimal.valueOf(0));
		order.processOrder();
		assertEquals(BigDecimal.valueOf(1.50).setScale(2), order.getTotalTaxes());
	}

	@Test
	public void getTotalTaxesForInput2Test() {
		order.add(chocolateForInput2);
		order.add(perfumForInput2);
		Mockito.when(taxCalculator.calculateTaxes(chocolateForInput2)).thenReturn(BigDecimal.valueOf(0.5));
		Mockito.when(taxCalculator.calculateTaxes(perfumForInput2)).thenReturn(BigDecimal.valueOf(7.15));
		order.processOrder();
		assertEquals(BigDecimal.valueOf(7.65), order.getTotalTaxes());
	}

	@Test
	public void getTotalTaxesForInput3Test() {
		order.add(perfumForInput3);
		order.add(notImportedPerfumForInput3);
		order.add(pillsForInput3);
		order.add(chocolateForInput3);
		Mockito.when(taxCalculator.calculateTaxes(perfumForInput3)).thenReturn(BigDecimal.valueOf(4.2));
		Mockito.when(taxCalculator.calculateTaxes(notImportedPerfumForInput3)).thenReturn(BigDecimal.valueOf(1.90));
		Mockito.when(taxCalculator.calculateTaxes(pillsForInput3)).thenReturn(BigDecimal.valueOf(0));
		Mockito.when(taxCalculator.calculateTaxes(chocolateForInput3)).thenReturn(BigDecimal.valueOf(0.6));
		order.processOrder();
		assertEquals(BigDecimal.valueOf(6.70).setScale(2), order.getTotalTaxes());
	}

	@Test(expected = IllegalStateException.class)
	public void getReceiptForEmptyOrder() {
		order.toString();
	}

	@Test
	public void getReceiptForInput1Order() {
		order.add(bookForInput1);
		order.add(cdForInput1);
		order.add(chocolateForInput1);
		Mockito.when(taxCalculator.calculateTaxes(bookForInput1)).thenReturn(BigDecimal.valueOf(0));
		Mockito.when(taxCalculator.calculateTaxes(cdForInput1)).thenReturn(BigDecimal.valueOf(1.5));
		Mockito.when(taxCalculator.calculateTaxes(chocolateForInput1)).thenReturn(BigDecimal.valueOf(0));
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
		Mockito.when(taxCalculator.calculateTaxes(chocolateForInput2)).thenReturn(BigDecimal.valueOf(0.5));
		Mockito.when(taxCalculator.calculateTaxes(perfumForInput2)).thenReturn(BigDecimal.valueOf(7.15));
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
		Mockito.when(taxCalculator.calculateTaxes(perfumForInput3)).thenReturn(BigDecimal.valueOf(4.2));
		Mockito.when(taxCalculator.calculateTaxes(notImportedPerfumForInput3)).thenReturn(BigDecimal.valueOf(1.90));
		Mockito.when(taxCalculator.calculateTaxes(pillsForInput3)).thenReturn(BigDecimal.valueOf(0));
		Mockito.when(taxCalculator.calculateTaxes(chocolateForInput3)).thenReturn(BigDecimal.valueOf(0.6));
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
