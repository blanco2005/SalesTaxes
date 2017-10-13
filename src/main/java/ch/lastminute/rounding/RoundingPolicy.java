package ch.lastminute.rounding;

import java.math.BigDecimal;

import ch.lastminute.errors.ErrorMsg;

public class RoundingPolicy {

	public BigDecimal round(final BigDecimal amount) {
		if (amount.compareTo(BigDecimal.ZERO) == 0 || amount.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException(ErrorMsg.INVALID_ROUNDING.toString());
		}
		if (amount.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0) {
			return amount;
		}
		else {
			return amount.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}

}
