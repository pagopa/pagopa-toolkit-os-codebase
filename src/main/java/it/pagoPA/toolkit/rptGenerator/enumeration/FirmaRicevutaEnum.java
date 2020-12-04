package it.pagoPA.toolkit.rptGenerator.enumeration;

/**
 * 
 */
public enum FirmaRicevutaEnum {
    NON_RICHIESTA(0);

    private final Integer value;

    /**
     * 
     * @param v
     * @return
     */
    public static FirmaRicevutaEnum fromValue(Integer v) {
        for (FirmaRicevutaEnum enumValue : FirmaRicevutaEnum.values()) {
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
    private FirmaRicevutaEnum(Integer v) {
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
