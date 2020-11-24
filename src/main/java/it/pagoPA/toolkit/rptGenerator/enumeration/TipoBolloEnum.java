package it.pagoPA.toolkit.rptGenerator.enumeration;

/**
 * 
 */
public enum TipoBolloEnum {
    IMPOSTA_DI_BOLLO("01");

    private final String value;

    /**
     * 
     * @param v
     * @return
     */
    public static TipoBolloEnum fromValue(String v) {
        for (TipoBolloEnum enumValue : TipoBolloEnum.values()) {
            if (enumValue.value.equals(v)) {
                return enumValue;
            }
        }
        throw new IllegalArgumentException(v);
    }

    /**
     * 
     * @param v
     */
    private TipoBolloEnum(String v) {
        value = v;
    }

    /**
     * 
     * @return
     */
    public String value() {
        return value;
    }
}
