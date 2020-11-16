package it.pagoPA.toolkit.paymentNoticeGenerator;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import it.pagoPA.toolkit.paymentNoticeGenerator.exception.ValidationExceptionTest;

@RunWith(Suite.class)
@SuiteClasses({ PaymentNoticeGenerationTest.class, ValidationExceptionTest.class })
public class AllTests {

}
