package ch.lastminute.rounding;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class RoundingTest {

	RoundingPolicy roundingPolicy;

	@Before
	public void setUp() {
		roundingPolicy = new RoundingPolicy();
	}

	@Test
	@Parameters({ "1", "1.00", "10" })
	public void roundingIntegersTest(final double amount) {
		assertEquals(new BigDecimal(amount), roundingPolicy.round(new BigDecimal(amount)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void roundingZeroTest() {
		roundingPolicy.round(BigDecimal.ZERO);
	}

	@Test(expected = IllegalArgumentException.class)
	public void roundingNegativeTest() {
		roundingPolicy.round(new BigDecimal("-10"));
	}

}
