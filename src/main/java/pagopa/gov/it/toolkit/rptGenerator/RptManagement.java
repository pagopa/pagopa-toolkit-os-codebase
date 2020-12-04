package pagopa.gov.it.toolkit.rptGenerator;

import pagopa.gov.it.toolkit.rptGenerator.bean.RptContainer;
import pagopa.gov.it.toolkit.rptGenerator.business.RptBusiness;
import pagopa.gov.it.toolkit.rptGenerator.enumeration.RptStatusEnum;

/**
 * Contains the methods for managing the <code>Rpt</code>
 */
public class RptManagement {

    /**
     * Validates a rpt
     * 
     * @param rptContainer
     * @throws Exception
     * @see RptContainer
     */
    public static void validate(RptContainer rptContainer) throws Exception {
        RptBusiness.validate(rptContainer);
    }

    /**
     * Updates <code>rptStatus</code> in <code>RptContainer</code> making it
     * sent
     * 
     * @param rptContainer
     * @return RptContainer with the changed status
     * @throws Exception
     * @see RptContainer
     */
    public static RptContainer makeSent(RptContainer rptContainer) throws Exception {
        RptBusiness.changeRptStatus(rptContainer, RptStatusEnum.SENT);
        return rptContainer;
    }

    /**
     * Updates <code>rptStatus</code> in <code>RptContainer</code> making it
     * received_rt
     * 
     * @param rptContainer
     * @return RptContainer with the changed status
     * @throws Exception
     */
    public static RptContainer makeReceivedRT(RptContainer rptContainer) throws Exception {
        RptBusiness.changeRptStatus(rptContainer, RptStatusEnum.RECEIVED_RT);
        return rptContainer;
    }
}