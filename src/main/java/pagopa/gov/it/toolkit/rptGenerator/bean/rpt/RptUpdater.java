package pagopa.gov.it.toolkit.rptGenerator.bean.rpt;

/**
 * Allows the set of bean protected fields
 */
public class RptUpdater {

    /**
     * Allows the set of the causal in RptDatiSingoloVersamento
     * 
     * @param rptDatiSingoloVersamento
     * @param causal
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDatiSingoloVersamento
     */
    public static void setCausaleVersamento(RptDatiSingoloVersamento rptDatiSingoloVersamento, String causal) {
        rptDatiSingoloVersamento.setCausaleVersamento(causal);
    }
}
