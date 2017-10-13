package ch.lastminute.rounding;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class RoundingTest {

	@Test
	public void roundingZeroTest() {
		final RoundingPolicy roundingPolicy = new RoundingPolicy();
		assertEquals(BigDecimal.ZERO, roundingPolicy.round(BigDecimal.ZERO));
	}

}
