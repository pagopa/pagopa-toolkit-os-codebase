package it.pagoPA.toolkit.rptGenerator;

import it.pagoPA.toolkit.rptGenerator.bean.RptContainer;
import it.pagoPA.toolkit.rptGenerator.business.RptBusiness;
import it.pagoPA.toolkit.rptGenerator.enumeration.RptStatusEnum;

/**
 * RptManagement
 */
public class RptManagement {

    /**
     * 
     * @param rptContainer
     * @throws Exception
     */
    public static void validate(RptContainer rptContainer) throws Exception {
        RptBusiness.validate(rptContainer);
    }

    /**
     * 
     * @param rptContainer
     * @return
     * @throws Exception
     */
    public static RptContainer makeSent(RptContainer rptContainer) throws Exception {
        RptBusiness.changeRptStatus(rptContainer, RptStatusEnum.SENT);
        return rptContainer;
    }

    /**
     * 
     * @param rptContainer
     * @return
     * @throws Exception
     */
    public static RptContainer makeReceivedRT(RptContainer rptContainer) throws Exception {
        RptBusiness.changeRptStatus(rptContainer, RptStatusEnum.RECEIVED_RT);
        return rptContainer;
    }
}