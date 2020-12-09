package pagopa.gov.it.toolkit.iuvGenerator.validation;

import org.junit.Before;
import org.junit.Test;

import pagopa.gov.it.toolkit.iuvGenerator.bean.IuvCodeGenerator;
import pagopa.gov.it.toolkit.iuvGenerator.validation.IuvCodeValidation;
import pagopa.gov.it.toolkit.iuvGenerator.validation.IuvCodeValidationImpl;

/**
 * Tests on iuv code validation
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
     * Test method on iuv code validation
     * 
     * @see IuvCodeGenerator
     * @see IuvCodeValidationImpl#validate(IuvCodeGenerator)
     */
    @Test
    public void testValidate() {
        iuvCodeValidation.validate(iuvCodeGenerator);
    }
}
