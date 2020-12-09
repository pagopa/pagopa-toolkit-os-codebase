package pagopa.gov.it.toolkit.paymentNoticeGenerator;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import pagopa.gov.it.toolkit.paymentNoticeGenerator.exception.ValidationExceptionTest;

/**
 * Performs all tests on the payment notice section
 */
@RunWith(Suite.class)
@SuiteClasses({ PaymentNoticeGenerationTest.class, ValidationExceptionTest.class })
public class AllTests {

}
