package pagopa.gov.it.toolkit.debtPositionGenerator;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import pagopa.gov.it.toolkit.debtPositionGenerator.exception.DebtPositionExceptionTest;

/**
 * Performs all tests on the debt position section
 */
@RunWith(Suite.class)
@SuiteClasses({ DebtPositionExceptionTest.class, DebtPositionGenerationTest.class, DebtPositionManagementTest.class })
public class AllTests {

}
