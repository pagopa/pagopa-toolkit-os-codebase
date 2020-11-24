package it.pagoPA.toolkit.rptGenerator;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import it.pagoPA.toolkit.rptGenerator.exception.ValidationExceptionTest;

@RunWith(Suite.class)
@SuiteClasses({ RptGenerationTest.class, RptManagementTest.class, ValidationExceptionTest.class })
public class AllTests {

}
