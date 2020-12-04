/**
 * 
 */
package it.pagoPA.toolkit.iuvGenerator.validation;

import org.junit.Before;
import org.junit.Test;

import it.pagoPA.toolkit.iuvGenerator.bean.IuvCodeGenerator;
import it.pagoPA.toolkit.iuvGenerator.validation.IuvCodeValidation;
import it.pagoPA.toolkit.iuvGenerator.validation.IuvCodeValidationImpl;

/**
 *
 */
public class IuvCodeValidationImplTest {

    private IuvCodeValidation iuvCodeValidation;

    private IuvCodeGenerator iuvCodeGenerator;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        iuvCodeValidation = new IuvCodeValidationImpl();

        iuvCodeGenerator = new IuvCodeGenerator.Builder().setAuxDigit(3).setApplicationCode(new Integer(5))
                .setSegregationCode(new Integer(25)).build();
    }

    /**
     * Test method for
     * {@link it.pagoPA.toolkit.iuvGenerator.validation.IuvCodeValidationImpl#validate(it.pagoPA.toolkit.iuvGenerator.bean.IuvCodeGenerator)}
     * .
     */
    @Test
    public void testValidate() {
        iuvCodeValidation.validate(iuvCodeGenerator);
    }
}
