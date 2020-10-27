package it.eng.ToolkitPagoPA.iuvGenerator;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import it.eng.ToolkitPagoPA.iuvGenerator.business.IuvAlghoritmAuxDigit0Test;
import it.eng.ToolkitPagoPA.iuvGenerator.business.IuvAlghoritmAuxDigit3Test;
import it.eng.ToolkitPagoPA.iuvGenerator.common.IuvSequenceReaderImplTest;
import it.eng.ToolkitPagoPA.iuvGenerator.validation.IuvCodeValidationImplTest;

@RunWith(Suite.class)
@SuiteClasses({ IuvAlghoritmAuxDigit0Test.class, IuvAlghoritmAuxDigit3Test.class, IuvSequenceReaderImplTest.class,
		IuvCodeValidationImplTest.class })
public class AllTests {

}
