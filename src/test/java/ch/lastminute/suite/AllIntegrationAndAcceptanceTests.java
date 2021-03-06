package ch.lastminute.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ch.lastminute.acceptance.AcceptanceTest;
import ch.lastminute.integration.order.taxes.IntegrationOrderTaxesTest;
import ch.lastminute.integration.taxes.rounding.IntegrationTaxesRoundingTest;

@RunWith(Suite.class)
@SuiteClasses({
		IntegrationTaxesRoundingTest.class,
		IntegrationOrderTaxesTest.class,
		AcceptanceTest.class

})
public class AllIntegrationAndAcceptanceTests {

}
