package ch.lastminute.rounding;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class StandardRoundingPolicyTest {

	StandardRoundingPolicy roundingPolicy;

	@Before
	public void setUp() {
		roundingPolicy = new StandardRoundingPolicy();
	}

	@Test(expected = IllegalArgumentException.class)
	@Parameters({ "0", "-1" })
	public void roundingInvalidAmountTest(final double amount) {
		roundingPolicy.round(BigDecimal.valueOf(amount));
	}

	@Test
	@Parameters({
			"1, 1.00",
			"1.00, 1.00",
			"10, 10.00",
			"10.10, 10.1",
			"1.50, 1.49",
			"0.05, 0.02",
			"0.90, 0.875",
			"0.90, 0.874",
			"0.90, 0.876",
			"0.60, 0.5625",
			"0.05, 0.0001" })
	public void roundingTest(final double expected, final double amount) {
		assertEquals(BigDecimal.valueOf(expected).setScale(2), roundingPolicy.round(BigDecimal.valueOf(amount)));
	}

}
