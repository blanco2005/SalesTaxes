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

	@Test
	public void roundingOneTest() {
		final RoundingPolicy roundingPolicy = new RoundingPolicy();
		assertEquals(BigDecimal.ONE, roundingPolicy.round(BigDecimal.ONE));
	}

	@Test
	public void roundingOneWithDecimalsTest() {
		final RoundingPolicy roundingPolicy = new RoundingPolicy();
		assertEquals(new BigDecimal(1.0), roundingPolicy.round(new BigDecimal(1.0)));
	}

}
