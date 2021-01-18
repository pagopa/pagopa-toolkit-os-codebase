package pagopa.gov.it.toolkit.reader.enumeration;

/**
 * Enumeration of Reader statuses:
 * <ul>
 * <li>OK - Debt positions successfully generated
 * <li>WARNING - Not all the debt positions were successfully generated
 * <li>KO - No debt position was successfully generated
 * </ul>
 */
public enum ReaderStatusEnum {
    OK(1), WARNING(2), KO(3);

    private final Integer value;

    /**
     * Extracts the corresponding enum value from an integer value
     * 
     * @param v
     *            a value
     * @return the enum value
     */
    public static ReaderStatusEnum fromValue(Integer v) {
        for (ReaderStatusEnum enumValue : ReaderStatusEnum.values()) {
            if (enumValue.value.equals(v)) {
                return enumValue;
            }
        }
        throw new IllegalArgumentException(String.valueOf(v));
    }

    /**
     * @param v
     */
    private ReaderStatusEnum(Integer v) {
        value = v;
    }

    /**
     * @return the value
     */
    public int value() {
        return value;
    }
}
