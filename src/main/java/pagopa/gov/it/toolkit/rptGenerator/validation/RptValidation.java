package pagopa.gov.it.toolkit.rptGenerator.validation;

import pagopa.gov.it.toolkit.rptGenerator.bean.RptContainer;
import pagopa.gov.it.toolkit.rptGenerator.exception.ValidationException;

/**
 * Validation of RPT interface
 */
public interface RptValidation {

    /**
     * Validate the rpt Container
     * 
     * @param rptContainer
     *            the Rpt Container Bean
     * @throws ValidationException
     */
    void validate(RptContainer rptContainer) throws ValidationException;

    /**
     * Constraints validation by annotation of the rpt or one of its components
     * 
     * @param objectToValidate
     * @throws ValidationException
     */
    <T> void checkConstraints(T objectToValidate) throws ValidationException;
}
