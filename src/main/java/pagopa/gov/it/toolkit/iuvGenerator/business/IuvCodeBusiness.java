package pagopa.gov.it.toolkit.iuvGenerator.business;

import pagopa.gov.it.toolkit.iuvGenerator.bean.IuvCodeGenerator;
import pagopa.gov.it.toolkit.iuvGenerator.validation.IuvCodeValidation;
import pagopa.gov.it.toolkit.iuvGenerator.validation.IuvCodeValidationImpl;

/**
 * Business logic class
 */
public class IuvCodeBusiness {

    /**
     * Generates the <code>iuv</code>
     * 
     * @param auxDigit
     * @param segregationCode
     * @param applicationCode
     * @return the <code>iuv</code>
     * @see pagopa.gov.it.toolkit.iuvGenerator.bean.IuvCodeGenerator
     * 
     */
    public static String generateIUV(int auxDigit, Integer segregationCode, Integer applicationCode) {
        IuvAlghoritmGenerator iuvGenerator = new IuvAlghoritmGenerator.Builder().build(auxDigit);
        return iuvGenerator.generate(segregationCode, applicationCode);
    }

    /**
     * Validates a iuvCodeGenerator
     * 
     * @param iuvCodeGenerator
     *            the bean to validate.
     * @see IuvCodeGenerator
     * @see pagopa.gov.it.toolkit.debtPositionGenerator.validation.DebtPositionValidationImpl
     */
    public static void validate(IuvCodeGenerator iuvCodeGenerator) {
        IuvCodeValidation iuvCodeValidation = new IuvCodeValidationImpl();
        iuvCodeValidation.validate(iuvCodeGenerator);
    }
}
