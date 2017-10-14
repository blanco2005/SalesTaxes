package ch.lastminute.unit.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ch.lastminute.item.ItemTest;
import ch.lastminute.rounding.RoundingTest;

@RunWith(Suite.class)
@SuiteClasses({
		ItemTest.class,
		RoundingTest.class
})
public class AllTests {

}
