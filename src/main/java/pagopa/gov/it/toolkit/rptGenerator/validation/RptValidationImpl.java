package pagopa.gov.it.toolkit.rptGenerator.validation;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import pagopa.gov.it.toolkit.common.Constants;
import pagopa.gov.it.toolkit.common.ErrorMessages;
import pagopa.gov.it.toolkit.common.validation.GenericValidation;
import pagopa.gov.it.toolkit.rptGenerator.bean.RptContainer;
import pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDatiSingoloVersamento;
import pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptSoggetto;
import pagopa.gov.it.toolkit.rptGenerator.exception.ValidationException;
import pagopa.gov.it.toolkit.rptGenerator.xsd.StTipoIdentificativoUnivocoPersFG;
import pagopa.gov.it.toolkit.rptGenerator.xsd.StTipoVersamento;

/**
 * Implementation of RptValidation interface
 */
public class RptValidationImpl implements RptValidation {

    /**
     * Validate the rpt<br/>
     * The validation includes:
     * <ul>
     * <li>checkConstraints - validation by annotation
     * <li>checkUniqueIdentificationType - if
     * <code>uniqueIdentificationType</code> = G then it must be
     * <code>uniqueIdentificationCode</code> = valid VAT number; if
     * <code>uniqueIdentificationType</code> = F then it must be
     * <code>uniqueIdentificationCode</code> = valid fiscal code
     * <li>checkIbanAddebito - <code>ibanAddebito</code> mandatory if
     * <code>tipoVersamento</code> = 'AD'
     * <li>checkSinglePaymentsDetailList - the list of
     * <code>RptDatiSingoloVersamento</code> must have a maximum of 5 elements
     * <li>checkAmounts - the sum of the amounts in
     * <code>RptDatiSingoloVersamento</code> must coincide with the amount in
     * <code>RptDatiVersamento</code>
     * <li>checkIbanAccredito - <code>ibanAccredito</code> must not be present
     * if <code>datiMarcaBolloDigitale</code> has been populated. In all other
     * cases <code>ibanAccredito</code> is mandatory.
     * </ul>
     * 
     * @param rptContainer
     * @throws ValidationException
     * @see RptContainer
     * @see ValidationException
     */
    @Override
    public void validate(RptContainer rptContainer) throws ValidationException {
        checkConstraints(rptContainer);

        if (rptContainer.getRpt().getSoggettoVersante() != null) {
            checkUniqueIdentificationType(rptContainer.getRpt().getSoggettoVersante());
        }

        checkUniqueIdentificationType(rptContainer.getRpt().getSoggettoPagatore());

        checkIbanAddebito(rptContainer);

        checkSinglePaymentsDetailList(rptContainer);

        checkAmounts(rptContainer);

        checkIbanAccredito(rptContainer);
    }

    /**
     * @param objectToValidate
     *            the bean to validate. It can be the rpt or one of its
     *            components.
     * @throws ValidationException
     * @see ValidationException
     */
    public <T> void checkConstraints(T objectToValidate) throws ValidationException {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<T>> validationInputResults = validator.validate(objectToValidate);
        if (!validationInputResults.isEmpty()) {
            throw new ValidationException(validationInputResults);
        }
    }

    /**
     * @param rptSoggetto
     * @see RptSoggetto
     */
    private void checkUniqueIdentificationType(RptSoggetto rptSoggetto) {
        String codiceIdentificativoUnivoco = rptSoggetto.getIdentificativoUnivoco().getCodiceIdentificativoUnivoco();
        StTipoIdentificativoUnivocoPersFG tipoIdentificativoUnivoco = rptSoggetto.getIdentificativoUnivoco()
                .getTipoIdentificativoUnivoco();
        if (!codiceIdentificativoUnivoco.equals(Constants.UNIQUE_IDENTIFICATION_CODE_DEFAULT)) {
            if (tipoIdentificativoUnivoco.equals(StTipoIdentificativoUnivocoPersFG.G)) {
                if (GenericValidation.checkVatNumber(codiceIdentificativoUnivoco)) {
                    throw new ValidationException(ErrorMessages.VALIDATION_VAT_NUMBER_ERROR);
                }
            } else if (tipoIdentificativoUnivoco.equals(StTipoIdentificativoUnivocoPersFG.F)) {
                if (GenericValidation.checkFiscalCode(codiceIdentificativoUnivoco) > 0) {
                    throw new ValidationException(ErrorMessages.VALIDATION_FISCAL_CODE_ERROR);
                }
            }
        }
    }

    /**
     * @param rptContainer
     * @see RptContainer
     */
    private void checkIbanAddebito(RptContainer rptContainer) {
        if (rptContainer.getRpt().getDatiVersamento().getTipoVersamento().equals(StTipoVersamento.AD)) {
            if (rptContainer.getRpt().getDatiVersamento().getIbanAddebito() == null) {
                throw new ValidationException(ErrorMessages.VALIDATION_IBAN_ADDEBITO_ERROR);
            }
        }
    }

    /**
     * @param rptContainer
     * @see RptContainer
     */
    private void checkSinglePaymentsDetailList(RptContainer rptContainer) {
        if (rptContainer.getRpt().getDatiSingoloVersamentoList().size() > Constants.SINGLE_PAYMENT_LIST_MAX_SIZE) {
            throw new ValidationException(ErrorMessages.VALIDATION_SINGLE_PAYMENT_LIST_SIZE_ERROR);
        }
    }

    /**
     * @param rptContainer
     * @see RptContainer
     */
    private void checkAmounts(RptContainer rptContainer) {
        BigDecimal amountSinglePaymentSum = rptContainer.getRpt().getDatiSingoloVersamentoList().stream()
                .map(RptDatiSingoloVersamento::getImportoSingoloVersamento).reduce(BigDecimal.ZERO, BigDecimal::add);
        if (rptContainer.getRpt().getDatiVersamento().getImportoTotaleDaVersare()
                .compareTo(amountSinglePaymentSum) != 0) {
            throw new ValidationException(ErrorMessages.VALIDATION_AMOUNTS_ERROR);
        }
    }

    /**
     * @param rptContainer
     * @see RptContainer
     */
    private void checkIbanAccredito(RptContainer rptContainer) {
        for (RptDatiSingoloVersamento rptDatiSingoloVersamento : rptContainer.getRpt().getDatiSingoloVersamentoList()) {
            if (rptDatiSingoloVersamento.getDatiMarcaBolloDigitale() != null) {
                if (rptDatiSingoloVersamento.getIbanAccredito() != null) {
                    throw new ValidationException(ErrorMessages.VALIDATION_INVALID_IBAN_ACCREDITO_ERROR);
                }
            } else {
                if (rptDatiSingoloVersamento.getIbanAccredito() == null) {
                    throw new ValidationException(ErrorMessages.VALIDATION_MANDATORY_IBAN_ACCREDITO_ERROR);
                }
            }
        }
    }
}
