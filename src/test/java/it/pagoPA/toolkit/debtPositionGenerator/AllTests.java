package it.pagoPA.toolkit.debtPositionGenerator;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import it.pagoPA.toolkit.debtPositionGenerator.exception.DebtPositionExceptionTest;

@RunWith(Suite.class)
@SuiteClasses({ 
    DebtPositionExceptionTest.class, 
    DebtPositionGenerationTest.class, 
    DebtPositionManagementTest.class 
})
public class AllTests {

}
