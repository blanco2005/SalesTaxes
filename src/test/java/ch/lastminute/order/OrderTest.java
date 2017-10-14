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
	Item item1;
	TaxCalculator taxCalculator;

	@Before
	public void setUp() {
		taxCalculator = Mockito.mock(TaxCalculator.class);
		order = new Order(taxCalculator);
		item1 = new Item("description", BigDecimal.valueOf(10), ItemType.BOOK, true);
	}

	@Test
	public void emptyOrderTest() {
		assertEquals(order.getShoppingBasket().size(), 0);
	}

	@Test
	public void nonEmptyOrderTest() {
		order.add(item1);
		assertEquals(order.getShoppingBasket().size(), 1);
	}

	@Test
	public void orderContainsInsertedObjectsTest() {
		order.add(item1);
		assertTrue(order.getShoppingBasket().contains(item1));
	}

	@Test
	public void itemInsertedTwiceAppearsTwiceTest() {
		order.add(item1);
		order.add(item1);
		assertEquals(order.getShoppingBasket().size(), 2);
	}

	@Test
	public void orderIsEmptyAfterClearTest() {
		order.add(item1);
		order.add(item1);
		order.clear();
		assertEquals(order.getShoppingBasket().size(), 0);
	}

	@Test
	public void removeElementTest() {
		order.add(item1);
		order.removeItem(item1);
		assertEquals(order.getShoppingBasket().size(), 0);
	}

	@Test
	public void processEmptyCartTest() {
		order.processOrder();
		assertEquals(order.getItemToTaxMap().size(), 0);
	}

	@Test
	public void processNonEmptyCartLeadToNonEmptyTaxMapTest() {
		order.add(item1);
		order.processOrder();
		assertEquals(order.getItemToTaxMap().size(), 1);
	}

	@Test
	public void processNonEmptyCartLeadToCorrectTaxMapTest() {
		order.add(item1);
		Mockito.when(taxCalculator.calculateTaxes(item1)).thenReturn(BigDecimal.valueOf(0.5));
		order.processOrder();
		assertEquals(order.getItemToTaxMap().get(item1), BigDecimal.valueOf(0.5));
		Mockito.verify(taxCalculator, Mockito.times(1)).calculateTaxes(item1);
	}

	@Test
	public void processCartWithDuplicatedItemsComputesTaxesOnlyOnceTest() {
		order.add(item1);
		order.add(item1);
		Mockito.when(taxCalculator.calculateTaxes(item1)).thenReturn(BigDecimal.valueOf(0.5));
		order.processOrder();
		Mockito.verify(taxCalculator, Mockito.times(1)).calculateTaxes(item1);
	}

	@Test
	public void getTotalCostForInput1Test() {
		final Item book = new Item("book", BigDecimal.valueOf(12.49), ItemType.BOOK, false);
		final Item cd = new Item("music cd", BigDecimal.valueOf(14.99), ItemType.OTHER, false);
		final Item chocolate = new Item("chocolate bar", BigDecimal.valueOf(0.85), ItemType.FOOD, false);
		order.add(book);
		order.add(cd);
		order.add(chocolate);
		Mockito.when(taxCalculator.calculateTaxes(book)).thenReturn(BigDecimal.valueOf(0));
		Mockito.when(taxCalculator.calculateTaxes(cd)).thenReturn(BigDecimal.valueOf(1.5));
		Mockito.when(taxCalculator.calculateTaxes(chocolate)).thenReturn(BigDecimal.valueOf(0));
		order.processOrder();
		assertEquals(BigDecimal.valueOf(29.83), order.getTotalCost());
	}

	@Test
	public void getTotalCostForInput2Test() {
		final Item chocolate = new Item("chocolate", BigDecimal.valueOf(10.00), ItemType.BOOK, true);
		final Item perfum = new Item("perfum", BigDecimal.valueOf(47.50), ItemType.OTHER, true);
		order.add(chocolate);
		order.add(perfum);
		Mockito.when(taxCalculator.calculateTaxes(chocolate)).thenReturn(BigDecimal.valueOf(0.5));
		Mockito.when(taxCalculator.calculateTaxes(perfum)).thenReturn(BigDecimal.valueOf(7.15));
		order.processOrder();
		assertEquals(BigDecimal.valueOf(65.15), order.getTotalCost());
	}

	@Test
	public void getTotalCostForInput3Test() {
		final Item perfum = new Item("perfum", BigDecimal.valueOf(27.99), ItemType.OTHER, true);
		final Item notImportedPerfum = new Item("perfum", BigDecimal.valueOf(18.99), ItemType.OTHER, false);
		final Item pills = new Item("pills", BigDecimal.valueOf(9.75), ItemType.MEDICAL, false);
		final Item chocolate = new Item("chocolates", BigDecimal.valueOf(11.25), ItemType.FOOD, true);
		order.add(perfum);
		order.add(notImportedPerfum);
		order.add(pills);
		order.add(chocolate);
		Mockito.when(taxCalculator.calculateTaxes(perfum)).thenReturn(BigDecimal.valueOf(4.2));
		Mockito.when(taxCalculator.calculateTaxes(notImportedPerfum)).thenReturn(BigDecimal.valueOf(1.90));
		Mockito.when(taxCalculator.calculateTaxes(pills)).thenReturn(BigDecimal.valueOf(0));
		Mockito.when(taxCalculator.calculateTaxes(chocolate)).thenReturn(BigDecimal.valueOf(0.6));
		order.processOrder();
		assertEquals(BigDecimal.valueOf(74.68), order.getTotalCost());
	}

	@Test
	public void getTotalTaxesForInput1Test() {
		final Item book = new Item("book", BigDecimal.valueOf(12.49), ItemType.BOOK, false);
		final Item cd = new Item("music cd", BigDecimal.valueOf(14.99), ItemType.OTHER, false);
		final Item chocolate = new Item("chocolate bar", BigDecimal.valueOf(0.85), ItemType.FOOD, false);
		order.add(book);
		order.add(cd);
		order.add(chocolate);
		Mockito.when(taxCalculator.calculateTaxes(book)).thenReturn(BigDecimal.valueOf(0));
		Mockito.when(taxCalculator.calculateTaxes(cd)).thenReturn(BigDecimal.valueOf(1.5));
		Mockito.when(taxCalculator.calculateTaxes(chocolate)).thenReturn(BigDecimal.valueOf(0));
		order.processOrder();
		assertEquals(BigDecimal.valueOf(1.50), order.getTotalTaxes());
	}

}
