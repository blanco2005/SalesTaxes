package ch.lastminute.parser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ParserTest {

	@Test
	public void emptyStringLeadsToEmptyCartTest() {
		final String string = "";
		final Parser parser = new Parser();
		assertEquals(0, parser.parse(string).size());
	}

}
