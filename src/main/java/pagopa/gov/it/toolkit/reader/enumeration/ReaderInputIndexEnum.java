package pagopa.gov.it.toolkit.reader.enumeration;

/**
 * Enumeration of Reader input data
 */
public enum ReaderInputIndexEnum {
    DOMAIN_AUX_DIGIT_INDEX(0),
    DOMAIN_SEGREGATION_CODE_INDEX(1),
    DOMAIN_APPLICATION_CODE_INDEX(2),
    DOMAIN_FISCAL_CODE_INDEX(3),
    DOMAIN_NAME_INDEX(4),
    DOMAIN_OPER_UNIT_CODE_INDEX(5),
    DOMAIN_OPER_UNIT_NAME_INDEX(6),
    DOMAIN_ADDRESS_INDEX(7),
    DOMAIN_NUMBER_STREET_INDEX(8),
    DOMAIN_LOCALITY_INDEX(9),
    DOMAIN_PROVINCE_INDEX(10),
    DOMAIN_POSTAL_CODE_INDEX(11),
    DOMAIN_NATION_INDEX(12),
    DOMAIN_SECTOR_INDEX(13),
    DOMAIN_CBILL_CODE_INDEX(14),
    DOMAIN_POSTAL_ACCOUNT_HOLDER_INDEX(15),
    DOMAIN_POSTAL_ACCOUNT_NUMBER_INDEX(16),
    DOMAIN_POSTAL_AUTH_CODE_INDEX(17),
    DOMAIN_INFO_INDEX(18),
    DOMAIN_WEBSITE_INDEX(19),
    PAYER_UNIQUE_IDENT_CODE_INDEX(20),
    PAYER_UNIQUE_IDENT_TYPE_INDEX(21),
    PAYER_REGISTRY_INDEX(22),
    PAYER_ADDRESS_INDEX(23),
    PAYER_NUMBER_STREET_INDEX(24),
    PAYER_LOCALITY_INDEX(25),
    PAYER_PROVINCE_INDEX(26),
    PAYER_POSTAL_CODE_INDEX(27),
    PAYER_NATION_INDEX(28),
    PAYER_EMAIL_INDEX(29),
    PAYER_MOBILE_INDEX(30),
    TENANT_ID_INDEX(31),
    TOTAL_AMOUNT_PAYMENT_INDEX(32),
    CAUSAL_INDEX(33),
    EXPIRATION_DATE_INDEX(34),
    SPECIFIC_COLLECTION_DATA_INDEX(35),
    DOCUMENT_NUMBER_INDEX(36),
    INSTALLMENT_NUMBER_INDEX(37),
    DEBIT_IBAN_INDEX(38),
    DEBIT_BIC_INDEX(39),
    CAUSAL_DESCRIPTION_INDEX(40),
    CREDIT_IBAN_INDEX(41),
    CREDIT_BIC_INDEX(42),
    SUPPORT_IBAN_INDEX(43),
    SUPPORT_BIC_INDEX(44),
    TIPO_BOLLO_INDEX(45),
    DOCUMENT_HASH_INDEX(46),
    RESIDENCE_PROVINCE_INDEX(47),
    MODELLO_1_INDEX(48);

    private final Integer value;

    /**
     * Extracts the corresponding enum value from an integer value
     * 
     * @param v
     *            a value
     * @return the enum value
     */
    public static ReaderInputIndexEnum fromValue(Integer v) {
        for (ReaderInputIndexEnum enumValue : ReaderInputIndexEnum.values()) {
            if (enumValue.value.equals(v)) {
                return enumValue;
            }
        }
        throw new IllegalArgumentException(String.valueOf(v));
    }

    /**
     * @param v
     */
    private ReaderInputIndexEnum(Integer v) {
        value = v;
    }

    /**
     * @return the value
     */
    public int value() {
        return value;
    }
}
