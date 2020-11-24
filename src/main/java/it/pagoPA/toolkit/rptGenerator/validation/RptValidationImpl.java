package it.pagoPA.toolkit.rptGenerator.validation;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import it.pagoPA.toolkit.common.Constants;
import it.pagoPA.toolkit.common.ErrorMessages;
import it.pagoPA.toolkit.common.validation.GenericValidation;
import it.pagoPA.toolkit.rptGenerator.bean.RptContainer;
import it.pagoPA.toolkit.rptGenerator.bean.rpt.RptDatiSingoloVersamento;
import it.pagoPA.toolkit.rptGenerator.bean.rpt.RptSoggetto;
import it.pagoPA.toolkit.rptGenerator.exception.ValidationException;
import it.pagoPA.toolkit.rptGenerator.xsd.StTipoIdentificativoUnivocoPersFG;
import it.pagoPA.toolkit.rptGenerator.xsd.StTipoVersamento;

/**
 * RptValidation with rptValidationBean
 */
public class RptValidationImpl implements RptValidation {

    /**
     * @param rptContainer
     * @throws ValidationException
     */
    @Override
    public void validate(RptContainer rptContainer) throws ValidationException {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<RptContainer>> validationInputResults = validator.validate(rptContainer);
        if (!validationInputResults.isEmpty()) {
            throw new ValidationException(validationInputResults);
        }

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
     * 
     * @param rptSoggetto
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
     * ibanAddebito obbligatorio qualora l'informazione tipoPagamento assuma il
     * valore 'AD'.
     * 
     * @param rptContainer
     */
    private void checkIbanAddebito(RptContainer rptContainer) {
        if (rptContainer.getRpt().getDatiVersamento().getTipoVersamento().equals(StTipoVersamento.AD)) {
            if (rptContainer.getRpt().getDatiVersamento().getIbanAddebito() == null) {
                throw new ValidationException(ErrorMessages.VALIDATION_IBAN_ADDEBITO_ERROR);
            }
        }
    }

    /**
     * 
     * @param rptContainer
     */
    private void checkSinglePaymentsDetailList(RptContainer rptContainer) {
        if (rptContainer.getRpt().getDatiSingoloVersamentoList().size() > Constants.SINGLE_PAYMENT_LIST_MAX_SIZE) {
            throw new ValidationException(ErrorMessages.VALIDATION_SINGLE_PAYMENT_LIST_SIZE_ERROR);
        }
    }

    /**
     * 
     * @param rptContainer
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
     * ibanAccredito non deve essere presente qualora sia stata popolata la
     * struttura datiMarcaBolloDigitale. In tutti gli alti casi e' obbligatorio.
     * 
     * @param rptContainer
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
