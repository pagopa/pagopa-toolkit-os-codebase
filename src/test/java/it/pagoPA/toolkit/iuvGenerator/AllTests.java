package it.pagoPA.toolkit.iuvGenerator;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import it.pagoPA.toolkit.iuvGenerator.business.IuvAlghoritmAuxDigit0Test;
import it.pagoPA.toolkit.iuvGenerator.business.IuvAlghoritmAuxDigit3Test;
import it.pagoPA.toolkit.iuvGenerator.common.IuvSequenceReaderImplTest;
import it.pagoPA.toolkit.iuvGenerator.exception.ValidationExceptionTest;
import it.pagoPA.toolkit.iuvGenerator.validation.IuvCodeValidationImplTest;

@RunWith(Suite.class)
@SuiteClasses({ IuvAlghoritmAuxDigit0Test.class, IuvAlghoritmAuxDigit3Test.class, IuvSequenceReaderImplTest.class,
        IuvCodeValidationImplTest.class, ValidationExceptionTest.class })
public class AllTests {

}
