package ch.lastminute.rounding;

import java.math.BigDecimal;

public interface RoundingPolicy {

	public BigDecimal round(final BigDecimal amount);

}
