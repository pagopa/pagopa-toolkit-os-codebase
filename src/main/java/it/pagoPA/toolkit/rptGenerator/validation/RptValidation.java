package it.pagoPA.toolkit.rptGenerator.validation;

import it.pagoPA.toolkit.rptGenerator.bean.RptContainer;
import it.pagoPA.toolkit.rptGenerator.exception.ValidationException;

/**
 * RptValidation with rptContainer
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
}
