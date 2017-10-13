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
	@Parameters({ "0", "-1" })
	public void roundingInvalidAmountTest(final double amount) {
		roundingPolicy.round(new BigDecimal(amount));
	}

	@Test
	public void roundingToSameTest() {
		assertEquals(new BigDecimal("10.10"), roundingPolicy.round(new BigDecimal("10.1")));
	}

}
