package ch.lastminute.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AllIntegrationAndAcceptanceTests.class, AllUnitTests.class })
public class AllTests {

}
