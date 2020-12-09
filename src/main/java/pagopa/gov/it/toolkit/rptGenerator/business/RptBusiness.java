package pagopa.gov.it.toolkit.rptGenerator.business;

import org.apache.commons.lang3.StringUtils;

import pagopa.gov.it.toolkit.rptGenerator.bean.RptContainer;
import pagopa.gov.it.toolkit.rptGenerator.bean.RptContainerUpdater;
import pagopa.gov.it.toolkit.rptGenerator.bean.rpt.Rpt;
import pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDatiSingoloVersamento;
import pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptUpdater;
import pagopa.gov.it.toolkit.rptGenerator.constants.RptConstants;
import pagopa.gov.it.toolkit.rptGenerator.enumeration.RptStatusEnum;
import pagopa.gov.it.toolkit.rptGenerator.validation.RptValidation;
import pagopa.gov.it.toolkit.rptGenerator.validation.RptValidationImpl;

/**
 * Business logic class
 */
public class RptBusiness {

    /**
     * Validates a rpt
     * 
     * @param rptContainer
     * @see RptContainer
     */
    public static void validate(RptContainer rptContainer) {
        RptValidation rptValidation = new RptValidationImpl();
        rptValidation.validate(rptContainer);
    }

    /**
     * Constraints validation by annotation of the rpt or one of its components
     * 
     * @param objectToValidate
     *            the bean to validate. It can be the rpt or one of its
     *            components.
     * @see RptValidationImpl
     * @see Rpt
     */
    public static <T> void validateConstraints(T objectToValidate) {
        RptValidation rptValidation = new RptValidationImpl();
        rptValidation.checkConstraints(objectToValidate);
    }

    /**
     * Calculates <code>causal</code> in PagoPa format:
     * "/RFB/<code>iuv</code>[/<code>importoSingoloVersamento</code>][/TXT/<code>descrizioneCausaleVersamento</code>]"
     * 
     * @param rptDatiSingoloVersamento
     * @see RptDatiSingoloVersamento
     */
    public static void createAgidCausal(RptDatiSingoloVersamento rptDatiSingoloVersamento) {
        String agidCausal = RptConstants.AGID_CAUSAL_FORMAT
                .replace(RptConstants.AGID_CAUSAL_IUV_PLACEHOLDER, rptDatiSingoloVersamento.getIuv())
                .replace(RptConstants.AGID_CAUSAL_AMOUNT_PLACEHOLDER,
                        rptDatiSingoloVersamento.getImportoSingoloVersamento().toString());

        if (StringUtils.isNotEmpty(rptDatiSingoloVersamento.getDescrizioneCausaleVersamento())) {
            agidCausal += RptConstants.AGID_CAUSAL_OPTIONAL_PART_FORMAT.replace(
                    RptConstants.AGID_CAUSAL_DESCRIPTION_PLACEHOLDER,
                    rptDatiSingoloVersamento.getDescrizioneCausaleVersamento());
        }

        RptUpdater.setCausaleVersamento(rptDatiSingoloVersamento, agidCausal);
    }

    /**
     * Generates the rpt in XML format
     * 
     * @param rptContainer
     * @throws Exception
     * @see RptContainer
     */
    public static void generateRptXml(RptContainer rptContainer) throws Exception {
        XmlRptBusiness.generateRptXml(rptContainer);
    }

    /**
     * Updates <code>rptStatus</code> in <code>rptContainer</code>
     * 
     * @param rptContainer
     * @param rptStatusEnum
     * @see RptContainer
     * @see RptStatusEnum
     */
    public static void changeRptStatus(RptContainer rptContainer, RptStatusEnum rptStatusEnum) {
        RptContainerUpdater.setRptStatus(rptContainer, rptStatusEnum);
    }
}
