package pagopa.gov.it.toolkit.rptGenerator.enumeration;

/**
 * Enumeration of rpt statuses:
 * <ul>
 * <li>CREATED - RPT created
 * <li>SENT - RPT sent to "Nodo dei Pagamenti-SPC"
 * <li>RECEIVED_RT - received related RT from "Nodo dei Pagamenti-SPC"
 * </ul>
 * 
 * @see pagopa.gov.it.toolkit.rptGenerator.bean.RptContainer
 * @see pagopa.gov.it.toolkit.rptGenerator.RptManagement
 */
public enum RptStatusEnum {
    CREATED(1), SENT(2), RECEIVED_RT(3);

    private final Integer value;

    /**
     * Extracts the corresponding enum value from an integer value
     * 
     * @param v
     *            a value
     * @return the enum value
     */
    public static RptStatusEnum fromValue(Integer v) {
        for (RptStatusEnum enumValue : RptStatusEnum.values()) {
            if (enumValue.value.equals(v)) {
                return enumValue;
            }
        }
        throw new IllegalArgumentException(String.valueOf(v));
    }

    /**
     * 
     * @param v
     */
    private RptStatusEnum(Integer v) {
        value = v;
    }

    /**
     * @return the value
     */
    public int value() {
        return value;
    }
}
