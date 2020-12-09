package pagopa.gov.it.toolkit.iuvGenerator.exception;

import org.junit.Test;

import pagopa.gov.it.toolkit.iuvGenerator.IuvCodeGeneration;
import pagopa.gov.it.toolkit.iuvGenerator.bean.IuvCodeGenerator;
import pagopa.gov.it.toolkit.iuvGenerator.exception.ValidationException;
import pagopa.gov.it.toolkit.iuvGenerator.validation.IuvCodeValidation;
import pagopa.gov.it.toolkit.iuvGenerator.validation.IuvCodeValidationImpl;

/**
 * Tests on thrown exceptions
 */
public class ValidationExceptionTest {

    /**
     * Test method on invalid <code>auxDigit</code>
     * 
     * @throws Exception
     * @see ValidationException
     * @see IuvCodeGeneration
     */
    @Test(expected = ValidationException.class)
    public void testAuxDigit() throws Exception {
        IuvCodeGeneration.generate(1, 01, 02);
    }

    /**
     * Test method on iuv code validation when <code>auxDigit</code> = 0 and
     * <code>applicationCode</code> is not valid
     * 
     * @see ValidationException
     * @see IuvCodeGenerator
     * @see IuvCodeValidation
     */
    @Test(expected = ValidationException.class)
    public void testAuxDigit0Validate() {
        IuvCodeValidation iuvCodeValidation = new IuvCodeValidationImpl();

        IuvCodeGenerator iuvCodeGenerator = new IuvCodeGenerator.Builder().setAuxDigit(0).setApplicationCode(null)
                .setSegregationCode(new Integer(05)).build();

        iuvCodeValidation.validate(iuvCodeGenerator);
    }

    /**
     * Test method on iuv code validation when <code>auxDigit</code> = 3 and
     * <code>segregationCode</code> is not valid
     * 
     * @see ValidationException
     * @see IuvCodeGenerator
     * @see IuvCodeValidation
     */
    @Test(expected = ValidationException.class)
    public void testAuxDigit3Validate() {
        IuvCodeValidation iuvCodeValidation = new IuvCodeValidationImpl();

        IuvCodeGenerator iuvCodeGenerator = new IuvCodeGenerator.Builder().setAuxDigit(3)
                .setApplicationCode(new Integer(5)).setSegregationCode(null).build();

        iuvCodeValidation.validate(iuvCodeGenerator);
    }
}
