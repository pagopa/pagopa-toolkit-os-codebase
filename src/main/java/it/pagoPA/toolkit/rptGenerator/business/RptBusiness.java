package it.pagoPA.toolkit.rptGenerator.business;

import org.apache.commons.lang3.StringUtils;

import it.pagoPA.toolkit.rptGenerator.bean.RptContainer;
import it.pagoPA.toolkit.rptGenerator.bean.rpt.RptDatiSingoloVersamento;
import it.pagoPA.toolkit.rptGenerator.constants.RptConstants;
import it.pagoPA.toolkit.rptGenerator.enumeration.RptStatusEnum;
import it.pagoPA.toolkit.rptGenerator.validation.RptValidation;
import it.pagoPA.toolkit.rptGenerator.validation.RptValidationImpl;

/**
 *
 */
public class RptBusiness {

    /**
     * /RFB/<IUV>[/<importo>][/TXT/<descrizione>]
     * 
     * @param rptDatiSingoloVersamento
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

        rptDatiSingoloVersamento.setCausaleVersamento(agidCausal);
    }

    /**
     * 
     * @param rptContainer
     */
    public static void validate(RptContainer rptContainer) {
        RptValidation rptValidation = new RptValidationImpl();
        rptValidation.validate(rptContainer);
    }

    /**
     * XML RPT generation
     * 
     * @param rptContainer
     * @throws Exception
     */
    public static void generateRptXml(RptContainer rptContainer) throws Exception {
        XmlRptBusiness.generateRptXml(rptContainer);
    }

    /**
     * 
     * @param rptContainer
     * @param rptStatusEnum
     */
    public static void changeRptStatus(RptContainer rptContainer, RptStatusEnum rptStatusEnum) {
        rptContainer.setRptStatus(rptStatusEnum);
    }
}
