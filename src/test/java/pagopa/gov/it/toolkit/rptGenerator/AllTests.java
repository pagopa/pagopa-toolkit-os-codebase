package pagopa.gov.it.toolkit.rptGenerator;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import pagopa.gov.it.toolkit.rptGenerator.exception.ValidationExceptionTest;

/**
 * Performs all tests on the rpt section
 */
@RunWith(Suite.class)
@SuiteClasses({ RptGenerationTest.class, RptManagementTest.class, ValidationExceptionTest.class })
public class AllTests {

}
