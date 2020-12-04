package pagopa.gov.it.toolkit.iuvGenerator;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import pagopa.gov.it.toolkit.iuvGenerator.business.IuvAlghoritmAuxDigit0Test;
import pagopa.gov.it.toolkit.iuvGenerator.business.IuvAlghoritmAuxDigit3Test;
import pagopa.gov.it.toolkit.iuvGenerator.common.IuvSequenceReaderImplTest;
import pagopa.gov.it.toolkit.iuvGenerator.exception.ValidationExceptionTest;
import pagopa.gov.it.toolkit.iuvGenerator.validation.IuvCodeValidationImplTest;

/**
 * Performs all tests on the iuv code section
 */
@RunWith(Suite.class)
@SuiteClasses({ IuvAlghoritmAuxDigit0Test.class, IuvAlghoritmAuxDigit3Test.class, IuvSequenceReaderImplTest.class,
        IuvCodeValidationImplTest.class, ValidationExceptionTest.class })
public class AllTests {

}
