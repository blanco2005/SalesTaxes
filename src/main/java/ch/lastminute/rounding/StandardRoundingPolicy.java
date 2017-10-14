package ch.lastminute.rounding;

import java.math.BigDecimal;
import java.math.RoundingMode;

import ch.lastminute.errors.ErrorMsg;

public class StandardRoundingPolicy implements RoundingPolicy {

	@Override
	public BigDecimal round(final BigDecimal amount) {
		if (amount.compareTo(BigDecimal.ZERO) == 0 || amount.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException(ErrorMsg.INVALID_ROUNDING.toString());
		}

		return BigDecimal.valueOf((double) Math.round(amount.doubleValue() * 20) / 20)
				.setScale(2, RoundingMode.HALF_UP);
	}

}
