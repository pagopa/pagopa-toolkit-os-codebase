package pagopa.gov.it.toolkit.rptGenerator.constants;

/**
 * All the constants related to RPT
 */
public class RptConstants {

    public static final String AGID_CAUSAL_IUV_PLACEHOLDER = "<iuv>";
    public static final String AGID_CAUSAL_AMOUNT_PLACEHOLDER = "<amount>";
    public static final String AGID_CAUSAL_DESCRIPTION_PLACEHOLDER = "<causalDescription>";
    public static final String AGID_CAUSAL_FORMAT = "/RFB/" + AGID_CAUSAL_IUV_PLACEHOLDER + "/"
            + AGID_CAUSAL_AMOUNT_PLACEHOLDER;
    public static final String AGID_CAUSAL_OPTIONAL_PART_FORMAT = "/TXT/" + AGID_CAUSAL_DESCRIPTION_PLACEHOLDER;

    public static final String XSD_RPT_PATH = "src/main/resources/xsd/PagInf_RPT_RT_6_2_0.xsd";
    public static final String XSD_RPT_NAMESPACE = "http://www.digitpa.gov.it/schemas/2011/Pagamenti/";
    public static final String XSD_RPT_LOCAL_PART = "RPT";
}
