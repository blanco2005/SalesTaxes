package ch.lastminute.unit.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ch.lastminute.item.ItemTest;
import ch.lastminute.order.OrderTest;
import ch.lastminute.rounding.StandardRoundingPolicyTest;
import ch.lastminute.salestaxes.StandardTaxCalculatorTest;

@RunWith(Suite.class)
@SuiteClasses({
		ItemTest.class,
		StandardRoundingPolicyTest.class,
		StandardTaxCalculatorTest.class,
		OrderTest.class
})
public class AllTests {

}
