package pagopa.gov.it.toolkit.paymentNoticeGenerator.validation;

import pagopa.gov.it.toolkit.paymentNoticeGenerator.bean.PaymentNotice;
import pagopa.gov.it.toolkit.paymentNoticeGenerator.exception.ValidationException;

/**
 * Validation of PaymentNotice interface
 */
public interface PaymentNoticeValidation {

    /**
     * Validate the payment notice
     * 
     * @param paymentNotice
     *            the Payment Notice Bean
     * @throws ValidationException
     */
    void validate(PaymentNotice paymentNotice) throws ValidationException;

    /**
     * Constraints validation by annotation of the payment notice or one of its
     * components
     * 
     * @param objectToValidate
     * @throws ValidationException
     */
    <T> void checkConstraints(T objectToValidate) throws ValidationException;
}
