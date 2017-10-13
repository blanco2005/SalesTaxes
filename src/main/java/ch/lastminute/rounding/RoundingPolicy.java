package ch.lastminute.rounding;

import java.math.BigDecimal;

public class RoundingPolicy {

	public BigDecimal round(final BigDecimal amount) {
		if (amount.compareTo(BigDecimal.ZERO) == 0) {
			throw new IllegalArgumentException();
		}
		if (amount.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0) {
			return amount;
		}
		else {
			return null;
		}
	}

}
