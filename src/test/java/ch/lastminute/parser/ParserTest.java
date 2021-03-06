package ch.lastminute.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import ch.lastminute.item.Item;
import ch.lastminute.item.ItemType;

public class ParserTest {

	@Test
	public void emptyStringLeadsToEmptyCartTest() {
		final String string = "";
		final Parser parser = new Parser();
		assertEquals(0, parser.parse(string).size());
	}

	@Test
	public void stringWithOneObjectTest() {
		final Item book = new Item("book", BigDecimal.valueOf(12.49), ItemType.BOOK, false);
		final String string = "1 book at 12.49";
		final Parser parser = new Parser();
		assertTrue(parser.parse(string).contains(book));
	}

	@Test
	public void stringWithObjectDuplicatedTest() {
		final String string = "2 book at 12.49";
		final Parser parser = new Parser();
		assertEquals(parser.parse(string).size(), 2);
	}

	@Test
	public void stringWithMultipleObjectsTest() {
		final String string = "1 book at 12.49\n1 music CD at 14.99\n1 chocolate bar at 0.85\n";
		final Item bookForInput1 = new Item("book", BigDecimal.valueOf(12.49), ItemType.BOOK, false);
		final Item cdForInput1 = new Item("music CD", BigDecimal.valueOf(14.99), ItemType.OTHER, false);
		final Item chocolateForInput1 = new Item("chocolate bar", BigDecimal.valueOf(0.85), ItemType.FOOD, false);
		final Parser parser = new Parser();
		final List<Item> items = parser.parse(string);
		assertEquals(items.size(), 3);
		assertTrue(items.contains(bookForInput1));
		assertTrue(items.contains(cdForInput1));
		assertTrue(items.contains(chocolateForInput1));
	}

	@Test
	public void stringWithMultipleObjectsAndImportKeywordTest() {
		final String string = "1 imported box of chocolates at 10.00\n1 imported bottle of perfume at 47.50\n";
		final Item chocolateForInput2 = new Item("imported box of chocolates", BigDecimal.valueOf(10.00), ItemType.FOOD, true);
		final Item perfumForInput2 = new Item("imported bottle of perfume", BigDecimal.valueOf(47.50), ItemType.OTHER, true);
		final Parser parser = new Parser();
		final List<Item> items = parser.parse(string);
		assertEquals(items.size(), 2);
		assertTrue(items.contains(chocolateForInput2));
		assertTrue(items.contains(perfumForInput2));
	}

}
