package pagopa.gov.it.toolkit.rptGenerator.enumeration;

/**
 * Enumeration of firmaRicevuta:
 * <ul>
 * <li>NON_RICHIESTA - not necessary
 * </ul>
 * 
 * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDatiVersamento
 * @see pagopa.gov.it.toolkit.rptGenerator.RptGeneration
 */
public enum FirmaRicevutaEnum {
    NON_RICHIESTA(0);

    private final Integer value;

    /**
     * Extracts the corresponding enum value from an integer value
     * 
     * @param v
     *            a value
     * @return the enum value
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
     * @return the value
     */
    public int value() {
        return value;
    }
}
