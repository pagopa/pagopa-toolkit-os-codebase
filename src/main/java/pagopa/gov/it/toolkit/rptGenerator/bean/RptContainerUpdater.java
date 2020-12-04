package pagopa.gov.it.toolkit.rptGenerator.bean;

import pagopa.gov.it.toolkit.rptGenerator.bean.RptContainer;
import pagopa.gov.it.toolkit.rptGenerator.enumeration.RptStatusEnum;

/**
 * Allows the set of bean protected fields
 */
public class RptContainerUpdater {

    /**
     * Allows the set of the rptXml in RptContainer
     * 
     * @param rptContainer
     * @param rptXml
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.RptContainer
     */
    public static void setRptXml(RptContainer rptContainer, byte[] rptXml) {
        rptContainer.setRptXml(rptXml);
    }

    /**
     * Allows the set of the rptStatusEnum in RptContainer
     * 
     * @param rptContainer
     * @param rptStatusEnum
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.RptContainer
     */
    public static void setRptStatus(RptContainer rptContainer, RptStatusEnum rptStatusEnum) {
        rptContainer.setRptStatus(rptStatusEnum);
    }
}
