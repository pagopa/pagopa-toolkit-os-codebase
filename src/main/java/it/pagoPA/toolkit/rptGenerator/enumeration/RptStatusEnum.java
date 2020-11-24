package it.pagoPA.toolkit.rptGenerator.enumeration;

/**
 * 
 */
public enum RptStatusEnum {
    CREATED(1), SENT(2), RECEIVED_RT(3);

    private final Integer value;

    /**
     * 
     * @param v
     * @return
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
     * 
     * @return
     */
    public int value() {
        return value;
    }
}
