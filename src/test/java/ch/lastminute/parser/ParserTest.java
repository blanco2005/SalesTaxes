package ch.lastminute.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

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

}
